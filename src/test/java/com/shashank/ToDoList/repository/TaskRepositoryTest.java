package com.shashank.ToDoList.repository;

import com.shashank.ToDoList.entity.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Task task;

    @BeforeEach
    void setUp() {
        task = Task.builder()
                .status(false)
                .task("Manager Meeting")
                .uid(1)
                .userid("sananda@onetrust.com")
                .build();

        entityManager.persist(task);
    }

    @Test
    @DisplayName("Delete Task By Task Id")
    public void deleteTaskById(){

        int id = task.getId();

        taskRepository.deleteById(id);

        boolean result = taskRepository.existsById(id);

        assertEquals(result,false);
    }

    @Test
    @DisplayName("Fetch Tasks By User Id")
    public void getTasksById(){
        int uid = 1;

        List<Task> resultTask = taskRepository.findByUid(uid);

        assertEquals(resultTask.get(0).getUid(),uid);

    }

    @Test
    @DisplayName("Save Task By User Id")
    public void saveTaskById(){
        Task inputTask = Task.builder()
                         .task("Module Development")
                         .uid(2)
                         .userid("sananda@onetrust.com")
                         .status(false)
                         .build();

        Task returnedTask = taskRepository.save(inputTask);

        List<Task> resultTask = taskRepository.findByUid(2);

        assertEquals(resultTask.get(0).getUid(),2);

    }

    @Test
    @DisplayName("Update Task By Task Id")
    public void updateTaskById(){

        String newTask = "CEO Meeting";

        task.setTask(newTask);

        Task returnedTask = taskRepository.save(task);

        List<Task> resultTask = taskRepository.findByUid(1);

        assertEquals(resultTask.get(0).getTask(),newTask);

    }

    @Test
    @DisplayName("Update Task Status By Task Id")
    public void updateStatusById(){

        task.setStatus(true);

        Task returnedTask = taskRepository.save(task);

        List<Task> resultTask = taskRepository.findByUid(1);

        assertEquals(resultTask.get(0).getStatus(),true);

    }

}