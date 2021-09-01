import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { Order } from '../model/Order';
import { OrderService } from '../services/order.service';

import { OrderManagementComponent } from './order-management.component';

const order1:Order = {
  priceTotalBeforeTax: 120,
  userId: 'A1',
  deliveryDate: '2021-08-26',
  creationDate: '2021-08-23',
  status: 'SUBMITTED',
  //orderItems : ['Item1', 'Item2'] 
}

describe('OrderManagementComponent', () => {
  let component: OrderManagementComponent;
  let fixture: ComponentFixture<OrderManagementComponent>;
  let orderservice: OrderService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [HttpClientModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [ OrderManagementComponent ],
      providers: [OrderService]
    })
    .compileComponents();
    orderservice = TestBed.get(OrderService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have ngOnInit()', () => {
    expect(component.ngOnInit).toBeTruthy();
  });

  it('ngOnInit() should call getOrders', () => {
    component.ngOnInit();
    expect(component.getOrders).toHaveBeenCalled();
  });

  it('should have getOrders()', () => {
    expect(component.getOrders).toBeTruthy();
  });

  it('getOrders() should call orderservice.getOrder()', () => {
    const list = [order1];
    spyOn(orderservice, 'getOrder').and.returnValue(of(list));
    component.getOrders();
    expect(orderservice.getOrder).toHaveBeenCalled();
  });

  it('should have updateStatus()', () => {
    expect(component.updateStatus).toBeTruthy();
  });

  it('updateStatus should call updateOrder', () => {
    spyOn(orderservice, 'updateOrder').and.returnValue(of(order1));
    component.form.get('status').setValue("SUBMITTED");
    component.updateStatus();
    expect(orderservice.updateOrder).toHaveBeenCalled();
  });

  it('should have changeStatus()', () => {
    expect(component.changeStatus).toBeTruthy();
  });

  it('should have triggerModal()', () => {
    expect(component.triggerModal).toBeTruthy();
  })
});
