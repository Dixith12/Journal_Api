package com.deekshith.myFirstProject.controller;


import com.deekshith.myFirstProject.entity.User;
import com.deekshith.myFirstProject.repository.UserRepository;
import com.deekshith.myFirstProject.service.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserControllers {


    @Autowired
    public UserServices userServices;

    @Autowired
    public UserRepository userRepository;



    @GetMapping
    public ResponseEntity<List<User>> getAll()
    {
        List<User> journ = userServices.getAll();
        if(journ!=null&& !journ.isEmpty())
        {

            return new ResponseEntity<>(journ, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user)
    {
        try{
            return new ResponseEntity<>(userServices.save(user),HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getById(@PathVariable String username)
    {
        User userTable = userServices.findbyUserName(username);
        if(userTable !=null)
        {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @DeleteMapping("/{username}")
//    public ResponseEntity<?> delete(@PathVariable String username)
//    {
//        userServices.deletebyId(username);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @PutMapping("/username")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable String username)
    {

         return new ResponseEntity<>(userServices.update(user,username),HttpStatus.OK);
    }
}
