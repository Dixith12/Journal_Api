package com.deekshith.myFirstProject.service;

import com.deekshith.myFirstProject.entity.JournalTable;
import com.deekshith.myFirstProject.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class JournalServices {


    @Autowired
    public JournalRepository journalRepository;



    public List<JournalTable> getAll()
    {
        return journalRepository.findAll();
    }

    public JournalTable save(JournalTable journalTable)
    {
        journalTable.setDate(LocalDateTime.now());
        return journalRepository.save(journalTable);
    }

    public Optional<JournalTable> findbyId(ObjectId id)
    {
        return journalRepository.findById(id);
    }

    public JournalTable updateJournal(ObjectId id,JournalTable journalTable)
    {
        return journalRepository.findById(id)
                .map(existing->
                        {
                        existing.setTitle(journalTable.getTitle());
                        existing.setContent(journalTable.getContent());
                        existing.setDate(LocalDateTime.now());
                        return journalRepository.save(existing);
                        }).orElse(null);
    }

    public void deletebyId(ObjectId id)
    {
        journalRepository.deleteById(id);
    }

    public void deleteAll()
    {
        journalRepository.deleteAll();
    }
}
