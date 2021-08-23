import { Component, OnInit } from '@angular/core';
import { Cart } from '../model/Cart';
import { Product } from '../model/Product';
import { ApprouteService } from '../services/approute.service';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartID: String;
  cart:Cart;
  cartItems:Product[];

  constructor(private cartservice:CartService, private approuter:ApprouteService) { }

  ngOnInit(): void {
    let priceTotal = 0;

    let payload = JSON.parse('{ "priceTotal" : 0}');

    // Create an empty cart
    this.createCart(payload);
    this.cartItems = new Array<Product>();

    this.cartItems.push({
      "productName" : "ngOnInit Item",
      "productDescription": "ngOnInit test",
      "picture": "product.picture",
      "priceCAD": 1,
      "discount": 1,
      "quantity": 1,
      "productSize": "XL",
      "productColor": "BLACK"
    });

    console.log('ngOnInit | cartItems: ' + JSON.stringify(this.cartItems));
    console.log('cartComponent initialized');
  }

  createCart(cart:Cart) {
    this.cartservice.createCart(cart).subscribe(
      data => {
        this.cart = data;
        this.cartID = data.id;
        this.cartItems = data.cartItems;
      }
    );
  }

  updateCart(cart:Cart) {
    this.cartservice.updateCart(cart).subscribe(
      data => {
        console.log('Updated cart: ' + data);
      }
    );
  }

  addItemToCart(product:Product) {
    console.log('addItemToCart | product: ' + JSON.stringify(product));
    console.log('addItemToCart | cartItems: ' + JSON.stringify(this.cartItems));

    this.cartItems.push({
      "productName" : "test",
      "productDescription": "test",
      "picture": "test",
      "priceCAD": 1,
      "discount": 1,
      "quantity": 1,
      "productSize": "XL",
      "productColor": "BLACK"
    });

    this.cart.cartItems = this.cartItems;

    this.updateCart(this.cart);

    console.log('addItemToCart | cartItems: ' + JSON.stringify(this.cartItems));
    console.log('Updated cart: ' + JSON.stringify(this.cart));
  }

  emptyCart() {
    this.cart.priceTotal = 0;
    this.cart.cartItems = [];
    this.cartservice.updateCart(this.cart);
  }

  deleteCart() {
      this.cartservice.deleteCart(this.cartID).subscribe(
        data => {
          console.log('DeleteCart succeed: ' + data);
        }
      );
  }
}
