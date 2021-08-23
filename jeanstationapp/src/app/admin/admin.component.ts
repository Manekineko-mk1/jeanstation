import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
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
  product: Product = new Product();
  closeModal:string;
  showProduct: Product;

  constructor(private formBuilder:FormBuilder, private productservice:ProductService, 
    private approute: ApprouteService, private modalService:NgbModal) { 
    this.form = this.formBuilder.group({
      productName: new FormControl('', Validators.required),
      productDescription: new FormControl('', Validators.required),
      picture: new FormControl('', Validators.required),
      priceCAD: new FormControl('', Validators.required),
      discount: new FormControl('', Validators.required),
      quantity: new FormControl('', Validators.required),
      productSize: new FormControl('', Validators.required),
      productColor: new FormControl('', Validators.required),
      productCategories: new FormArray([new FormControl()])
    });
  }

  ngOnInit(): void {
    this.approute.inOrderManag.next(false);
    this.getProducts();
    this.approute.showAdd.subscribe(
      value => {
        this.toAdd = value;
        this.toUpdate=false;
        this.clearForm();
      }
    )
  }

  getProducts(){
    this.productservice.getProduct().subscribe(
      data => {
      this.products = data;
    }
    );
  }

  addProduct(){

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

  updateProduct(){

    if (this.form.valid) {
      this.product.productName = this.form.value.productName;
      this.product.productDescription = this.form.value.productDescription;
      this.product.picture = this.form.value.picture;
      this.product.priceCAD = this.form.value.priceCAD;
      this.product.discount = this.form.value.discount;
      this.product.quantity = this.form.value.quantity;
      this.product.productSize = this.form.value.productSize;
      this.product.productColor = this.form.value.productColor;
      this.product.productCategories = this.form.value.productCategories;
      this.productservice.updateProduct(this.product).subscribe(
        data => {
          this.message = 'Product updated';
          this.clearForm();
          this.getProducts();
          this.toUpdate = false;
        },
        err => {
          this.message = 'Failed to update Product!!';
          this.clearForm();
        }
      );
    } else {
      this.message = 'The fields should not be empty!!! Please verify details';
    }

  }

  deleteProduct(id:number){
    this.productservice.deleteProduct(id).subscribe(
      data =>{
        this.message = "Product deleted";
        this.getProducts();
      },
      err => {
        this.message = "Failed to delete product!";
      }
    );
  }

  clearForm(){
    this.form.reset();
    this.message = '';
  }

  logout(){
    this.approute.openHome();
  }

  showUpdate(product:Product){
    this.toUpdate = true;
    this.toAdd = false;
    this.message = '';
    this.product = product;
    let top = document.getElementById('Dashboard');
    if(top!=null){
      top.scrollIntoView();
      top = null;
    }
    this.form.get('productName').setValue(this.product.productName);
    this.form.get('productDescription').setValue(this.product.productDescription);
    this.form.get('picture').setValue(this.product.picture);
    this.form.get('priceCAD').setValue(this.product.priceCAD);
    this.form.get('discount').setValue(this.product.discount);
    this.form.get('quantity').setValue(this.product.quantity);
    this.form.get('productSize').setValue(this.product.productSize);
    this.form.get('productColor').setValue(this.product.productColor);
    this.form.get('productCategories').setValue(this.product.productCategories);
  }

  addCategory(){
    this.form.productCategories = this.form.get('productCategories') as FormArray;
    this.form.productCategories.push(new FormControl());
  }

  deleteCategory(index:number){
    this.form.productCategories = this.form.get('productCategories') as FormArray;
    this.form.productCategories.removeAt(index);
  }

  triggerModal(content, product) {
    this.showProduct = product;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed ${this.getDismissReason(res)}`;
    });
  }
  
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

}
