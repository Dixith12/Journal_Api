package com.deekshith.myFirstProject.service;

import com.deekshith.myFirstProject.entity.User;
import com.deekshith.myFirstProject.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class UserServices {


    @Autowired
    public UserRepository userRepository;


    private static final PasswordEncoder passwordencoder = new BCryptPasswordEncoder();

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User saveNewUser(User user) {
        user.setPassword(passwordencoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User"));
        return userRepository.save(user);
    }

    public User saveNewEntry(User user) {
        return userRepository.save(user);
    }

    public User findbyUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public User update(User updatedData, String username) {

        User existingUser = userRepository.findByUsername(username);

        if (existingUser == null) {
            throw new RuntimeException("User not found: " + username);
        }
        existingUser.setUsername(updatedData.getUsername());
        existingUser.setPassword(updatedData.getPassword());
        return userRepository.save(existingUser);
    }


    public void deletebyId(ObjectId id) {


        userRepository.deleteById(id);
    }

    public void deletebyUsername(String username)
    {

    }

    public User findby(String username)
    {
        return userRepository.findByUsername(username);
    }

}
