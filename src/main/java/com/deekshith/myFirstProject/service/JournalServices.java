package com.deekshith.myFirstProject.service;

import com.deekshith.myFirstProject.entity.Journal;
import com.deekshith.myFirstProject.entity.User;
import com.deekshith.myFirstProject.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class JournalServices {


    @Autowired
    public JournalRepository journalRepository;


    @Autowired
    public UserServices userServices;



    public Journal save(Journal journal, String username)
    {
        User user = userServices.findbyUserName(username);

        if(user == null)
        {
            throw new RuntimeException("User Not FOund"+ username);
        }
        journal.setDate(LocalDateTime.now());
        Journal saved = journalRepository.save(journal);
        user.getJournalEntries().add(saved);
        userServices.save(user);
        return saved;
    }

    public List<Journal> findbyId(String username)
    {
        User user = userServices.findbyUserName(username);

        if(user == null)
        {
            throw new RuntimeException("User Not FOund"+ username);
        }
        List<Journal> journals = user.getJournalEntries();
        return journals!=null? journals : List.of();
    }

    public Journal updateJournal(ObjectId id, Journal journal)
    {
        return journalRepository.findById(id)
                .map(existing->
                        {
                        existing.setTitle(journal.getTitle());
                        existing.setContent(journal.getContent());
                        existing.setDate(LocalDateTime.now());
                        return journalRepository.save(existing);
                        }).orElse(null);
    }

    public void deletebyId(String username, ObjectId id)
    {
        journalRepository.deleteById(id);
    }

    public void deleteAll()
    {
        journalRepository.deleteAll();
    }
}
