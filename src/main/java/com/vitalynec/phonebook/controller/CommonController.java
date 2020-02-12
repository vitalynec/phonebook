package com.vitalynec.phonebook.controller;

import com.vitalynec.phonebook.entity.User;
import com.vitalynec.phonebook.entity.dto.PhoneDto;
import com.vitalynec.phonebook.entity.dto.UserDto;
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
        UserDto user = new UserDto();
        user.setName(name);
        userService.save(user);
    }

    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    public UserDto getUserById(Integer id) throws NotFoundException {
        return userService.findById(id).orElseThrow(NotFoundException::new);
    }

    public void addPhoneToUser(String number, Integer id) throws NotFoundException {
        PhoneDto phone = new PhoneDto();
        phone.setUser(getUserById(id));
        phone.setNumber(number);
        phoneService.save(phone);
    }

    public void removePhone(Integer id) {
    }

    public void removeUser(Integer id) throws NotFoundException {
        userService.deleteById(id);
    }
}
