package com.subscription.api.service;

import com.subscription.api.exception.UserNotFoundException;
import com.subscription.api.model.User;
import com.subscription.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class UserServiceTest {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserService userService;

  @BeforeEach
  public void setUp(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void getUserDetailsWhenEndpointIsUsed() throws Exception{
    given(userRepository.findByUserName("Tom")).willReturn((Optional.of(new User("Tom"))));

    User user = userService.getUserDetails("Tom");
    assertNotNull(user);
    assertEquals("Tom", user.getUserName());
  }

  @Test
  void getUserNotFound() {
    given(userRepository.findByUserName("Tom")).willThrow(new UserNotFoundException());

    assertThrows(UserNotFoundException.class, () -> userService.getUserDetails("Tom"));
  }

}