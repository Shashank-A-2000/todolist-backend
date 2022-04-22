package com.shashank.ToDoList.service;

import com.shashank.ToDoList.entity.Task;

import java.util.List;


public interface TaskService {
    List<Task> getTasksById(int uid);

    Task saveTaskById(Task task);

    Task updateTaskById(Task task);

    String deleteTaskById(int id);

    Task updateStatusById(int id);
}
