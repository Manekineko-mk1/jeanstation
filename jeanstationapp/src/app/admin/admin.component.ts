import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Product } from '../model/Product';
import { ApprouteService } from '../services/approute.service';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  form;
  message = "";
  products: Product[];
  toAdd = false;
  toUpdate = false;

  constructor(private formBuilder:FormBuilder, private productservice:ProductService, private approute: ApprouteService) { 
    this.form = this.formBuilder.group({
      id: new FormControl('', Validators.required),
      productname: new FormControl('', Validators.required),
      qty: new FormControl('', Validators.required)
    });
  }

  ngOnInit(): void {
    this.getProducts();
    
  }

  getProducts(){
    this.productservice.getProduct().subscribe(
      data => {
      this.products = data;
    }
    );
  }

  onSubmit(){

    if (this.form.valid) {
      this.productservice.addProduct(this.form.value).subscribe(
        data => {
          this.message = 'Product added';
          this.clearForm();
          this.getProducts();
        },
        err => {
          this.message = 'Failed to add Product!!';
          this.clearForm();
        }
      );
    } else {
      this.message = 'The fields should not be empty!!! Please verify details';
    }

  }

  clearForm(){
    this.form.reset();
  }

  logout(){
    this.approute.openHome();
  }

  showAdd(){
    this.toAdd = true;
  }

}
