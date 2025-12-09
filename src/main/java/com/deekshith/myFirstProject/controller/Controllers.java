package com.deekshith.myFirstProject.controller;


import com.deekshith.myFirstProject.entity.JournalTable;
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
public class Controllers {


    @Autowired
    public JournalServices journalServices;

    @GetMapping
    public ResponseEntity<List<JournalTable>> getAll()
    {
        List<JournalTable> journ = journalServices.getAll();
        if(journ!=null&& !journ.isEmpty())
        {

            return new ResponseEntity<>(journ, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalTable> create(@RequestBody JournalTable journalTable)
    {
        try{
            return new ResponseEntity<>(journalServices.save(journalTable),HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ObjectId id)
    {
        Optional<JournalTable> journalTable = journalServices.findbyId(id);
        if(journalTable.isPresent())
        {
            return new ResponseEntity<>(journalTable.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(journalServices.findbyId(id),HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ObjectId id)
    {
        journalServices.deletebyId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalTable> update(@RequestBody JournalTable journalTable,@PathVariable ObjectId id)
    {
        try{
            return new ResponseEntity<>(journalServices.updateJournal(id,journalTable),HttpStatus.OK);
        }
        catch (Exception e)
        {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
