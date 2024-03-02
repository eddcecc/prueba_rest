package com.cecc.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cecc.apirest.PhoneService;
import com.cecc.apirest.model.Phone;

@RestController
@RequestMapping("api/phones")
public class PhoneController {
    
    @Autowired
    private PhoneService phoneService;
    
    @PostMapping
    public Phone createPhone(@RequestBody Phone phone){
        return phoneService.createPhone(phone);        
    }

    @GetMapping
    public List<Phone> getAllPhones(){
        return phoneService.getAllPhones();
    }

    @GetMapping("{id}")
    public Phone searchPhoneById(@PathVariable("id") Long id){
        return phoneService.getPhoneById(id);
    }

    @DeleteMapping("{id}")
    public void deletePhoneById(@PathVariable("id") Long id){
        phoneService.deletePhone(id);
    }

}
