import { Component, OnInit } from '@angular/core';
import { ApprouteService } from '../services/approute.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn:boolean;
  isAdmin:boolean;
  inOrderManag:boolean;

  constructor(private approute:ApprouteService) { }

  ngOnInit(): void {
    this.isLoggedIn = sessionStorage.getItem('isLoggedIn')=='true';
    this.isAdmin = sessionStorage.getItem('isAdmin')=='true';
    this.inOrderManag = sessionStorage.getItem('inOrderManag')=='true';
  }

  showAdd(){
    let add = sessionStorage.getItem('showAdd');
    if(add === 'true'){
      sessionStorage.setItem('showAdd', 'false');
    } else {
      sessionStorage.setItem('showAdd', 'true');
    }
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
