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
    )
  }

  showAdd(){
    let add = this.approute.showAdd.value;
    this.approute.showAdd.next(!add);
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
