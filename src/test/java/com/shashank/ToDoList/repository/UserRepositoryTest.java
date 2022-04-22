package com.shashank.ToDoList.repository;

import com.shashank.ToDoList.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .userid("sananda@onetrust.com")
                .username("Shashank")
                .password("password")
                .build();

        entityManager.persist(user);
    }

    @Test
    @DisplayName("User Signup")
    public void userSignUp(){

        User inputUser = User.builder()
                .username("Shashank")
                .userid("sananda@onetrust.com")
                .password("password")
                .build();

        List<User> output = userRepository.findByUserid("sananda@onetrust.com");

        User returnedUser = userRepository.save(inputUser);

        assertEquals("sananda@onetrust.com",output.get(0).getUserid());
        assertEquals("sananda@onetrust.com",returnedUser.getUserid());
    }

    @Test
    @DisplayName("User Login")
    public void userSignIn(){

        User returnedUser = userRepository
                            .findByUseridAndPassword("sananda@onetrust.com","password");

        assertNotNull(returnedUser);
    }
}
