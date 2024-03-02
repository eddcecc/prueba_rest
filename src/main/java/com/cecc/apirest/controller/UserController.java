package com.cecc.apirest.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cecc.apirest.EmailValidator;
import com.cecc.apirest.ErrorResponse;
import com.cecc.apirest.PasswordValidator;
import com.cecc.apirest.PhoneService;
import com.cecc.apirest.UserResponse;
import com.cecc.apirest.UserService;
import com.cecc.apirest.model.Phone;
import com.cecc.apirest.model.User;

@RestController
@RequestMapping("api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;
    
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){

        if(userService.existsByEmail(user.getEmail())==true){
            ErrorResponse errorResponse = new ErrorResponse("El email ya existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        else{
            //No existe registro
            if (EmailValidator.isValid(user.getEmail())){
                if (PasswordValidator.isValid(user.getPassword())){
                    user.setCreated(LocalDateTime.now());
                    user.setModified(LocalDateTime.now());
                    user.setLast_login(LocalDateTime.now());
                    user.setIsActive(true);
                    user.setToken(UUID.randomUUID().toString());
                    userService.createUser(user);
                    
                    List<Phone> phones = user.getPhones();
                    if (phones != null) {
                        for (Phone phoneRequests : phones) {
                            phoneRequests.setUser(user);
                            phoneService.createPhone(phoneRequests);             
                        }
                    }

                    UserResponse response = new UserResponse();
                    response.setId(user.getId());
                    response.setCreated(user.getCreated());
                    response.setModified(user.getModified());
                    response.setLast_login(user.getLast_login());
                    response.setIsActive(user.getIsActive());
                    response.setToken(user.getToken());    
                    return ResponseEntity.ok(response);
                }
                else{
                    ErrorResponse errorResponse = new ErrorResponse("Password no tiene formato valido");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
                }                
            }
            else{
                ErrorResponse errorResponse = new ErrorResponse("Email no tiene formato valido");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
            
        }      
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> searchUserById(@PathVariable("id") Long id){
        System.out.println("Opcional Retornado:"+userService.getUserById(id));
        Optional<User> userOptional = userService.getUserById(id);

        if (userOptional.isPresent()){
            return ResponseEntity.ok(userService.getUserById(id));
        } 
        else{
            ErrorResponse errorResponse = new ErrorResponse("Id no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            // Devuelve una respuesta 200 OK con el usuario actualizado
            return ResponseEntity.ok(updatedUser);
        } else {
            // Si el usuario no se encuentra, devuelve un error 404 Not Found
            ErrorResponse errorResponse = new ErrorResponse("Id no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
