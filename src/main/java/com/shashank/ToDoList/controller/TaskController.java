package com.shashank.ToDoList.controller;

import com.shashank.ToDoList.entity.Task;
import com.shashank.ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/todolist")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{uid}")
    public List<Task> getTasksById(@PathVariable("uid") int uid){

        return taskService.getTasksById(uid);
    }

    @PostMapping("/add-task")
    public Task saveTaskById(@Valid @RequestBody Task task){
        return taskService.saveTaskById(task);
    }

    @PutMapping("/update")
    public Task updateTaskById(@Valid @RequestBody Task task){
        return taskService.updateTaskById(task);
    }

    @DeleteMapping("/delete/{tid}")
    public String deleteTaskById(@PathVariable("tid") int tid){
        return taskService.deleteTaskById(tid);
    }

    @PatchMapping("/update-status")
    public Task updateStatusById(@RequestBody Map<String, Integer> payload){
        return taskService.updateStatusById(payload.get("sid"));
    }
}
