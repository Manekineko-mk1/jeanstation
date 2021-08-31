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
@Document(collection = "userdto")
public class UserDto {
    @Id
    private String username;
    private String password;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
