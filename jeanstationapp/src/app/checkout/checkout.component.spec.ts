import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { CookieOptionsProvider, CookieService, CookieWriterService, COOKIE_OPTIONS, COOKIE_WRITER } from 'ngx-cookie';
import { MessengerService } from '../services/messenger.service';
import { OrderService } from '../services/order.service';

import { CheckoutComponent } from './checkout.component';

describe('CheckoutComponent', () => {
  let component: CheckoutComponent;
  let fixture: ComponentFixture<CheckoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [HttpClientModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [ CheckoutComponent ],
      providers: [CookieService, OrderService, MessengerService, CookieOptionsProvider, 
        {provide:COOKIE_OPTIONS, useValue:CookieOptionsProvider}, {provide: COOKIE_WRITER, useValue:CookieOptionsProvider}]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
