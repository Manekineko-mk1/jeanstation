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
  message;
  products: Product[];
  toAdd = false;
  toUpdate = false;
  product: Product = new Product();
  closeModal:string;
  showProduct: Product;

  constructor(private formBuilder:FormBuilder, private productservice:ProductService, 
    private approute: ApprouteService, private modalService:NgbModal) { 
    this.form = this.formBuilder.group({
      name: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      picture: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
      discount: new FormControl('', Validators.required),
      quantity: new FormControl('', Validators.required),
      size: new FormControl('', Validators.required),
      color: new FormControl('', Validators.required),
      categories: new FormArray([new FormControl()])
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
          this.clearForm();
          this.message = 'Product added';
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
      this.product.name = this.form.value.name;
      this.product.description = this.form.value.description;
      this.product.picture = this.form.value.picture;
      this.product.price = this.form.value.price;
      this.product.discount = this.form.value.discount;
      this.product.quantity = this.form.value.quantity;
      this.product.size = this.form.value.size;
      this.product.color = this.form.value.color;
      this.product.categories = this.form.value.categories;
      this.productservice.updateProduct(this.product).subscribe(
        data => {
          this.toUpdate = false;
          this.clearForm();
          this.message = 'Product updated';
          this.getProducts();
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

  deleteProduct(id:string){
    this.productservice.deleteProduct(id).subscribe(
      data =>{
        this.approute.showAdd.next(false);
        this.message = 'Product deleted';
        this.getProducts();
        
      },
      err => {
        this.message = 'Failed to delete product!';
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
    this.form.get('name').setValue(this.product.name);
    this.form.get('description').setValue(this.product.description);
    this.form.get('picture').setValue(this.product.picture);
    this.form.get('price').setValue(this.product.price);
    this.form.get('discount').setValue(this.product.discount);
    this.form.get('quantity').setValue(this.product.quantity);
    this.form.get('size').setValue(this.product.size);
    this.form.get('color').setValue(this.product.color);
    this.form.get('categories').setValue(this.product.categories);
  }

  addCategory(){
    this.form.categories = this.form.get('categories') as FormArray;
    this.form.categories.push(new FormControl());
  }

  deleteCategory(index:number){
    this.form.categories = this.form.get('categories') as FormArray;
    this.form.categories.removeAt(index);
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
