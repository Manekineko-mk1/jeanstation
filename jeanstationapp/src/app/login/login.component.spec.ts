import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { ApprouteService } from '../services/approute.service';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let router: ApprouteService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [HttpClientModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [ LoginComponent ],
      providers: [ApprouteService]
    })
    .compileComponents();
    router = TestBed.get(ApprouteService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have onSubmit()', () => {
    expect(component.onSubmit).toBeTruthy();
  });

  it('onSubmit should print error message if form invalid', () => {
    expect(component.message).toEqual('Username and Password should not be empty!!! Please verify details');
  });

  it('should have openRegistration()', () => {
    expect(component.openRegistration).toBeTruthy();
  });

  it('openRegistration() should call ApprouteService', () => {
    component.openRegistration();
    expect(router.openRegistration).toHaveBeenCalled();
  });
});
