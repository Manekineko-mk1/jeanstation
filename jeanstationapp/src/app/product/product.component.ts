import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
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
  showDetails:boolean;
  closeModal: string;
  showProduct:Product;
  isLoggedIn:boolean;

  constructor(private productservice:ProductService, private approuter:ApprouteService, private modalService: NgbModal) { }

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
    // this.approuter.isLoggedIn.subscribe(
    //   value => {
    //     this.isLoggedIn = value;
    //   }
    // );
  }

  getProducts(){
    this.productservice.getProduct().subscribe(
      data => {
        this.products = data;
      }
    )


  }

  addToCart(){
    // if(this.isLoggedIn==false){
    //   this.approuter.openLogin();
    // } else {
    //   this.approuter.openCart();
    // }
    this.approuter.openCart();
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
