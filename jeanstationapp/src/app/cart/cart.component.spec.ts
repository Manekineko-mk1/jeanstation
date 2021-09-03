import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { CookieOptionsProvider, CookieService, CookieWriterService, COOKIE_OPTIONS, COOKIE_WRITER } from 'ngx-cookie';
import { Product } from '../model/Product';
import { CartService } from '../services/cart.service';
import { MessengerService } from '../services/messenger.service';

import { CartComponent } from './cart.component';

describe('CartComponent', () => {
  let component: CartComponent;
  let fixture: ComponentFixture<CartComponent>;
  let cookieservice: CookieService;
  let msg: MessengerService;
  let cartservice: CartService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [HttpClientModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [ CartComponent ],
      providers : [CookieService, MessengerService, CartService, CartComponent, CookieOptionsProvider, 
        {provide:COOKIE_OPTIONS, useValue:CookieOptionsProvider}, {provide: COOKIE_WRITER, useValue:CookieOptionsProvider}]
    })
    .compileComponents();
    component = TestBed.get(CartComponent);
    cookieservice = TestBed.get(CookieService);
    msg = TestBed.get(MessengerService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });

  // it('should have ngOnInit()', () => {
  //   expect(component.ngOnInit).toBeTruthy();
  // });

  // it('ngOnInit() should retrieve cart if cart already exists', () => {
  //   spyOn(cookieservice, 'get').and.returnValue('A1');
  //   spyOn(component, 'retrieveCart');
  //   component.ngOnInit();
  //   expect(cookieservice.get).toHaveBeenCalled();
  //   expect(component.retrieveCart).toHaveBeenCalled();
  // });

  // it('ngOnInit() should create cart if cart does not exist', () => {
  //   spyOn(cookieservice, 'get').and.returnValue(null);
  //   spyOn(component, 'createCart');
  //   component.ngOnInit();
  //   expect(cookieservice.get).toHaveBeenCalled();
  //   expect(component.createCart).toHaveBeenCalled();
  // });

  // it('should have handleSubscription()', () => {
  //   expect(component.handleSubscription).toBeTruthy();
  // });

  // it('handleSubscription() should get message from messenger', () => {
  //   spyOn(msg, 'getMsg');
  //   component.handleSubscription();
  //   expect(msg.getMsg).toHaveBeenCalled();
  // })

  // it('should have retrieveCart()', () => {
  //   expect(component.retrieveCart).toBeTruthy();
  // });

  // it('retrieveCart() should call cartservice.getCartById', () => {
  //   spyOn(cartservice, 'getCartById');
  //   component.retrieveCart('A1');
  //   expect(cartservice.getCartById).toHaveBeenCalled();
  // });

  // it('should have createCart()', () => {
  //   expect(component.createCart).toBeTruthy();
  // });

  // it('createCart() should call cartservice.createCart', () => {
  //   spyOn(cartservice, 'createCart');
  //   component.createCart();
  //   expect(cartservice.createCart).toHaveBeenCalled();
  // });

  // it('should have addItemToCart()', () => {
  //   expect(component.addItemToCart).toBeTruthy();
  // });

  // it('addItemToCart() should add an item', () => {
  //   const product1: Product = {
  //     name: 'New Product',
  //     description: 'The description',
  //     //picture: 'picture',
  //     //price: 120,
  //     discount: 0.05,
  //     quantity: 56,
  //     size: 'L',
  //     color: 'BLACK',
  //     categories: ['Men', 'Jacket']
  //   };
  //   component.addItemToCart(product1);
  //   expect(component.cartItems.length).toEqual(1);
  // });

  // it('addItemToCart() should not add an item if it already exists', () => {
  //   const product1: Product = {
  //     name: 'New Product',
  //     description: 'The description',
  //     //picture: 'picture',
  //     //price: 120,
  //     discount: 0.05,
  //     quantity: 56,
  //     size: 'L',
  //     color: 'BLACK',
  //     categories: ['Men', 'Jacket']
  //   };
  //   component.addItemToCart(product1);
  //   component.addItemToCart(product1);
  //   expect(component.cartItems.length).toEqual(1);
  // });

  // it('should have updateCart()', () => {
  //   expect(component.updateCart).toBeTruthy();
  // });

  // it('updateCart() should call cartservice.updateCart', () => {
  //   spyOn(cartservice, 'updateCart');
  //   component.updateCart();
  //   expect(cartservice.updateCart).toHaveBeenCalled();
  // });

  // it('should have emptyCart()', () => {
  //   expect(component.emptyCart).toBeTruthy();
  // });

  // it('emptyCart() should set price to zero', () => {
  //   component.emptyCart();
  //   expect(component.cart.priceTotalAfterTax).toEqual(0);
  // });

  // it('should have calculateTotal()', () => {
  //   expect(component.calculateTotal).toBeTruthy();
  // });

  // it('calculateTotal() should calculate the total price', () => {
  //   const product1: Product = {
  //     name: 'New Product',
  //     description: 'The description',
  //     price: {
  //       amount: 100,
  //       currency: 'CAD'
  //     },
  //     discount: 0,
  //     quantity: 56,
  //     size: 'L',
  //     color: 'BLACK',
  //     categories: ['Men', 'Jacket']
  //   };
  //   component.addItemToCart(product1);
  //   component.calculateTotal();
  //   expect(component.priceTotalBeforeTax).toEqual(100);
  // });
});
