package com.subscription.api.controller;

import com.subscription.api.exception.UserNotFoundException;
import com.subscription.api.model.User;
import com.subscription.api.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  UserService userService;


  @Test
  public void getUserDetailsTest() throws Exception{
    given(userService.getUserDetails(Mockito.anyString())).willReturn(new User("Tom"));
    mockMvc.perform(MockMvcRequestBuilders.get("/users/Tom"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isMap())
            .andExpect(jsonPath("userName").value("Tom"));
  }

  @Test
  void userNotFoundHTTPStatus() throws Exception {
    given(userService.getUserDetails(Mockito.anyString())).willThrow(new UserNotFoundException());

    mockMvc.perform(MockMvcRequestBuilders.get("/users/Tom"))
            .andExpect(status().isNotFound());

  }
}