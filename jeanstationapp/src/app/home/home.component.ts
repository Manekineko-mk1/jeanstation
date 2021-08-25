import { Component, OnInit } from '@angular/core';
import { ProductComponent } from '../product/product.component';
import { CartComponent } from '../cart/cart.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  providers: [ProductComponent, CartComponent],
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }



}
