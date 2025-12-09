package com.deekshith.myFirstProject.service;

import com.deekshith.myFirstProject.entity.JournalTable;
import com.deekshith.myFirstProject.entity.UserTable;
import com.deekshith.myFirstProject.repository.JournalRepository;
import com.deekshith.myFirstProject.repository.UserRepository;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class UserServices {


    @Autowired
    public UserRepository userRepository;



    public List<UserTable> getAll()
    {
        return userRepository.findAll();
    }

    public UserTable save(UserTable userTable)
    {

        return userRepository.save(userTable);
    }

    public Optional<UserTable> findbyId(ObjectId id)
    {
        return userRepository.findById(id);
    }

//    public JournalTable updateJournal(ObjectId id,JournalTable journalTable)
//    {
//        return journalRepository.findById(id)
//                .map(existing->
//                        {
//                        existing.setTitle(journalTable.getTitle());
//                        existing.setContent(journalTable.getContent());
//                        existing.setDate(LocalDateTime.now());
//                        return journalRepository.save(existing);
//                        }).orElse(null);
//    }

    public void deletebyId(ObjectId id)
    {
        userRepository.deleteById(id);
    }

}
