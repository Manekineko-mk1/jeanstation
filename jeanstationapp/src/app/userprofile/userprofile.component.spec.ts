import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { Address } from '../model/Address';
import { User } from '../model/User';
import { UserService } from '../services/user.service';

import { UserprofileComponent } from './userprofile.component';

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

describe('UserprofileComponent', () => {
  let component: UserprofileComponent;
  let fixture: ComponentFixture<UserprofileComponent>;
  let userservice: UserService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [HttpClientModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [ UserprofileComponent ],
      providers: [UserService]
    })
    .compileComponents();
    userservice = TestBed.get(UserService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component.user = user1;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have ngOnInit()', () => {
    expect(component.ngOnInit).toBeTruthy();
  });

  it('ngOnInit() should call getUser', () => {
    component.ngOnInit();
    expect(component.getUser).toHaveBeenCalled();
  });

  it('getUser() should return user', () => {
    spyOn(userservice, 'getUserById').and.returnValue(of(user1));
    component.getUser();
    expect(userservice.getUserById).toHaveBeenCalled();
  });

  it('should have showUpdate()', () => {
    expect(component.showUpdate).toBeTruthy();
  });

  it('showUpdate should set update', () => {
    component.showUpdate();
    expect(component.update).toBeTrue();
  });

  it('should have updateUser()', () => {
    expect(component.updateUser).toBeTruthy();
  });

  it('should have clearForm()', () => {
    expect(component.clearForm).toBeTruthy();
  });

  it('should have changePassword()', () => {
    expect(component.changePassword).toBeTruthy();
  });
});
