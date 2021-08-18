import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ApprouteService {

  isLoggedIn = false;
  isAdmin = false;

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
}
