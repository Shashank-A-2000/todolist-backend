package com.shashank.ToDoList.repository;

import com.shashank.ToDoList.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByUserid(String userid);
    User findByUseridAndPassword(String userid,String password);
}
