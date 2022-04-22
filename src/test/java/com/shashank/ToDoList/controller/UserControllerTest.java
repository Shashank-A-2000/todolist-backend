package com.shashank.ToDoList.controller;

import com.shashank.ToDoList.entity.User;
import com.shashank.ToDoList.service.UserService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .userid("sananda@onetrust.com")
                .username("Shashank")
                .password("password")
                .build();
    }

    @Test
    @DisplayName("User Sign Up")
    public void userSignUp() throws Exception {

        Mockito.when(userService.userSignUp(user)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\" : \"Shashank\",\n" +
                        "    \"userid\" : \"sananda@onetrust.com\",\n" +
                        "    \"password\" : \"password\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("User Login In")
    public void userSignIn() throws Exception {
        User inputUser = User.builder()
                        .userid("sananda@onetrust.com")
                        .password("password")
                        .build();
        User outputUser = User.builder()
                .userid("sananda@onetrust.com")
                .password("password")
                .id(1)
                .build();

        Mockito.when(userService.userSignIn(inputUser)).thenReturn(outputUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"userid\" : \"sananda@onetrust.com\",\n" +
                                "    \"password\" : \"password\"\n" +
                                "}"))
                        .andExpect(status().isOk());

    }
}