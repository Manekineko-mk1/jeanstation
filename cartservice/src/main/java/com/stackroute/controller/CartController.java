package com.stackroute.controller;

import com.stackroute.domain.Cart;
import com.stackroute.service.CartService;
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


@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class CartController {

    private final CartService cartService;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu - HH:mm:ss z");

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    /**
     * save a new Cart
     */
    @PostMapping("/cart")
    @ApiOperation(value = "POST a new Cart", notes = "Add a new Cart entry to the Cart database " +
            "using a provided JSON Cart object. Returns the newly created entry " +
            "if the operation is a success.", response = ResponseEntity.class)
    public ResponseEntity<Cart> saveCart(@RequestBody Cart cart) {
        Cart savedCart = cartService.saveCart(cart);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Added a cart to carts collection | Cart ID: {} | Cart name: {} | Timestamp(EST): {}",
                cart.getId(), cart.getCartName(), timeStamp);

        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }


    /**
     * retrieve all Carts
     */
    @GetMapping("/carts")
    @ApiOperation(value = "GET all Carts", notes = "GET all Cart entries from the Cart database. " +
            "Returns the result as a List of Cart object in JSON format " +
            "if any entry is found.", response = ResponseEntity.class)
    public ResponseEntity<List<Cart>> getAllCarts() {

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Query to get all cart entries | Timestamp: {}", timeStamp);

        return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
    }

    /**
     * retrieve Cart by id
     */
    @GetMapping("cart/{cartId}")
    @ApiOperation(value = "GET a Cart by ID", notes = "GET a Cart entry from the Cart database " +
            "by a provided Cart ID. Returns a Cart object if found.", response = ResponseEntity.class)
    public ResponseEntity<Cart> getCartById(@PathVariable("cartId") String cartId) {

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Query to get a cart | Cart ID: {} | Timestamp: {}" ,cartId ,timeStamp);

        return new ResponseEntity<>(cartService.getCartById(cartId), HttpStatus.FOUND);
    }

    /**
     * delete Cart by id
     */
    @DeleteMapping("cart/{cartId}")
    @ApiOperation(value = "DELETE an existing Cart", notes = "Remove a Cart entry from the Cart database " +
            "by a provided Cart ID. Returns the deleted Cart object " +
            "if the operation is successful.", response = ResponseEntity.class)
    public ResponseEntity<Cart> getCartAfterDeleting(@PathVariable("CartId") String cartId) {

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Request to DELETE a cart | Cart ID: {} | Timestamp: {}" ,cartId ,timeStamp);

        return new ResponseEntity<>(cartService.deleteCart(cartId), HttpStatus.OK);
    }

    /**
     * update Cart
     */
    @PutMapping("cart")
    @ApiOperation(value = "UPDATE an existing Cart", notes = "Update an existing Cart entry " +
            "from the Cart database using a provided JSON Cart object. " +
            "Returns the updated entry if the operation is a success.", response = ResponseEntity.class)
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCart(cart);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Request to UPDATE a cart | Cart Name: {} | Cart ID: {} | Timestamp: {}",
                cart.getCartName() ,cart.getId() ,timeStamp);

        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }
}
