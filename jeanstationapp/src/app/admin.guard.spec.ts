import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

import { AdminGuard } from './admin.guard';
import { ApprouteService } from './services/approute.service';

describe('AdminGuard', () => {
  let guard: AdminGuard;
  let routerservice: ApprouteService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports : [ RouterTestingModule],
    });
    guard = TestBed.inject(AdminGuard);
    routerservice = TestBed.get(ApprouteService);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  it('should hit route if logged in and is admin', () => {
    routerservice.isAdmin.next(true);
    routerservice.isLoggedIn.next(true);
    expect(guard.canActivate(new ActivatedRouteSnapshot(), <RouterStateSnapshot>{url: 'testUrl'})).toBe(true);
  });

  it('should not hit route if not logged in or is not admin', () => {
    routerservice.isAdmin.next(false);
    expect(guard.canActivate(new ActivatedRouteSnapshot(), <RouterStateSnapshot>{url: 'testUrl'})).toBe(false);
  });
});
