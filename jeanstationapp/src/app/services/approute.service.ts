import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApprouteService {

  public isLoggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public isAdmin: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public showAdd: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  //public inOrderManag: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private router:Router) { }

  openLogin(){
    this.router.navigate(['login']);
  }

  openHome(){
    this.router.navigate(['']);
  }

  openProduct(){
    this.router.navigate(['product']);
  }

  openCart(){
    this.router.navigate(['cart']);
  }

  openAdmin(){
    this.router.navigate(['admin']);
  }

  openOrderManagement(){
    this.router.navigate(['order-management']);
  }

}
