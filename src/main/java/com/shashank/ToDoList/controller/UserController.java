package com.shashank.ToDoList.controller;

import com.shashank.ToDoList.entity.User;
import com.shashank.ToDoList.error.UserIdAlreadyExistsException;
import com.shashank.ToDoList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.function.*;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public boolean userSignUp(@Valid @RequestBody User user) throws UserIdAlreadyExistsException {
        return userService.userSignUp(user);
    }

    @PostMapping("/login")
    public User userSignIn(@RequestBody User user){
        return userService.userSignIn(user);
    }
}
