package com.stackroute.controller;

import com.stackroute.domain.Users;
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

@CrossOrigin()
@RestController
@RequestMapping(value = "/api/v1/user")
@Slf4j
public class UserController {
    private UserService userService;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu - HH:mm:ss z");

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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

    @GetMapping("username/{username}")
    @ApiOperation(value = "GET a User by Username", notes = "GET a User entry from the users collection " +
            "by a provided User Name. Returns a User object if found.", response = ResponseEntity.class)
    public ResponseEntity<Users> loadUserByUsername(@PathVariable("username") String username){
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);
        log.info("Query to get a user | User Name: {} | Timestamp: {}" ,username ,timeStamp);

        return new ResponseEntity<>(userService.loadUserByUsername(username), HttpStatus.FOUND);
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
