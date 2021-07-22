package com.subscription.api.service;

import com.subscription.api.exception.UserNotFoundException;
import com.subscription.api.model.User;
import com.subscription.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public User getUserDetails(String userName) {

      User user = null;
//    This is using Optional to return an empty object instead of a null value
      Optional<User> optionalUser = userRepository.findByUserName(userName);
      if(optionalUser.isPresent()){
        user = optionalUser.get();
      } else {
        throw new UserNotFoundException();
      }

      return user;
    }
}

