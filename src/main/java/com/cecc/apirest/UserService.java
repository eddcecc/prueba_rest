package com.cecc.apirest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cecc.apirest.model.User;
import com.cecc.apirest.repository.UserRepository;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    
    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public boolean existsByEmail(String email){
        if (userRepository.findByEmail(email) != null) {
            return true;
        }
        else{
            return false;
        }
        
    }

    public User updateUser(Long id, User user){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setName(user.getName());
            existingUser.setPassword(user.getPassword());
            existingUser.setModified(LocalDateTime.now());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }


}
