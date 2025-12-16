package com.deekshith.myFirstProject.controller;


import com.deekshith.myFirstProject.entity.User;
import com.deekshith.myFirstProject.repository.UserRepository;
import com.deekshith.myFirstProject.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    public UserServices userServices;

    @Autowired
    public UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user)
    {
        try{
            return new ResponseEntity<>(userServices.save(user), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
