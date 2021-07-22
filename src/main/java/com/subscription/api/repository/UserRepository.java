package com.subscription.api.repository;


import com.subscription.api.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

  public Optional<User> findByUserName(String userName);
  public List<User> findAll();

}
