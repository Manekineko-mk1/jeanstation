import { Component, OnInit} from '@angular/core';
import { Cart } from '../model/Cart';
import { Product } from 'src/app/model/Product';
import { Order } from 'src/app/model/Order';
import { CartService } from '../services/cart.service';
import { CookieService } from 'ngx-cookie';
import { MessengerService } from 'src/app/services/messenger.service';
import { ApprouteService } from 'src/app/services/approute.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  cart: Cart;
  cartId: string;
  userId: string;
  cartItems: Product[] = new Array<Product>();
  tax = 0.14;
  priceTotalBeforeTax: number = 0;
  priceTotalAfterTax: number = 0;
  closeModal;

  constructor(private cookieService: CookieService, private cartService:CartService,
              private orderService:OrderService, private msg: MessengerService, private appRouter: ApprouteService,
              private modalService:NgbModal) { }

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

  emptyCart() {
    this.cart.priceTotalBeforeTax = 0;
    this.cart.priceTotalAfterTax = 0;
    this.cart.cartItems = new Array<Product>();

    this.cartService.updateCart(this.cart);
  }

  checkout(content) {
    // 1. Convert Cart -> Order
    const order: Order = new Order();

    // 1.1 Get userId if no id from cookie redirect to login
    // this.userId = this.cookieService.get("userId");
    this.userId = sessionStorage.getItem("username");
    console.log("sessionStorage@checkout");
    console.log(this.userId);

    if(this.userId == null) {
      console.log("UserId not found. Redirect to login page");
      // redirect to login page
      // TODO: Re-enable this once userId login is persistent
      this.appRouter.openLogin();

      // Testing only
      this.userId = "userId";
    } else {
      order.userId = this.userId;
      order.priceTotalBeforeTax = this.cart.priceTotalBeforeTax;
      order.priceTotalAfterTax = this.cart.priceTotalAfterTax;
      order.orderItems = this.cart.cartItems;

      // 2. Call addOrder@orderService
      this.orderService.addOrder(order).subscribe(data => {
        console.log("checkout@orderService");
        console.log(data);
      });

      // 3. Redirect to Orders page
      this.emptyCart();
      this.cookieService.removeAll();
      this.triggerModal(content);
      
    }

    
  }

  triggerModal(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed ${this.getDismissReason(res)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  viewOrder(){
    this.appRouter.openOrder();
    this.modalService.dismissAll();
  }
}
