package com.shashank.ToDoList.service;

import com.shashank.ToDoList.entity.Task;
import com.shashank.ToDoList.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    private Task task;
    private List<Task> tasks;

    @BeforeEach
    void setUp() {
        task = Task.builder()
                .id(1)
                .uid(1)
                .status(false)
                .task("Manager Meeting")
                .userid("sananda@onetrust.com")
                .build();
        tasks = new ArrayList<Task>();
        tasks.add(task);
    }

    @Test
    @DisplayName("Get Task By User Id")
    public void getTasksById(){
        int uid = 1;

        Mockito.when(taskRepository.findByUid(uid)).thenReturn(tasks);

        List<Task> resultTasks = taskService.getTasksById(uid);

        assertThat(resultTasks.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Save Task By Id")
    public void saveTaskById(){
        Task inputTask = Task.builder()
                        .uid(1)
                        .task("Manager Meeting")
                        .userid("sananda@onetrust.com")
                        .build();

        Mockito.when(taskRepository.save(inputTask)).thenReturn(task);

        Task resultTask = taskService.saveTaskById(inputTask);

        assertEquals(1,resultTask.getUid());

    }

    @Test
    @DisplayName("Update Task By Task Id")
    public void updateTaskById(){
        Task inputTask = Task.builder()
                         .task("CEO Meeting")
                         .id(1)
                         .uid(1)
                         .userid("sananda@onetrust.com")
                         .status(false)
                         .build();

        Mockito.when(taskRepository.save(inputTask)).thenReturn(inputTask);

        Mockito.when(taskRepository.getById(1)).thenReturn(task);

        Task returnedTask = taskService.updateTaskById(inputTask);

        assertEquals(returnedTask.getTask(),inputTask.getTask());
    }

    @Test
    @DisplayName("Delete Task By Task Id")
    public void deleteTaskById(){

        int id = 1;

        taskService.deleteTaskById(id);

        verify(taskRepository).deleteById(id);

    }


    @Test
    @DisplayName("Update Task Status By Task Id")
    public void updateStatusById(){

        Task inputTask = Task.builder()
                         .id(1)
                         .uid(1)
                         .status(true)
                         .task("Manager Meeting")
                         .userid("sananda@onetrust.com")
                         .build();

        int id = 1;

        Mockito.when(taskRepository.getById(id)).thenReturn(task);

        Mockito.when(taskRepository.save(inputTask)).thenReturn(inputTask);

        Task returnedTask = taskService.updateStatusById(id);

        assertEquals(returnedTask.getStatus(),true);

    }
}
