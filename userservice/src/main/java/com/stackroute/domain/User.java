package com.stackroute.domain;

import com.stackroute.enums.UserRole;
import com.stackroute.enums.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private UserRole userRole;
    private UserStatus userStatus;
    private Date creationDate;

    private String realName;
    private Address address;
    private String telephone;

    /*
     private List<Order> currentOrders;
     private List<Order> pastOrders;
    */

    public User(String username, UserRole userRole, UserStatus userStatus, Date creationDate, String realName, Address address, String telephone) {
        this.username = username;
        this.userRole = userRole;
        this.userStatus = userStatus;

        this.creationDate = creationDate;

        this.realName = realName;
        this.address = address;
        this.telephone = telephone;

        log.info("A new user is created | Username: {} | User role : {} | User status: {} " +
                "| User creation date: {} | User real name: {} | User address: {} | User telephone #: {} "
                ,this.username, this.userRole, this.userStatus, this.creationDate, this.realName
                ,this.address, this.telephone);
    }
}
