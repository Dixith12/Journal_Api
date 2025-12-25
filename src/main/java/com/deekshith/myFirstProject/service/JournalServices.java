package com.deekshith.myFirstProject.service;

import com.deekshith.myFirstProject.entity.Journal;
import com.deekshith.myFirstProject.entity.User;
import com.deekshith.myFirstProject.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class JournalServices {


    @Autowired
    public JournalRepository journalRepository;


    @Autowired
    public UserServices userServices;



    @Transactional
    public Journal save(Journal journal, String username)
    {
        User user = userServices.findbyUserName(username);

        if(user == null)
        {
            throw new RuntimeException("User Not FOund"+ username);
        }
        journal.setDate(LocalDateTime.now());
        Journal saved = journalRepository.save(journal);
        if (user.getJournalEntries() == null) {
            user.setJournalEntries(new ArrayList<>());
        }
        user.getJournalEntries().add(saved);
        userServices.saveNewEntry(user);
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

    public Journal updateJournal(Journal journal, String username, ObjectId id) {

        User user = userServices.findbyUserName(username);

        boolean ownsJournal = user.getJournalEntries()
                .stream()
                .anyMatch(j -> j.getId().equals(id));

        if (!ownsJournal) {
            throw new RuntimeException("Journal not found for user: " + username);
        }

        Journal existing = journalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal not found"));

        existing.setTitle(journal.getTitle());
        existing.setContent(journal.getContent());
        existing.setDate(LocalDateTime.now());

        return journalRepository.save(existing);
    }


    public void deletebyId(String username, ObjectId id)
    {
        User user = userServices.findbyUserName(username);

        if(user == null)
        {
            throw new RuntimeException("User Not FOund"+ username);
        }
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userServices.saveNewUser(user);
        journalRepository.deleteById(id);


    }

    public void deleteAll()
    {
        journalRepository.deleteAll();
    }
}
