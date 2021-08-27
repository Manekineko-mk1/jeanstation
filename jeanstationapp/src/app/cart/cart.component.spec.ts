import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';

import { CartComponent } from './cart.component';

describe('CartComponent', () => {
  let component: CartComponent;
  let fixture: ComponentFixture<CartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [HttpClientModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [ CartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have ngOnInit()', () => {
    expect(component.ngOnInit).toBeTruthy();
  });

  it('should have initCart()', () => {
    expect(component.initCart).toBeTruthy();
  });

  it('should have retrieveCart()', () => {
    expect(component.retrieveCart).toBeTruthy();
  });

  it('should have createCart()', () => {
    expect(component.createCart).toBeTruthy();
  });

  it('should have addItemToCart()', () => {
    expect(component.addItemToCart).toBeTruthy();
  });

  it('should have updateCart()', () => {
    expect(component.updateCart).toBeTruthy();
  });

  it('should have emptyCart()', () => {
    expect(component.emptyCart).toBeTruthy();
  });

  it('should have deleteCart()', () => {
    expect(component.deleteCart).toBeTruthy();
  });

  it('should have calculateTotal()', () => {
    expect(component.calculateTotal).toBeTruthy();
  });
});
