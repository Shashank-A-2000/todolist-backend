package com.shashank.ToDoList.service;

import com.shashank.ToDoList.entity.User;
import com.shashank.ToDoList.error.UserIdAlreadyExistsException;
import com.shashank.ToDoList.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user;

    private List<User> users = new ArrayList<User>();

    @BeforeEach
    void setUp() {
        user = User.builder()
                .username("Shashank")
                .userid("sananda@onetrust.com")
                .password("password")
                .id(1)
                .build();
    }

    @Test
    @DisplayName("User Sign Up")
    public void userSignUp() throws UserIdAlreadyExistsException {

        User inputUser = User.builder()
                         .username("Shashank")
                         .userid("sananda@onetrust.com")
                         .password("password")
                         .build();

        Mockito.when(userRepository.findByUserid("sananda@onetrust.com")).thenReturn(users);

        Mockito.when(userRepository.save(inputUser)).thenReturn(user);

        boolean result = userService.userSignUp(inputUser);

        assertEquals(true,result);

    }

    @Test
    @DisplayName("User Login In")
    public void userSignIn(){

        User inputUser = User.builder().userid("sananda@onetrust.com").password("password").build();

        Mockito.when(userRepository.findByUseridAndPassword(inputUser.getUserid(),inputUser.getPassword())).thenReturn(user);

        User result = userService.userSignIn(inputUser);

        assertEquals(result.getUserid(),"sananda@onetrust.com");
    }
}