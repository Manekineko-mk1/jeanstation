import { Component, OnInit } from '@angular/core';
import { Cart } from '../model/Cart';
import { Product } from 'src/app/model/Product';
import { Money } from '../model/Money';
import { CartService } from '../services/cart.service';
import { CheckoutService } from '../services/checkout.service';
import { CookieService } from 'ngx-cookie';
import { MessengerService } from 'src/app/services/messenger.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  cart: Cart;
  cartId: string;
  cartItems: Product[] = new Array<Product>();
  tax = 0.14;
  priceTotalBeforeTax: number = 0;
  priceTotalAfterTax: number = 0;

  constructor(private cookieService: CookieService, private cartService:CartService,
              private checkoutService:CheckoutService, private msg: MessengerService) { }

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

  retrieveCart(cartId: string) {
    this.cartService.getCartById(cartId).subscribe(data => {
        this.cart = data;
        this.cart.cartItems === null ? this.cartItems = new Array<Product>() : this.cartItems = this.cart.cartItems;
        this.priceTotalBeforeTax === null ? this.priceTotalBeforeTax = 0 : this.priceTotalBeforeTax = this.cart.priceTotalBeforeTax;
        this.priceTotalAfterTax === null ? this.priceTotalAfterTax = 0 : this.priceTotalAfterTax = this.cart.priceTotalAfterTax;

        console.log("retrieveCart@checkout")
        console.log(this.cart);
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

  handleSubscription() {
    // Add listener to MessengerService
    this.msg.getMsg().subscribe(product => {
      this.retrieveCart(this.cartId);
    });
  }
}
