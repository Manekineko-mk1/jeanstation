import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

import { ApprouteService } from './approute.service';

describe('ApprouteService', () => {
  let service:ApprouteService;
  let router:Router;
  beforeEach(() => {
    
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule, RouterTestingModule],
      providers: [ApprouteService]
    });
    service = TestBed.get(ApprouteService);
    router = TestBed.get(Router);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should have openLogin()', () => {
    expect(service.openLogin).toBeTruthy();
  });

  it('openLogin() should call router.navigate', () => {
    service.openLogin();
    expect(router.navigate).toHaveBeenCalled();
  });

  it('should have openHome()', () => {
    expect(service.openHome).toBeTruthy();
  });

  it('openHome() should call router.navigate', () => {
    service.openHome();
    expect(router.navigate).toHaveBeenCalled();
  });

  it('should have openProduct()', () => {
    expect(service.openProduct).toBeTruthy();
  });

  it('openProduct() should call router.navigate', () => {
    service.openProduct();
    expect(router.navigate).toHaveBeenCalled();
  });

  it('should have openCart()', () => {
    expect(service.openCart).toBeTruthy();
  });

  it('openCart() should call router.navigate', () => {
    service.openCart();
    expect(router.navigate).toHaveBeenCalled();
  });

  it('should have openAdmin()', () => {
    expect(service.openAdmin).toBeTruthy();
  });

  it('openAdmin() should call router.navigate', () => {
    service.openAdmin();
    expect(router.navigate).toHaveBeenCalled();
  });

  it('should have openOrderManagement()', () => {
    expect(service.openOrderManagement).toBeTruthy();
  });

  it('openOrderManagement() should call router.navigate', () => {
    service.openOrderManagement();
    expect(router.navigate).toHaveBeenCalled();
  });

  it('should have openPassword()', () => {
    expect(service.openPassword).toBeTruthy();
  });

  it('openPassword() should call router.navigate', () => {
    service.openPassword();
    expect(router.navigate).toHaveBeenCalled();
  });

  it('should have openRegistration()', () => {
    expect(service.openRegistration).toBeTruthy();
  });

  it('openRegistration() should call router.navigate', () => {
    service.openRegistration();
    expect(router.navigate).toHaveBeenCalled();
  });
});
