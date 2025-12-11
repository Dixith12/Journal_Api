package com.deekshith.myFirstProject.controller;


import com.deekshith.myFirstProject.entity.Journal;
import com.deekshith.myFirstProject.service.JournalServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journals")
public class JournalControllers {


    @Autowired
    public JournalServices journalServices;




    @PostMapping("/{username}")
    public ResponseEntity<Journal> create(@RequestBody Journal journal,@PathVariable String username)
    {
        try{
            return new ResponseEntity<>(journalServices.save(journal,username),HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getById(@PathVariable String username)
    {

        return new ResponseEntity<>(journalServices.findbyId(username),HttpStatus.FOUND);
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<?> delete(@PathVariable String username,@PathVariable ObjectId id)
    {
        journalServices.deletebyId(username,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{username}/{id}")
    public ResponseEntity<Journal> update(@RequestBody Journal journal,@PathVariable String username, @PathVariable ObjectId id)
    {
        try{
            return new ResponseEntity<>(journalServices.updateJournal(journal, username,id),HttpStatus.OK);
        }
        catch (Exception e)
        {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
