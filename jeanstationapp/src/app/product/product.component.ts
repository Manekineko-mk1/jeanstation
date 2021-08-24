import { Component, OnInit } from '@angular/core';
import { Product } from '../model/Product';
import { ApprouteService } from '../services/approute.service';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[] = [];

  // Dependency injection
  constructor(private productservice:ProductService, private approuter:ApprouteService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(){
    this.productservice.getProduct().subscribe(
      data => {
        this.products = data;
      }
    )
  }
}
