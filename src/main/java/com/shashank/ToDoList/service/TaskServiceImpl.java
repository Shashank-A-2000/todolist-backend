package com.shashank.ToDoList.service;

import com.shashank.ToDoList.entity.Task;
import com.shashank.ToDoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getTasksById(int uid) {
        return taskRepository.findByUid(uid);
    }

    @Override
    public Task saveTaskById(Task task) {

         Task newTask = new Task();
         newTask = task;
         newTask.setStatus(false);
         return taskRepository.save(newTask);

    }

    @Override
    public Task updateTaskById(Task task) {

        Task oldTask = taskRepository.getById(task.getId());
        if(Objects.nonNull(task.getTask()) && !"".equalsIgnoreCase(task.getTask())){
            oldTask.setTask(task.getTask());
        }
        return taskRepository.save(oldTask);

    }

    @Override
    public String deleteTaskById(int id) {

        taskRepository.deleteById(id);
        return "Task Deleted";

    }

    @Override
    public Task updateStatusById(int id) {

        Task oldTask = taskRepository.getById(id);
        oldTask.setStatus(true);
        return taskRepository.save(oldTask);

    }

}
