import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Money } from '../model/Money';
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
  productsReceived: Product[];
  toAdd = false;
  toUpdate = false;
  product: Product = new Product();
  closeModal:string;
  showProduct: Product;
  private selectedFile;
  imgURL: any;
  showchangeImage: boolean = false;

  constructor(private formBuilder:FormBuilder, private productservice:ProductService, 
    private approute: ApprouteService, private modalService:NgbModal) { 
    this.form = this.formBuilder.group({
      name: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      picture: new FormControl(''),
      price: new FormControl('', Validators.required),
      discount: new FormControl('', Validators.required),
      quantity: new FormControl('', Validators.required),
      size: new FormControl(''),
      color: new FormControl(''),
      categories: new FormArray([new FormControl()])
    });
  }

  ngOnInit(): void {
    sessionStorage.setItem('inOrderManag', 'false')
    this.getProducts();
    if(sessionStorage.getItem('showAdd')=='true'){
      this.toAdd = true;
    }
    this.toUpdate=false;
    this.clearForm();
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
          } else if (prod.picture.startsWith('data:image/jpeg;base64,')) {
            prodWithImage.picture = prod.picture;
          } else {
            prodWithImage.picture = 'data:image/jpeg;base64,'+prod.picture;
          }
          prodWithImage.price = prod.price;
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

  addProduct(){

    if (this.form.valid) {
      const uploadData = new FormData();
      uploadData.append('imageFile', this.selectedFile, this.selectedFile.name);
      this.selectedFile.imageName = this.selectedFile.name;
      const prodWithImage = new Product();
      prodWithImage.name = this.form.value.name;
      prodWithImage.picture = this.form.value.picture;
      prodWithImage.price = new Money();
      prodWithImage.price.amount = this.form.value.price;
      prodWithImage.price.currency = 'CAD';
      prodWithImage.discount = this.form.value.discount;
      prodWithImage.description = this.form.value.description;
      prodWithImage.quantity = this.form.value.quantity;
      prodWithImage.color = this.form.value.color;
      prodWithImage.size = this.form.value.size;
      prodWithImage.categories = this.form.value.categories;
      this.productservice.uploadImage(uploadData).subscribe(
        data => {
          this.productservice.addProduct(prodWithImage).subscribe(
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
        },
        err => {
          this.message = 'Image upload failed';
        }
      );
     
    } else {
      this.message = 'The fields should not be empty!!! Please verify details';
    }

  }

  updateProduct(){
    if(this.showchangeImage){
      this.updateWithImage();
    }else {
      this.updateWithoutImage();
    }
  }

  updateWithImage(){
    if (this.form.valid) {
      const uploadData = new FormData();
      uploadData.append('imageFile', this.selectedFile, this.selectedFile.name);
      this.selectedFile.imageName = this.selectedFile.name;
      this.product.name = this.form.value.name;
      this.product.description = this.form.value.description;
      this.product.picture = this.form.value.picture;
      this.product.price = new Money();
      this.product.price.amount = this.form.value.price;
      this.product.price.currency = 'CAD';
      this.product.discount = this.form.value.discount;
      this.product.quantity = this.form.value.quantity;
      this.product.size = this.form.value.size;
      this.product.color = this.form.value.color;
      this.product.categories = this.form.value.categories;
      this.productservice.uploadImage(uploadData).subscribe(
        data => {
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
        },
        err => {
          this.message = 'Image upload failed';
        }
      );
    } else {
      this.message = 'The fields should not be empty!!! Please verify details';
    }
  }

  updateWithoutImage(){
    if (this.form.valid) {
      this.product.name = this.form.value.name;
      this.product.description = this.form.value.description;
      this.product.price = new Money();
      this.product.price.amount = this.form.value.price;
      this.product.price.currency = 'CAD';
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
        sessionStorage.setItem('showAdd', 'false');
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

  // logout(){
  //   this.approute.openHome();
  // }

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
    this.form.get('price').setValue(this.product.price.amount);
    this.form.get('discount').setValue(this.product.discount);
    this.form.get('quantity').setValue(this.product.quantity);
    this.form.get('size').setValue(this.product.size);
    this.form.get('color').setValue(this.product.color);
    this.form.get('categories').setValue(this.product.categories);
  }

  changeImage(){
    this.showchangeImage = true;
  }

  addCategory(){
    this.form.categories = this.form.get('categories') as FormArray;
    this.form.categories.push(new FormControl());
  }

  deleteCategory(index:number){
    this.form.categories = this.form.get('categories') as FormArray;
    this.form.categories.removeAt(index);
  }

  fileChange(event) {
    console.log(event);
    this.selectedFile = event.target.files[0];

    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event2) => {
      this.imgURL = reader.result;
    };
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
