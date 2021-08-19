package com.stackroute.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "userAddresses")
public class Address {
    @Id
    private String id;
    private int doorNumber;
    private String street;
    private String city;
    private String country;
    private String postalCode;

    public Address(int doorNumber, String street, String city, String country, String postalCode) {
        this.doorNumber = doorNumber;
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }
}
