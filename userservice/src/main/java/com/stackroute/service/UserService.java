package com.stackroute.service;

import com.stackroute.domain.Users;
import com.stackroute.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    /**
     * AbstractMethod to save a user
     */
    Users saveUser(Users user);

    /**
     * AbstractMethod to save a list of users
     */
    List<Users> saveUsers(List<Users> users);

    /**
     * AbstractMethod to get all users
     */
    List<Users> findAllUser();

    /**
     * AbstractMethod to get user by id
     */
    Users findUserById(String id);

    Users findByIdAndPassword(String id, String password) throws UserNotFoundException;


    /**
     * AbstractMethod to delete user by id
     */
    Users deleteUserById(String id);

    /**
     * AbstractMethod to update a user
     */
    Users updateUser(Users user);
}
