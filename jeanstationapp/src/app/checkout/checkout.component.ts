import { Component, OnInit } from '@angular/core';
import { Cart } from '../model/Cart';
import { Product } from 'src/app/model/Product';
import { Order } from 'src/app/model/Order';
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

  // Safe-guard: In case user bypass default route
  createCart() {
    // Create an empty cart
    this.cart = JSON.parse('{ "priceTotalBeforeTax" : 0, "priceTotalAfterTax" : 0, "cartItems": [] }');

    this.cartService.createCart(this.cart).subscribe(data => {
      this.cart = data;
      this.cookieService.put("cartId", this.cart.id);
    });
  }

  // Update checkout list as user add item into cart
  handleSubscription() {
    // Add listener to MessengerService
    this.msg.getMsg().subscribe(product => {
      this.retrieveCart(this.cartId);
    });
  }

  removeItemFromCart(item: Product) {
    this.cartItems.find((element, index) => {
      if(item === element) {
        this.cartItems.splice(index, 1);

        this.calculateTotal();
        this.cart.cartItems = this.cartItems;

        this.cartService.updateCart(this.cart).subscribe(data => {
          this.retrieveCart(this.cartId);
        });
      }
    });
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

  checkout() {
    // 1. Convert Cart -> Order
    const order: Order = new Order();

    // 1.1 Get userId if no id from cookie redirect to login
    order.userId = "userId";
    order.priceTotalBeforeTax = this.cart.priceTotalBeforeTax;
    order.priceTotalAfterTax = this.cart.priceTotalAfterTax;
    order.orderItems = this.cart.cartItems;

    // 2. Call createOrder@checkoutService
    this.checkoutService.createOrder(order).subscribe(data => {
      console.log("checkout@checkoutService");
      console.log(data);
    });

    // 3. Redirect to Orders page
  }
}
