import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { ApprouteService } from './services/approute.service';
import { AuthenticationService } from './services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {

  constructor(private approute:ApprouteService, private authservice:AuthenticationService){

  }
  
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      // if(sessionStorage.getItem('isLoggedIn')=='true'){
      //   return true;
      // } else {
      //   alert('Access Denied');
      //   this.approute.openLogin();
      //   return false;
      // }
      if(this.approute.isLoggedIn.getValue()||this.authservice.isUserLoggedIn()){
        return true;
      } else {
        alert('Access Denied');
        this.approute.openLogin();
        return false;
      }
  }
  
}
