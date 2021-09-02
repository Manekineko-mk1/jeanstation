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
        this.isLoggedIn = value;
      }
    );
    this.approute.isAdmin.subscribe(
      value => {
        this.isAdmin = value;
      }
    );
    this.approute.inOrderManag.subscribe(
      value => {
        this.inOrderManag = value;
      }
    );
    this.approute.inProdManag.subscribe(
      value => {
        this.inProdManag = value;
      }
    )
    // this.isLoggedIn = this.authservice.isUserLoggedIn();
    // this.isAdmin = sessionStorage.getItem('isAdmin')==='true';
    // this.inOrderManag = sessionStorage.getItem('inOrderManag')==='true';
  }

  showAdd(){
    let add = this.approute.showAdd.value;
    this.approute.showAdd.next(!add);
    // let add = sessionStorage.getItem('showAdd');
    // if(add === 'true'){
    //   sessionStorage.setItem('showAdd', 'false');
    // } else {
    //   sessionStorage.setItem('showAdd', 'true');
    // }
  }

  // toOrderManagement(){
  //   this.inOrderManag=true;
  //   this.approute.openOrderManagement();
  // }

  // toProductManagement(){
  //   this.inOrderManag=false;
  //   this.approute.openAdmin();
  // }

}
