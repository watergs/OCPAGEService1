package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/age/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return userService.getUser(userId).isPresent() ?
                new ResponseEntity<>(userService.getUser(userId).get(), new HttpHeaders(), HttpStatus.OK)
                : new ResponseEntity<>( null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), new HttpHeaders(), HttpStatus.CREATED);
    }
}
