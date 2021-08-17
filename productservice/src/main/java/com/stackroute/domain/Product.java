package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.transaction.TransactionScoped;
import java.util.ArrayList;

@Slf4j
@Entity
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    @Column(name ="id", nullable = false)
    private Long id;
    private String productName;
    private String productDescription;
    private String picture;
    private float priceCAD;
    @Transient
    ArrayList<String> productCategories;// = new ArrayList<String>();

    public Product(Long id, String productName, String productDescription, String picture, float priceCAD, ArrayList<String> productCategories) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.picture = picture;
        this.priceCAD = priceCAD;
        this.productCategories = productCategories;

        log.info("A new product is created: {} | {}", this.id, this.productName);
    }
}