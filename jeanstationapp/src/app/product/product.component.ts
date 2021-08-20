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

  products:Product[];

  constructor(private productservice:ProductService, private approuter:ApprouteService) { }

  ngOnInit(): void {
    // this.products = new Array();
    // let p = new Product();
    // p.discount = 0.1;
    // p.id = 1;
    // p.picture = "http://staffmobility.eu/sites/default/files/isewtweetbg.jpg";
    // p.priceCAD = 20.00;
    // p.productCategories = ["something"];
    // p.productDescription = "the description";
    // p.productName = "some";
    // p.quantity = 10;
    // this.products.push(p);
    this.getProducts();
  }

  getProducts(){
    this.productservice.getProduct().subscribe(
      data => {
        this.products = data;
      }
    )


  }

  addToCart(){
    this.approuter.openCart();
  }
  

}
