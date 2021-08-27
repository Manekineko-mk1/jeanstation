import { Component, OnInit } from '@angular/core';
import { Cart } from '../model/Cart';
import { Product } from 'src/app/model/Product'
import { ApprouteService } from '../services/approute.service';
import { CartService } from '../services/cart.service';
import { MessengerService } from 'src/app/services/messenger.service';
import { CookieService } from 'ngx-cookie';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cart:Cart;
  cartId:string;
  cartItems: Product[];
  tax=0.14;
  priceTotalBeforeTax;
  priceTotalAfterTax;

  constructor(private cartservice:CartService, private approuter:ApprouteService,
              private msg: MessengerService, private cookieService: CookieService) { }

  ngOnInit(): void {
    this.cartId = this.cookieService.get("cartId");

    // Retrieve Cart from cartID exists in cookie ELSE create a new Cart
    if (this.cartId != null) {
      console.log("cartId found | cartId: " + this.cartId);
      this.retrieveCart(this.cartId);
    } else {
      console.log("cartId not found | this.initCart()");
      this.initCart();
    }

    // Add listener to MessengerService
    this.msg.getMsg().subscribe(product => {
      this.calculateTotal();
      this.addItemToCart(product);
    });

    console.log('cartComponent initialized');
  }

  initCart() {
    // Initialization
    this.priceTotalBeforeTax = 0;
    this.priceTotalAfterTax = 0;
    this.cartItems = new Array<Product>();

    // Create an empty cart
    this.cart = JSON.parse('{ "priceTotalBeforeTax" : 0, "priceTotalAfterTax" : 0, "cartItems": [] }');
    this.createCart(this.cart);

    console.log('cartComponent initCart()');
  }

  retrieveCart(cartId: string) {
    this.cartservice.getCartById(cartId).subscribe(data => {
        console.log("retrieveCart: | cartID: " + cartId);
        console.log(data);
        this.cart = data;
        this.cart.cartItems === null ? this.cartItems = new Array<Product>() : this.cartItems = this.cart.cartItems;
        this.priceTotalBeforeTax === null ? this.priceTotalBeforeTax = 0 : this.priceTotalBeforeTax = this.cart.priceTotalBeforeTax;
        this.priceTotalAfterTax === null ? this.priceTotalAfterTax = 0 : this.priceTotalAfterTax = this.cart.priceTotalAfterTax;
        console.log("retrieveCart: | this.cartItems");
        console.log(this.cartItems);
    });
  }

  createCart(cart: Cart) {
    this.cartservice.createCart(cart).subscribe(data => {
        this.cart = data;
        console.log(this.cart);
        this.cookieService.put("cartId", this.cart.id);
        console.log("cookie set @createCart() | cartId: " + this.cart.id);

        return data;
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
    this.cart.priceTotalBeforeTax = this.priceTotalBeforeTax;
    this.cart.priceTotalAfterTax = this.priceTotalAfterTax;

    console.log("addItemToCart | this.cart.priceTotalBeforeTax " + this.cart.priceTotalBeforeTax);
    console.log("addItemToCart | this.cart.priceTotalAfterTax " + this.cart.priceTotalAfterTax);

    this.updateCart(this.cart);
  }

  updateCart(cart: Cart) {
    this.cartservice.updateCart(cart).subscribe(data => {
        console.log("updateCart");
        console.log(data);
        this.cart = data;
    });
  }

  emptyCart() {
    this.cart.priceTotalBeforeTax = 0;
    this.cart.priceTotalAfterTax = 0;
    this.cart.cartItems = new Array<Product>();

    this.cartservice.updateCart(this.cart);
  }

  deleteCart() {
      this.cartservice.deleteCart(this.cart.id).subscribe(data => {
          console.log('DeleteCart succeed: ' + data);
      });
  }

  calculateTotal() {
    this.cartItems.forEach(item => {
      this.priceTotalBeforeTax = this.priceTotalBeforeTax + (item.quantity * item.price);
    });

    this.priceTotalAfterTax = this.priceTotalBeforeTax + (this.priceTotalAfterTax * this.tax);
  }
}
