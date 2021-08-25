package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.service.UserService;
import com.stackroute.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/")
@Slf4j
public class AuthRestController {

	@Autowired
	private JwtUtil jwtUtil;
	private UserService userService;
	ResponseEntity<?> responseEntity;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		try {
			if (user.getId() == null || user.getPassword() == null) {
				throw new UserNotFoundException("Id and Password Empty");
			}
			User userDetails = userService.findByIdAndPassword(user.getId(), user.getPassword());
			if (userDetails == null) {
				throw new UserNotFoundException("Id and Password not found");
			}
			if (!(user.getPassword().equals(userDetails.getPassword()))) {
				throw new UserNotFoundException("Id and Password invalid");
			}
			String token = jwtUtil.generateToken(userDetails.getId());
			responseEntity = new ResponseEntity<>(token, HttpStatus.OK);

		} catch (UserNotFoundException e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@PostMapping("/auth/register")
	public ResponseEntity<?> register(@RequestBody String userName) {
		// Persist user to some persistent storage
		System.out.println("Info saved...");
		responseEntity = new ResponseEntity<>("Registered", HttpStatus.OK);
		return responseEntity;
	}
}
