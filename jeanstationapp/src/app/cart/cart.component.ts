import { Component, OnInit } from '@angular/core';
import { Cart } from '../model/Cart';
import { Product } from 'src/app/model/Product';
import { Money } from '../model/Money';
import { CartService } from '../services/cart.service';
import { MessengerService } from 'src/app/services/messenger.service';
import { CookieService } from 'ngx-cookie';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cart: Cart;
  cartId: string;
  cartItems: Product[] = new Array<Product>();
  tax = 0.14;
  priceTotalBeforeTax: number = 0;
  priceTotalAfterTax: number = 0;

  constructor(private cartService:CartService, private msg: MessengerService, private cookieService: CookieService) { }

  ngOnInit(): void {
    this.cartId = this.cookieService.get("cartId");

    // Retrieve Cart from cartID exists in cookie ELSE create a new Cart
    if (this.cartId != null) {
      this.retrieveCart(this.cartId);
    } else {
      this.createCart();
    }

    this.handleSubscription();
  }

  handleSubscription() {
    // Add listener to MessengerService
    this.msg.getMsg().subscribe(product => {
      this.addItemToCart(product);
    });
  }

  retrieveCart(cartId: string) {
    this.cartService.getCartById(cartId).subscribe(data => {
        this.cart = data;
        this.cart.cartItems === null ? this.cartItems = new Array<Product>() : this.cartItems = this.cart.cartItems;
        this.priceTotalBeforeTax === null ? this.priceTotalBeforeTax = 0 : this.priceTotalBeforeTax = this.cart.priceTotalBeforeTax;
        this.priceTotalAfterTax === null ? this.priceTotalAfterTax = 0 : this.priceTotalAfterTax = this.cart.priceTotalAfterTax;
    });
  }

  createCart() {
    // Create an empty cart
    this.cart = JSON.parse('{ "priceTotalBeforeTax" : 0, "priceTotalAfterTax" : 0, "cartItems": [] }');

    this.cartService.createCart(this.cart).subscribe(data => {
        this.cart = data;
        this.cookieService.put("cartId", this.cart.id);
    });
  }

  addItemToCart(product: Product) {
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

    this.calculateTotal();
    this.updateCart();
  }

  updateCart() {
    this.cartService.updateCart(this.cart).subscribe(data => {
        this.cart = data;
    });
  }

  emptyCart() {
    this.cart.priceTotalBeforeTax = 0;
    this.cart.priceTotalAfterTax = 0;
    this.cart.cartItems = new Array<Product>();

    this.cartService.updateCart(this.cart);
  }

  calculateTotal() {
    this.priceTotalBeforeTax = 0;
    this.priceTotalAfterTax = 0;

    this.cartItems.forEach(item => {
      this.priceTotalBeforeTax = this.priceTotalBeforeTax + (item.quantity * (item.finalPrice.amount / 100));
    });

    this.priceTotalAfterTax = this.priceTotalBeforeTax + (this.priceTotalBeforeTax * this.tax);

    this.cart.priceTotalBeforeTax = this.priceTotalBeforeTax;
    this.cart.priceTotalAfterTax = this.priceTotalAfterTax;
  }
}
