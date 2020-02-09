package com.vitalynec.phonebook.controller;

import com.vitalynec.phonebook.domain.Phone;
import com.vitalynec.phonebook.domain.User;
import com.vitalynec.phonebook.exception.NotFoundException;
import com.vitalynec.phonebook.service.PhoneService;
import com.vitalynec.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonController {

    protected UserService userService;
    protected PhoneService phoneService;

    @Autowired
    public void setPhoneService(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllUsers() {
        return userService.findAll();
    }

    public User getUserById(Long id) throws NotFoundException {
        return userService.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Phone> getAllPhones() {
        return phoneService.findAll();
    }
}
