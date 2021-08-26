package com.stackroute.controller;

import com.stackroute.domain.Users;
import com.stackroute.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/")
@Slf4j
public class UserController {
    private UserService userService;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu - HH:mm:ss z");

    // @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * save a new User
     */
    @PostMapping("user")
    @ApiOperation(value = "POST a new User", notes = "Add a new User entry to the users collection " +
            "using a provided JSON User object. Returns the newly created entry " +
            "if the operation is a success.", response = ResponseEntity.class)
    public ResponseEntity<Users> saveUser(@RequestBody Users user) {
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        //List<GrantedAuthority> authorities = new ArrayList<>();
        Users encryptedUser = new Users(user.getUsername(),user.getUserRole(), user.getUserStatus(), user.getCreationDate(), user.getRealName(), user.getAddress(), user.getTelephone(), encodedPassword/*, authorities*/);
        userService.saveUser(encryptedUser);


        log.info("Added a user to users collection | User ID: {} | User name: {} | Timestamp(EST): {}",
                user.getId(), user.getUsername(), timeStamp);
        return new ResponseEntity<>(encryptedUser, HttpStatus.CREATED);
    }

    /**
     * save a new User
     */
    @PostMapping("users")
    @ApiOperation(value = "POST a list of new User", notes = "Add a list of new User entries " +
            "to the User database using a provided JSON User object. Returns the newly created entry " +
            "if the operation is a success.", response = ResponseEntity.class)
    public ResponseEntity<List<Users>> saveUsers(@RequestBody List<Users> users) {
        List<Users> savedUsers = userService.saveUsers(users);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Added the list of users to the users collection | Timestamp(EST): {}", timeStamp);

        return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);
    }


    /**
     * retrieve all Users
     */
    @GetMapping("users")
    @ApiOperation(value = "GET all Users", notes = "GET all User entries from the users collection. " +
            "Returns the result as a List of User object in JSON format " +
            "if any entry is found.", response = ResponseEntity.class)
    public ResponseEntity<List<Users>> getAllUsers() {

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Query to get all user entries | Timestamp: {}", timeStamp);

        return new ResponseEntity<>(userService.findAllUser(), HttpStatus.OK);
    }

    /**
     * retrieve User by id
     */
    @GetMapping("user/{userId}")
    @ApiOperation(value = "GET a User by ID", notes = "GET a User entry from the users collection " +
            "by a provided User ID. Returns a User object if found.", response = ResponseEntity.class)
    public ResponseEntity<Users> getUserById(@PathVariable("userId") String userId) {

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Query to get a user | User ID: {} | Timestamp: {}" ,userId ,timeStamp);

        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.FOUND);
    }

    /**
     * delete User by id
     */
    @DeleteMapping("user/{userId}")
    @ApiOperation(value = "DELETE an existing User", notes = "Remove a User entry from the users collection " +
            "by a provided User ID. Returns the deleted User object " +
            "if the operation is successful.", response = ResponseEntity.class)
    public ResponseEntity<Users> getUserAfterDeleting(@PathVariable("userId") String userId) {

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Request to DELETE a user | User ID: {} | Timestamp: {}",userId ,timeStamp);

        return new ResponseEntity<>(userService.deleteUserById(userId), HttpStatus.OK);
    }

    /**
     * update User
     */
    @PutMapping("user")
    @ApiOperation(value = "UPDATE an existing User", notes = "Update an existing User entry " +
            "from the users collection using a provided JSON User object. " +
            "Returns the updated entry if the operation is a success.", response = ResponseEntity.class)
    public ResponseEntity<Users> updateUser(@RequestBody Users users) {
        Users updatedUsers = userService.updateUser(users);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Request to UPDATE a user | User Name: {} | User ID: {} | Timestamp: {}",
                users.getUsername(), users.getId(), timeStamp);

        return new ResponseEntity<>(updatedUsers, HttpStatus.OK);
    }
}
