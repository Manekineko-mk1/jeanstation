import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { ApprouteService } from './services/approute.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private approute:ApprouteService){

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    // if(sessionStorage.getItem('isLoggedIn')=='true' && sessionStorage.getItem('isAdmin')=='true'){
    //   return true;
    // } else {
    //   alert('Access Denied');
    //   this.approute.openLogin();
    //   return false;
    // }
    if(this.approute.isLoggedIn.getValue() && this.approute.isAdmin.getValue()){
      return true;
    } else {
      alert('Access Denied');
      this.approute.openLogin();
      return false;
    }
  }
  
}
