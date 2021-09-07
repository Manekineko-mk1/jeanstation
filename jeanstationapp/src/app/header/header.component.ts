import { Component, OnInit } from '@angular/core';
import { ApprouteService } from '../services/approute.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn:boolean;
  isAdmin:boolean;
  inOrderManag:boolean;
  inProdManag:boolean;

  constructor(private approute:ApprouteService, private authservice:AuthenticationService) {
    // this.isLoggedIn = this.authservice.isUserLoggedIn();
    // this.isAdmin = sessionStorage.getItem('isAdmin')==='true';
    // this.inOrderManag = sessionStorage.getItem('inOrderManag')==='true';
   }

  ngOnInit(): void {
    this.approute.isLoggedIn.subscribe(
      value => {
        this.isLoggedIn = value || this.authservice.isUserLoggedIn();
      }
    );
    this.approute.isAdmin.subscribe(
      value => {
        this.isAdmin = value || (sessionStorage.getItem('isAdmin')==='true');
      }
    );
    this.approute.inOrderManag.subscribe(
      value => {
        this.inOrderManag = value || (sessionStorage.getItem('inOrderManag')==='true');
      }
    );
    this.approute.inProdManag.subscribe(
      value => {
        this.inProdManag = value || (sessionStorage.getItem('inProdManag')==='true');
      }
    )
    // this.isLoggedIn = this.authservice.isUserLoggedIn();
    // this.isAdmin = sessionStorage.getItem('isAdmin')==='true';
    // this.inOrderManag = sessionStorage.getItem('inOrderManag')==='true';
  }

  showAdd(){
    let add = this.approute.showAdd.value;
    this.approute.showAdd.next(!add);
  }

  logout(){
    this.authservice.logOut();
    sessionStorage.setItem('isAdmin', 'false');
    this.approute.openLogin();
  }

  goToOrder(){
    sessionStorage.setItem('inProdManag', 'false');
    sessionStorage.setItem('inOrderManag', 'true');
    this.approute.openOrderManagement();
  }

  goToProduct(){
    sessionStorage.setItem('inOrderManag', 'false');
    sessionStorage.setItem('inProdManag', 'true');
    this.approute.openAdmin();
  }

  goToProfile(){
    sessionStorage.setItem('inOrderManag', 'false');
    sessionStorage.setItem('inProdManag', 'false');
    this.approute.openProfile();
  }

  goToHome(){
    sessionStorage.setItem('inOrderManag', 'false');
    sessionStorage.setItem('inProdManag', 'false');
    this.approute.openHome();
  }

}
