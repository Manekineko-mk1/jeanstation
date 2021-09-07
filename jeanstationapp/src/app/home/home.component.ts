import { Component, OnInit } from '@angular/core';
import { ProductComponent } from '../product/product.component';
import { CartComponent } from '../cart/cart.component';
import { ApprouteService } from '../services/approute.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  providers: [ProductComponent, CartComponent],
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private approute:ApprouteService) { }

  ngOnInit(): void {
    this.approute.inOrderManag.next(false);
    this.approute.inProdManag.next(false);
  }



}
