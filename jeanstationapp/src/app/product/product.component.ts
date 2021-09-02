import { Component, OnInit } from '@angular/core';
import { Product } from '../model/Product';
import { Money } from '../model/Money';
import { ApprouteService } from '../services/approute.service';
import { ProductService } from '../services/product.service';
import { FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  form;
  products: Product[] = [];
  productsReceived: Product[];

  // Dependency injection
  constructor(private productservice:ProductService, private approuter:ApprouteService, private formBuilder:FormBuilder) {
    this.form = this.formBuilder.group({
      from: new FormControl('', Validators.required),
      to: new FormControl('', Validators.required)
    })
   }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(){
    this.productservice.getProduct().subscribe(
      data => {
        this.products = new Array<Product>();
        this.productsReceived = data;
        for(const prod of this.productsReceived){
          const prodWithImage = new Product();
          prodWithImage.id = prod.id;
          prodWithImage.name = prod.name;
          if(prod.picture.startsWith('http')){
            prodWithImage.picture = prod.picture;
          } else {
            prodWithImage.picture = 'data:image/jpeg;base64,'+prod.picture;
          }
          prodWithImage.price = prod.price;
          prodWithImage.finalPrice = new Money();

          // Temporary: For testing, the json products didn't include the finalPrice,
          //  but for production, when products are added via ProductService, the finalPrice will be handled by constructor
          prodWithImage.finalPrice.amount = prod.price.amount * (1 - (prod.discount/100));
          prodWithImage.finalPrice.currency = prod.price.currency;
          // End temporary

          prodWithImage.discount = prod.discount;
          prodWithImage.description = prod.description;
          prodWithImage.quantity = prod.quantity;
          prodWithImage.color = prod.color;
          prodWithImage.size = prod.size;
          prodWithImage.categories = prod.categories;
          this.products.push(prodWithImage);
        }
    }
    );
  }

  onSubmit(){
    let filtered = new Array();
    let low = this.form.value.from*100;
    let high = this.form.value.to*100;
    let price;
    let priceFinal;
    //console.log(low+' '+high);
    for(let item of this.products){
      price = item.price.amount;
      priceFinal = item.finalPrice.amount;
      //console.log(price + ' '+ priceFinal + ' '+((price>=low && price<=high) || (priceFinal>=low && priceFinal<=high)));
      if((price>=low && price<=high) || (priceFinal>=low && priceFinal<=high)){
        filtered.push(item);
      }
    }
    this.products = filtered;
  }

  clearFilter(){
    this.getProducts();
  }

  // backToTop(){
  //   document.body.scrollTop = document.documentElement.scrollTop = 0;
  // }
}
