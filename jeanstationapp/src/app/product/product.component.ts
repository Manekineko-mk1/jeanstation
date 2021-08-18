import { Component, OnInit } from '@angular/core';
import { Product } from '../model/Product';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  message:string;
  products:Product[];

  constructor(private productservice:ProductService) { }

  ngOnInit(): void {
  }

  getProducts(){
    this.productservice.getProduct().subscribe(
      data => {
        this.products = data;
      },
      err => {
        this.message = 'Failed to get the products! Please try again later.';
      }
    )
  }

}
