package com.vitalynec.phonebook.controller;

import com.vitalynec.phonebook.domain.User;
import com.vitalynec.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CLIController {

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    UserService userService;

    public void getAllUsers() {
        List<User> userList = userService.findAll();
        userList.forEach(System.out::println);
    }

}
