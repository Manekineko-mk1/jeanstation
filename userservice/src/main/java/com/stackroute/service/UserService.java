package com.stackroute.service;

import com.stackroute.domain.Users;
import com.stackroute.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    /**
     * AbstractMethod to save a user
     */
<<<<<<< HEAD
    Users saveUser(Users user);
=======
    Users saveUser(Users users);
>>>>>>> d526a11bb77da354cfd452b53ab183b8b5b9a500

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
<<<<<<< HEAD
    Users updateUser(Users user);
=======
    Users updateUser(Users users);

    UserDetails loadUserByUsername(String username);
>>>>>>> d526a11bb77da354cfd452b53ab183b8b5b9a500
}
