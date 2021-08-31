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
//	@Autowired
//	private PasswordEncoder bCryptPasswordEncoder;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

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


	@PostMapping("register")
	@ApiOperation(value = "POST a new User", notes = "Add a new User entry to the users collection " +
			"using a provided JSON User object. Returns the newly created entry " +
			"if the operation is a success.", response = ResponseEntity.class)
	public ResponseEntity<?> register(@RequestBody Users user) {
//		// Persist user to some persistent storage
////		Users regUser = userService.saveUser(user);
//		UserController newUser;
////		System.out.println("Info saved...");
////		responseEntity = new ResponseEntity<>("Registered", HttpStatus.OK);
//		return responseEntity;
		ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
		String timeStamp = zonedDateTimeNow.format(formatter);
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		//List<GrantedAuthority> authorities = new ArrayList<>();
		Users encryptedUser = new Users(user.getUsername(),user.getUserRole(), user.getUserStatus(), user.getCreationDate(), user.getRealName(), user.getAddress(), user.getTelephone(), encodedPassword/*, authorities*/);
		Users newUser = userService.saveUser(encryptedUser);
//		log.info("Added a user to users collection | User ID: {} | User name: {} | Timestamp(EST): {}",
//				user.getId(), user.getUsername(), timeStamp);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
}
