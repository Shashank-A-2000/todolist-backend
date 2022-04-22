package com.shashank.ToDoList.controller;

import com.shashank.ToDoList.entity.Task;
import com.shashank.ToDoList.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private Task task;

    private List<Task> tasks = new ArrayList<>();

    @BeforeEach
    void setUp() {
        task = Task.builder()
                .task("Manager Meeting")
                .userid("sananda@onetrust.com")
                .status(false)
                .uid(1)
                .id(1)
                .build();

        tasks.add(task);
    }

    @Test
    @DisplayName("Get Task By Id")
    public void getTaskById() throws Exception{
        Mockito.when(taskService.getTasksById(1)).thenReturn(tasks);

        mockMvc.perform(get("/todolist/{uid}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].uid").value(task.getUid()))
                        .andExpect(jsonPath("$[0].task").value(task.getTask()));
    }

    @Test
    @DisplayName("Save Task By User Id")
    public void saveTaskById() throws Exception {
        Task inputTask = Task.builder()
                .task("Manager Meeting")
                .userid("sananda@onetrust.com")
                .status(false)
                .uid(1)
                .build();

        Mockito.when(taskService.saveTaskById(inputTask)).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.post("/todolist/add-task")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"uid\" : 1,\n" +
                        "    \"task\" : \"Manager Meeting\",\n" +
                        "    \"status\" : false,\n" +
                        "    \"userid\" : \"sananda@onetrust.com\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.task").value(inputTask.getTask()));
    }

    @Test
    @DisplayName("Update Task By Task Id")
    public void updateTaskById() throws Exception {

        Mockito.when(taskService.updateTaskById(task)).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.patch("/todolist/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"uid\" : 1,\n" +
                        "    \"task\" : \"Manager Meeting\",\n" +
                        "    \"status\" : false,\n" +
                        "    \"userid\" : \"sananda@onetrust.com\"\n" +
                        "}"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Delete Task By Task Id")
    public void deleteTaskById() throws Exception {
        Mockito.when(taskService.deleteTaskById(1)).thenReturn("Task Deleted");

        mockMvc.perform(MockMvcRequestBuilders.delete("/todolist/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Update Task Status By Task Id")
    public void updateStatusById() throws Exception {

        Task outputTask = Task.builder()
                    .task("Manager Meeting")
                    .userid("sananda@onetrust.com")
                    .uid(1)
                    .id(1)
                    .status(true)
                    .build();

        Mockito.when(taskService.updateStatusById(1)).thenReturn(outputTask);

        mockMvc.perform(MockMvcRequestBuilders.patch("/todolist/update-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": 1\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true));
    }
}