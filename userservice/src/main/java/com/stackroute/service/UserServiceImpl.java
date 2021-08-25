package com.stackroute.service;

import com.stackroute.domain.Users;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu - HH:mm:ss z");

    /**
     * Constructor based Dependency injection to inject ProductRepository here
     */
    @Autowired
    UserRepository userRepository;

    /**
     * AbstractMethod to save a user
     *
     * @param users
     */
    @Override
    public Users saveUser(Users users) {
        boolean isUserExist;

        if(users.getId() == null) {
            isUserExist = false;
        } else {
            isUserExist = userRepository.findById(users.getId()).isPresent();
        }

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if(isUserExist) {
            log.error("ERROR: Unable to add user. User already existed in database | User ID: {} | User name: {} | Timestamp(EST): {}",
                    users.getId(), users.getUsername(), timeStamp);

            throw new UserAlreadyExistsException(users.getId());
        } else {
            log.info("SUCCESS: Add a user to the \"users\" collection | User ID: {} | User name: {} | Timestamp(EST): {}",
                    users.getId(), users.getUsername(), timeStamp);

            return userRepository.save(users);
        }
    }

    /**
     * AbstractMethod to save a list of users
     *
     * @param users
     */
    @Override
    public List<Users> saveUsers(List<Users> users) {
        boolean isUserExist;

        for(Users user : users){
            if(user.getId() == null) {
                isUserExist = false;
            } else {
                isUserExist = userRepository.findById(user.getId()).isPresent();
            }

            ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
            String timeStamp = zonedDateTimeNow.format(formatter);

            if(isUserExist) {
                log.error("ERROR: Unable to add user. Product already existed in database | User ID: {} | User name: {} | Timestamp(EST): {}",
                        user.getId(), user.getUsername(), timeStamp);

                throw new UserAlreadyExistsException(user.getId());
            } else {
                userRepository.save(user);

                log.info("SUCCESS: Add a user to the \"users\" collection | User ID: {} | User name: {} | Timestamp(EST): {}",
                        user.getId(), user.getUsername(), timeStamp);
            }
        }

        return users;
    }

    /**
     * AbstractMethod to get all users
     */
    @Override
    public List<Users> findAllUser() {
        return userRepository.findAll();
    }

    /**
     * AbstractMethod to get user by id
     *
     * @param id
     */
    @Override
    public Users findUserById(String id) {
        boolean isUserExist = userRepository.findById(id).isPresent();

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if(isUserExist) {
            log.info("SUCCESS: User found by ID | User ID: {} | Timestamp(EST): {}", id, timeStamp);

            return userRepository.findById(id).get();
        } else {
            log.error("ERROR: Unable to find user. User ID not found | User ID: {} | Timestamp(EST): {}", id, timeStamp);

            throw new UserNotFoundException("User with ID "+id+" not found.");
        }
    }

    @Override
    public Users findByIdAndPassword(String id, String password) throws UserNotFoundException {
        Users authUsers = userRepository.findByIdAndPassword(id, password);
        if (authUsers == null) {
            throw new UserNotFoundException("Username or Password is invalid");
        }
        return authUsers;
    }



    /**
     * AbstractMethod to delete user by id
     *
     * @param id
     */
    @Override
    public Users deleteUserById(String id) {
        Users users;
        Optional<Users> optional = userRepository.findById(id);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if (optional.isPresent()) {
            users = userRepository.findById(id).get();
            userRepository.deleteById(id);

            log.info("SUCCESS: Deleted user by ID | User ID: {} | Timestamp(EST): {}", id, timeStamp);

            return users;
        } else {
            log.error("ERROR: Unable to delete user. User ID not found | User ID: {} | Timestamp(EST): {}", id, timeStamp);

            throw new UserNotFoundException(id);
        }
    }

    /**
     * AbstractMethod to update a user
     *
     * @param users
     */
    @Override
    public Users updateUser(Users users) {
        Users updatedProduct;
        Optional<Users> optional = userRepository.findById(users.getId());

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if (optional.isPresent()) {
            // Locate the existing product with same product ID
            Users getUsers = userRepository.findById(users.getId()).get();

            // Update the existing product with new info
            getUsers.setUsername(users.getUsername());
            getUsers.setUserRole(users.getUserRole());
            getUsers.setUserStatus(users.getUserStatus());
            getUsers.setCreationDate(users.getCreationDate());
            getUsers.setRealName(users.getRealName());
            getUsers.setAddress(users.getAddress());
            getUsers.setTelephone(users.getTelephone());

            // Update the existing user to the DB
            userRepository.save(getUsers);

            // Retrieve the updated product for return
            updatedProduct = userRepository.findById(users.getId()).get();

            log.info("SUCCESS: Updated user to the \"users\" collection | User ID: {} | User name: {} | Timestamp(EST): {}",
                    users.getId(), users.getUsername(), timeStamp);

            return updatedProduct;
        } else {
            log.error("ERROR: Unable to delete user. User ID not found | User ID: {} | Timestamp(EST): {}", users.getId(), timeStamp);

            throw new UserNotFoundException(users.getId());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username);

        if(users == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        return new User(users.getUsername(), users.getPassword(), authorities);
    }
}
