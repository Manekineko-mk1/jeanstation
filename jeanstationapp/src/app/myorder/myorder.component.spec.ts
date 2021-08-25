import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { OrderService } from '../services/order.service';

import { MyorderComponent } from './myorder.component';

describe('MyorderComponent', () => {
  let component: MyorderComponent;
  let fixture: ComponentFixture<MyorderComponent>;
  let service: OrderService;
  beforeEach(async () => {
    
    await TestBed.configureTestingModule({
      imports : [HttpClientModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [ MyorderComponent ],
      providers : [OrderService]
    })
    .compileComponents();
    service = TestBed.get(OrderService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyorderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('ngOninit() should exist', () => {
    expect(component.ngOnInit).toBeTruthy();
  });

  it('getOrders() should exist', () => {
    expect(component.getOrders).toBeTruthy();
  });

  it('getOrders() should called getOrderByUserId', () => {
    spyOn(service, 'getOrderByUserId').and.callThrough();
    component.getOrders();
    expect(service.getOrderByUserId).toHaveBeenCalled();
  })
});
