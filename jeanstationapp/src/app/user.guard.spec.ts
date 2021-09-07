import { TestBed } from '@angular/core/testing';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { LoginComponent } from './login/login.component';
import { MyorderComponent } from './myorder/myorder.component';
import { ApprouteService } from './services/approute.service';

import { UserGuard } from './user.guard';

describe('UserGuard', () => {
  let guard: UserGuard;
  let routerservice: ApprouteService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports : [RouterTestingModule.withRoutes([
        {path:'myorder', component:MyorderComponent},
        {path:'login', component:LoginComponent}
      ])],
    });
    guard = TestBed.inject(UserGuard);
    routerservice = TestBed.get(ApprouteService);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  it('should hit route if logged in', () => {
    routerservice.isLoggedIn.next(true);
    expect(guard.canActivate(new ActivatedRouteSnapshot(), <RouterStateSnapshot>{url: 'myorder'})).toBe(true);
  });

  it('should not hit route if not logged in', () => {
    routerservice.isLoggedIn.next(false);
    expect(guard.canActivate(new ActivatedRouteSnapshot(), <RouterStateSnapshot>{url: 'myorder'})).toBe(false);
  });
});
