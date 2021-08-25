package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    /**
     * AbstractMethod to save a user
     */
    User saveUser(User user);

    /**
     * AbstractMethod to save a list of users
     */
    List<User> saveUsers(List<User> users);

    /**
     * AbstractMethod to get all users
     */
    List<User> findAllUser();

    /**
     * AbstractMethod to get user by id
     */
    User findUserById(String id);

    User findByIdAndPassword(String id, String password) throws UserNotFoundException;


    /**
     * AbstractMethod to delete user by id
     */
    User deleteUserById(String id);

    /**
     * AbstractMethod to update a user
     */
    User updateUser(User user);
}
