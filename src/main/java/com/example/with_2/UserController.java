package com.example.with_2;

import com.example.with_2.exception.PageNotFoundException;
import com.example.with_2.exception.UserNotFoundException;
import com.example.with_2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//
@RestController
@RequestMapping("/social")
public class UserController {

    private final UserService userService;  // Dependency injection for UserService

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping
    public ResponseEntity<String> welcomeMessage() {
        String message = "Welcome to the Social Network API!";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // Endpoint to get a user by name
    @GetMapping("/user")
    public ResponseEntity<?> getUserByName(@RequestParam String name) {
        try {
            User user = userService.getUserByName(name);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);  // Return user if found
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if user not found
            }
        } catch (UserNotFoundException e) {
            // Handle the case where the user is not found
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to get users that a specific user is following
    @GetMapping("/{name}/follows")
    public ResponseEntity<?> getUsersFollowing(@PathVariable String name) {
        try {
            List<User> followedUsers = userService.getUsersFollowing(name);
            return new ResponseEntity<>(followedUsers, HttpStatus.OK);  // Return followed users if found
        } catch (UserNotFoundException e) {
            // Handle the case where the user is not found
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to get followers of a specific user with pagination
    @GetMapping("/{name}/followers")
    public ResponseEntity<?> getUsersFollowers(@PathVariable String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        try {
            Page<User> followers = userService.getUsersFollowedBy(name, page, size);
            return new ResponseEntity<>(followers.getContent(), HttpStatus.OK);  // Return followers if found
        } catch (UserNotFoundException e) {
            // Handle the case where the user is not found
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (PageNotFoundException e) {
            // Handle the case where the page is not found
            return new ResponseEntity<>("Page not found", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to get mutual followers between two users
    @GetMapping("/mutualFollowers")
    public ResponseEntity<?> getMutualFollowers(@RequestParam String user1Name, @RequestParam String user2Name) {
        try {
            List<User> mutualFollowers = userService.getMutualFollowers(user1Name, user2Name);
            return new ResponseEntity<>(mutualFollowers, HttpStatus.OK);  // Return mutual followers if found
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to get all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);  // Return all users if found
    }
}
