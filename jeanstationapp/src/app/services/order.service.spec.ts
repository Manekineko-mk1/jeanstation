import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Order } from '../model/Order';

import { OrderService } from './order.service';

const order1:Order = {
  priceTotal: 120,
  userId: 'A1',
  deliveryDate: '2021-08-26',
  creationDate: '2021-08-23',
  status: 'SUBMITTED',
  orderItems : ['Item1', 'Item2'] 
}

describe('OrderService', () => {
  let service: OrderService;
  let httpMock: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers: [OrderService]
    });
    httpMock = TestBed.get(HttpTestingController);
    service = TestBed.get(OrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should have getOrder()', () =>{
    expect(service.getOrder).toBeTruthy();
  });

  it('should have getOrderById()', () =>{
    expect(service.getOrderById).toBeTruthy();
  });

  it('should have addOrder()', () =>{
    expect(service.addOrder).toBeTruthy();
  });

  it('should have updateOrder()', () =>{
    expect(service.updateOrder).toBeTruthy();
  });

  it('should have deleteOrder()', () =>{
    expect(service.deleteOrder).toBeTruthy();
  });

  it('getOrder() method should return list of Orders', () => {
    service.getOrder().subscribe();
    const req = httpMock.expectOne('http://localhost:8080/order/api/v1/orders');
    expect(req.request.method).toEqual('GET');
  });

  it('getOrderById() method should return respective Order', () => {
    service.getOrderById(order1.id).subscribe();
    const req = httpMock.expectOne('http://localhost:8080/order/api/v1/orders/'+order1.id);
    expect(req.request.method).toEqual('GET');
  });

  it('addOrder() method should add Order', () => {
    service.addOrder(order1).subscribe(
      data => {
        expect(data.userId).toEqual('New Order');
      }
    );
    const req = httpMock.expectOne('http://localhost:8080/order/api/v1/orders');
    expect(req.request.method).toEqual('POST');
    expect(req.request.headers.get('Content-Type')).toEqual('application/json');
  });

  it('updateOrder() method should update Order', () => {
    order1.userId = 'A2';
    service.updateOrder(order1).subscribe(
      data => {
        expect(data.userId).toEqual('A2');
      }
    );
    const req = httpMock.expectOne('http://localhost:8080/order/api/v1/orders');
    expect(req.request.method).toEqual('PUT');
    expect(req.request.headers.get('Content-Type')).toEqual('application/json');
  });

  it('deleteOrder() method should delete Order', () => {
    service.deleteOrder(order1.id).subscribe();
    const req = httpMock.expectOne('http://localhost:8080/order/api/v1/orders/'+order1.id);
    expect(req.request.method).toEqual('DELETE');
  });

  it('getOrderByUserId() method should return respective Order', () => {
    service.getOrderByUserId(order1.userId).subscribe();
    const req = httpMock.expectOne('http://localhost:8080/order/api/v1/orders/user/'+order1.userId);
    expect(req.request.method).toEqual('GET');
  });

  afterEach( () => {
    httpMock.verify();
  });
});
