package com.shashank.ToDoList.service;

import com.shashank.ToDoList.entity.User;
import com.shashank.ToDoList.error.UserIdAlreadyExistsException;
import com.shashank.ToDoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean userSignUp(User user) throws UserIdAlreadyExistsException {

        List<User> output = userRepository.findByUserid(user.getUserid());

        if(!output.isEmpty())
           throw new UserIdAlreadyExistsException("User Id Already Exists");

        else {
             userRepository.save(user);
             return true;
        }

    }

    @Override
    public User userSignIn(User user) {
        User returnedUser = userRepository.findByUseridAndPassword(user.getUserid(),user.getPassword());

        return returnedUser;
    }
}
