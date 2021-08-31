<<<<<<< HEAD
package com.stackroute.controller;

import com.stackroute.domain.Users;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.service.UserService;
import com.stackroute.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/user/")
@Slf4j
public class AuthRestController {

	@Autowired
	private JwtUtil jwtUtil;
	private UserService userService;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	ResponseEntity<?> responseEntity;
	final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu - HH:mm:ss z");

	@Autowired
	public AuthRestController(UserService userService) {
		this.userService = userService;
	}


	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody Users user) {
		try {
			if (user.getId() == null || user.getPassword() == null) {
				throw new UserNotFoundException("Id or Password Empty");
			}
			Users usersDetails = userService.findByIdAndPassword(user.getId(), user.getPassword());
			if (usersDetails == null) {
				throw new UserNotFoundException("Id and Password not found");
			}
			if (!(user.getPassword().equals(usersDetails.getPassword()))) {
				throw new UserNotFoundException("Id and Password invalid");
			}
			String token = jwtUtil.generateToken(usersDetails.getId());
			responseEntity = new ResponseEntity<>(token, HttpStatus.OK);

		} catch (UserNotFoundException e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;
//		String token = jwtUtil.generateToken(id);
//		return new ResponseEntity<>(token, HttpStatus.OK);
	}

	/**
	 * register a new User
	 */
	@PostMapping("register")
	@ApiOperation(value = "POST a new User", notes = "Add a new User entry to the users collection " +
			"using a provided JSON User object. Returns the newly created entry " +
			"if the operation is a success.", response = ResponseEntity.class)
	public ResponseEntity<?> register(@RequestBody Users user) {
		ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
		String timeStamp = zonedDateTimeNow.format(formatter);
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		Users encryptedUser = new Users(user.getUsername(),user.getUserRole(), user.getUserStatus(), user.getCreationDate(), user.getRealName(), user.getAddress(), user.getTelephone(), encodedPassword/*, authorities*/);
		Users newUser = userService.saveUser(encryptedUser);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
}
=======
//package com.stackroute.controller;
//
//import com.stackroute.domain.Users;
//import com.stackroute.exceptions.UserNotFoundException;
//import com.stackroute.service.UserService;
//import com.stackroute.util.JwtUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping(value = "/api/v1/")
//@Slf4j
//public class AuthRestController {
//
//	@Autowired
//	private JwtUtil jwtUtil;
//	private UserService userService;
//	ResponseEntity<?> responseEntity;
//
//	@PostMapping("/auth/login")
//	public ResponseEntity<?> login(@RequestBody Users users) {
//		try {
//			if (users.getId() == null || users.getPassword() == null) {
//				throw new UserNotFoundException("Id and Password Empty");
//			}
//			Users usersDetails = userService.findByIdAndPassword(users.getId(), users.getPassword());
//			if (usersDetails == null) {
//				throw new UserNotFoundException("Id and Password not found");
//			}
//			if (!(users.getPassword().equals(usersDetails.getPassword()))) {
//				throw new UserNotFoundException("Id and Password invalid");
//			}
//			String token = jwtUtil.generateToken(usersDetails.getId());
//			responseEntity = new ResponseEntity<>(token, HttpStatus.OK);
//
//		} catch (UserNotFoundException e) {
//			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//		}
//		return responseEntity;
//	}
//
////	@PostMapping("/auth/register")
////	public ResponseEntity<?> register(@RequestBody String userName) {
////		// Persist user to some persistent storage
////		System.out.println("Info saved...");
////		responseEntity = new ResponseEntity<>("Registered", HttpStatus.OK);
////		return responseEntity;
////	}
//}
>>>>>>> d526a11bb77da354cfd452b53ab183b8b5b9a500
