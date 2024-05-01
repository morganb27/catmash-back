package com.morganb27.catmash.service;

import com.morganb27.catmash.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);
    Optional<User> findUserById(Long id);
    List<User> findAllUsers();
    void deleteUser(Long id);
}
