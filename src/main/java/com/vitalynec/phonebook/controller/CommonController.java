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

    public void addUser(String name) {
        User user = new User();
        user.setName(name);
        userService.save(user);
    }

    public List<User> getAllUsers() {
        return userService.findAll();
    }

    public User getUserById(Long id) throws NotFoundException {
        return userService.findById(id).orElseThrow(NotFoundException::new);
    }

    public User getUserByName(String name) throws NotFoundException {
        return userService.findByName(name).orElseThrow(NotFoundException::new);
    }

    public void addPhoneToUser(String number, Long id) throws NotFoundException {
        Phone phone = new Phone();
        phone.setUser(getUserById(id));
        phone.setNumber(phoneService.extractNumber(number));
        phoneService.save(phone);
    }

    public void removePhone(String number) {

    }
}
