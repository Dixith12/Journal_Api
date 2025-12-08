package com.deekshith.myFirstProject.controller;


import com.deekshith.myFirstProject.entity.JournalTable;
import com.deekshith.myFirstProject.service.JournalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controllers {


    @Autowired
    public JournalServices journalServices;

    @GetMapping("/all")
    public ResponseEntity<List<JournalTable>> getAll()
    {
        List<JournalTable> journ = journalServices.getAll();
        return new ResponseEntity<>(journ, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JournalTable> create(@RequestBody JournalTable journalTable)
    {
        return new ResponseEntity<>(journalServices.save(journalTable),HttpStatus.CREATED);
    }
}
