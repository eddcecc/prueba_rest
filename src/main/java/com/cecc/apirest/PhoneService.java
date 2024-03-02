package com.cecc.apirest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cecc.apirest.model.Phone;
import com.cecc.apirest.repository.PhoneRepository;

@Component
public class PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;

    public Phone createPhone(Phone phone){
        System.out.println("Entro al createPhone");
        if (phone.getUserId() != null){
            return phoneRepository.save(phone);
        }
        else{
            return phone;
        }
    }

    public Phone getPhoneById(Long id){
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        return optionalPhone.get();
    }

    public List<Phone> getAllPhones(){
        return phoneRepository.findAll();
    }

    public void deletePhone(Long id){
        phoneRepository.deleteById(id);
    }

}
