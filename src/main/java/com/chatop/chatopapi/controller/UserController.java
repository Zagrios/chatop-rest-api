package com.chatop.chatopapi.controller;

import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable("id") Long id){
        return userService.getUser(id);
    }

}
