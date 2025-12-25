package com.deekshith.myFirstProject.controller;


import com.deekshith.myFirstProject.entity.User;
import com.deekshith.myFirstProject.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    public UserServices userServices;

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<User> all = userServices.getAll();
        if(!all.isEmpty()&&all!=null){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
