import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';

import { OrderManagementComponent } from './order-management.component';

describe('OrderManagementComponent', () => {
  let component: OrderManagementComponent;
  let fixture: ComponentFixture<OrderManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [HttpClientModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [ OrderManagementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
