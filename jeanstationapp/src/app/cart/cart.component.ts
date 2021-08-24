import { Component, OnInit } from '@angular/core';
import { Cart } from '../model/Cart';
import { Product } from 'src/app/model/Product'
import { ApprouteService } from '../services/approute.service';
import { CartService } from '../services/cart.service';
import { MessengerService } from 'src/app/services/messenger.service';
import { CartStatus } from 'src/app/model/CartStatus';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartStatus: CartStatus;
  cart:Cart;
  cartItems: Product[];
  tax=0.14;
  priceTotalBeforeTax;
  priceTotalAfterTax;

  constructor(private cartservice:CartService, private approuter:ApprouteService, private msg: MessengerService) { }

  ngOnInit(): void {
    if (this.cartStatus != null && this.cartStatus.status) {
      this.retrieveCart(this.cartStatus.cartId);
    } else {
      this.initCart();
    }

    // Add listener to MessengerService
    this.msg.getMsg().subscribe(product => {
      this.addItemToCart(product);
      this.calculateTotal();
    });

    console.log('cartComponent initialized');
  }

  initCart() {
    // Initialization
    this.cart = JSON.parse('{ "priceTotalBeforeTax" : 0}');
    this.priceTotalBeforeTax = 0;
    this.priceTotalAfterTax = 0;
    this.cartItems = new Array<Product>();

    // Create an empty cart
    this.createCart(this.cart);

    this.cartStatus = new CartStatus(true, this.cart.id);

    console.log('cartComponent initialized');
  }

  retrieveCart(cartId: string) {
    this.cartservice.getCartById(cartId).subscribe(
      data => {
        this.cart = data;
      }
    );
  }

  createCart(cart:Cart) {
    this.cartservice.createCart(cart).subscribe(
      data => {
        this.cart = data;
      }
    );
  }

  addItemToCart(product:Product) {
    let ifProductExist = false;

    for(let i in this.cartItems) {
      if(this.cartItems[i].id === product.id) {
        this.cartItems[i].quantity++;
        ifProductExist = true;
        break;
      }
    }

    if(!ifProductExist) {
      product.quantity = 1;
      this.cartItems.push(product);
    }

    this.cart.cartItems = this.cartItems;
    this.updateCart(this.cart);
  }

  updateCart(cart:Cart) {
    this.cartservice.updateCart(cart).subscribe(
      data => {
        console.log(data);
        this.cart = data;
      }
    );
  }

  emptyCart() {
    this.cart.priceTotalBeforeTax = 0;
    this.cart.priceTotalAfterTax = 0;
    this.cart.cartItems = new Array<Product>();

    this.cartservice.updateCart(this.cart);
  }

  deleteCart() {
      this.cartservice.deleteCart(this.cart.id).subscribe(
        data => {
          console.log('DeleteCart succeed: ' + data);
        }
      );
  }

  calculateTotal() {
    this.cartItems.forEach(item => {
      this.priceTotalBeforeTax = this.priceTotalBeforeTax + (item.quantity * item.price);

    });

    this.priceTotalAfterTax = this.priceTotalBeforeTax + (this.priceTotalAfterTax * this.tax);
  }
}
