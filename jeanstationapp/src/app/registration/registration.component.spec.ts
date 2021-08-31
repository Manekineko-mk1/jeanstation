import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { Address } from '../model/Address';
import { User } from '../model/User';
import { ApprouteService } from '../services/approute.service';
import { UserService } from '../services/user.service';

import { RegistrationComponent } from './registration.component';

const address:Address = {
  doorNumber : 100,
  street : 'Streetname',
  city: 'Montreal',
  country : 'Canada',
  postalCode : 'H5R0T3'
}

const user1:User = {
  username : 'NewUser',
  userRole : 'USER',
  userStatus : 'ACTIVE',
  creationDate : '2021-08-30',
  realName : 'Real Name',
  address : address,
  telephone : '123-456-7890'
}

describe('RegistrationComponent', () => {
  let component: RegistrationComponent;
  let fixture: ComponentFixture<RegistrationComponent>;
  let userservice: UserService;
  let router: ApprouteService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [HttpClientModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [ RegistrationComponent ]
    })
    .compileComponents();
    userservice = TestBed.get(UserService);
    router = TestBed.get(ApprouteService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have onSubmit()', () => {
    expect(component.onSubmit).toBeTruthy();
  });

  it('onSubmit() should return error message if form is invalid', () => {
    component.onSubmit();
    expect(component.message).toEqual('Fields cannot be empty! Please verify provided information');
  });

  it('onSubmit() should go to login page if addUser is successful', () => {
    component.form.clearValidators();
    component.form.updateValueAndValidity();
    spyOn(userservice, 'addUser').and.returnValue(of(user1));
    component.onSubmit();
    expect(userservice.addUser).toHaveBeenCalled();
    expect(router.openLogin).toHaveBeenCalled();
  });

  it('onSubmit() should return error message if addUser is not successful', () => {
    component.form.clearValidators();
    component.form.updateValueAndValidity();
    spyOn(userservice, 'addUser').and.returnValue(throwError({status:404}));
    component.onSubmit();
    expect(userservice.addUser).toHaveBeenCalled();
    expect(component.message).toEqual('Registration failed!');
  });

});
