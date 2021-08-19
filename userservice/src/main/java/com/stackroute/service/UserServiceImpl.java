package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
     * @param user
     */
    @Override
    public User saveUser(User user) {
        boolean isUserExist;

        if(user.getId() == null) {
            isUserExist = false;
        } else {
            isUserExist = userRepository.findById(user.getId()).isPresent();
        }

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if(isUserExist) {
            log.error("ERROR: Unable to add user. User already existed in database | User ID: {} | User name: {} | Timestamp(EST): {}",
                    user.getId(), user.getUsername(), timeStamp);

            throw new UserAlreadyExistsException(user.getId());
        } else {
            log.info("SUCCESS: Add a user to the \"users\" collection | User ID: {} | User name: {} | Timestamp(EST): {}",
                    user.getId(), user.getUsername(), timeStamp);

            return userRepository.save(user);
        }
    }

    /**
     * AbstractMethod to save a list of users
     *
     * @param users
     */
    @Override
    public List<User> saveUsers(List<User> users) {
        boolean isUserExist;

        for(User user : users){
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
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    /**
     * AbstractMethod to get user by id
     *
     * @param id
     */
    @Override
    public User findUserById(String id) {
        boolean isUserExist = userRepository.findById(id).isPresent();

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if(isUserExist) {
            log.info("SUCCESS: User found by ID | User ID: {} | Timestamp(EST): {}", id, timeStamp);

            return userRepository.findById(id).get();
        } else {
            log.error("ERROR: Unable to find user. User ID not found | User ID: {} | Timestamp(EST): {}", id, timeStamp);

            throw new UserNotFoundException(id);
        }
    }

    /**
     * AbstractMethod to delete user by id
     *
     * @param id
     */
    @Override
    public User deleteUserById(String id) {
        User user;
        Optional<User> optional = userRepository.findById(id);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if (optional.isPresent()) {
            user = userRepository.findById(id).get();
            userRepository.deleteById(id);

            log.info("SUCCESS: Deleted user by ID | User ID: {} | Timestamp(EST): {}", id, timeStamp);

            return user;
        } else {
            log.error("ERROR: Unable to delete user. User ID not found | User ID: {} | Timestamp(EST): {}", id, timeStamp);

            throw new UserNotFoundException(id);
        }
    }

    /**
     * AbstractMethod to update a user
     *
     * @param user
     */
    @Override
    public User updateUser(User user) {
        User updatedProduct;
        Optional<User> optional = userRepository.findById(user.getId());

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if (optional.isPresent()) {
            // Locate the existing product with same product ID
            User getUser = userRepository.findById(user.getId()).get();

            // Update the existing product with new info
            getUser.setUsername(user.getUsername());
            getUser.setUserRole(user.getUserRole());
            getUser.setUserStatus(user.getUserStatus());
            getUser.setCreationDate(user.getCreationDate());
            getUser.setRealName(user.getRealName());
            getUser.setAddress(user.getAddress());
            getUser.setTelephone(user.getTelephone());

            // Update the existing user to the DB
            userRepository.save(getUser);

            // Retrieve the updated product for return
            updatedProduct = userRepository.findById(user.getId()).get();

            log.info("SUCCESS: Updated user to the \"users\" collection | User ID: {} | User name: {} | Timestamp(EST): {}",
                    user.getId(), user.getUsername(), timeStamp);

            return updatedProduct;
        } else {
            log.error("ERROR: Unable to delete user. User ID not found | User ID: {} | Timestamp(EST): {}", user.getId(), timeStamp);

            throw new UserNotFoundException(user.getId());
        }
    }
}
