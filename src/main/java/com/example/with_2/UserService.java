package com.example.with_2;

import com.example.with_2.exception.PageNotFoundException;
import com.example.with_2.exception.UserNotFoundException;
import com.example.with_2.model.User;
import com.example.with_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public List<User> getMutualFollowers(String user1Name, String user2Name) {
        User user1 = getUserByName(user1Name);
        User user2 = getUserByName(user2Name);
        return userRepository.getMutualFollowers(user1Name, user2Name);


    }
    public User getUserByName(String name) {
        User user = userRepository.findByName(name);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User not found with name: " + name);
        }
    }

    // UserService.java
    public List<User> getUsersFollowing(String name) {
        User user = getUserByName(name);
        return userRepository.getUsersFollowing(name);


    }


    public Page<User> getUsersFollowedBy(String name, int page, int size) {
        if (page < 0) {
            // Throw a PageNotFoundException for negative page numbers
            throw new PageNotFoundException("Page not found");
        }
        User user = getUserByName(name);
        return userRepository.getUsersFollowerBy(name, PageRequest.of(page, size));

    }



    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
