import { Component, OnInit } from '@angular/core';
import { ApprouteService } from '../services/approute.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn = false;

  constructor(private approute:ApprouteService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.approute.isLoggedIn;
  }

}
