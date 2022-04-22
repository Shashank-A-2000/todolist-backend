package com.shashank.ToDoList.service;

import com.shashank.ToDoList.entity.User;
import com.shashank.ToDoList.error.UserIdAlreadyExistsException;

public interface UserService {

    boolean userSignUp(User user) throws UserIdAlreadyExistsException;

    User userSignIn(User user);
}
