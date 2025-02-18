package com.janitri.JanitriApplication;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.janitri.JanitriApplication.controllers.UserController;
import com.janitri.JanitriApplication.services.UserService;

@WebMvcTest(UserController.class) // Only loads the UserController for the test
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testLoginUser() throws Exception {
        String email = "test@example.com";
        String password = "password";

        when(userService.validateUser(email, password)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/users/login")
                .param("email", email)
                .param("password", password))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Login successful"));

        verify(userService, times(1)).validateUser(email, password);
    }
}
