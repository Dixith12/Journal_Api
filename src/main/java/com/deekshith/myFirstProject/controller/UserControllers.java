package com.deekshith.myFirstProject.controller;


import com.deekshith.myFirstProject.entity.JournalTable;
import com.deekshith.myFirstProject.entity.UserTable;
import com.deekshith.myFirstProject.service.JournalServices;
import com.deekshith.myFirstProject.service.UserServices;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journals")
public class UserControllers {


    @Autowired
    public UserServices userServices;

    @GetMapping
    public ResponseEntity<List<UserTable>> getAll()
    {
        List<UserTable> journ = userServices.getAll();
        if(journ!=null&& !journ.isEmpty())
        {

            return new ResponseEntity<>(journ, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<UserTable> create(@RequestBody UserTable userTable)
    {
        try{
            return new ResponseEntity<>(userServices.save(userTable),HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ObjectId id)
    {
        Optional<UserTable> userTable = userServices.findbyId(id);
        if(userTable.isPresent())
        {
            return new ResponseEntity<>(userTable.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(userServices.findbyId(id),HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ObjectId id)
    {
        userServices.deletebyId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<JournalTable> update(@RequestBody JournalTable journalTable,@PathVariable ObjectId id)
//    {
//        try{
//            return new ResponseEntity<>(userServices.updateJournal(id,journalTable),HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
}
