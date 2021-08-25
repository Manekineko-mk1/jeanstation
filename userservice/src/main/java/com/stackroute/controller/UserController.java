package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/")
@Slf4j
public class UserController {
    private UserService userService;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu - HH:mm:ss z");

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
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User saveUser = userService.saveUser(user);
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);
        log.info("Added a user to users collection | User ID: {} | User name: {} | Timestamp(EST): {}",
                user.getId(), user.getUsername(), timeStamp);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    /**
     * save a new User
     */
    @PostMapping("users")
    @ApiOperation(value = "POST a list of new User", notes = "Add a list of new User entries " +
            "to the User database using a provided JSON User object. Returns the newly created entry " +
            "if the operation is a success.", response = ResponseEntity.class)
    public ResponseEntity<List<User>> saveUsers(@RequestBody List<User> users) {
        List<User> savedUser = userService.saveUsers(users);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Added the list of users to the users collection | Timestamp(EST): {}", timeStamp);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    /**
     * retrieve all Users
     */
    @GetMapping("users")
    @ApiOperation(value = "GET all Users", notes = "GET all User entries from the users collection. " +
            "Returns the result as a List of User object in JSON format " +
            "if any entry is found.", response = ResponseEntity.class)
    public ResponseEntity<List<User>> getAllUsers() {

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
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {

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
    public ResponseEntity<User> getUserAfterDeleting(@PathVariable("userId") String userId) {

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
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Request to UPDATE a user | User Name: {} | User ID: {} | Timestamp: {}",
                user.getUsername(), user.getId(), timeStamp);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
