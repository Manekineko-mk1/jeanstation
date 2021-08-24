import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProductComponent } from '../product/product.component';
import { CartComponent } from '../cart/cart.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  providers: [ProductComponent, CartComponent],
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  //images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);
  constructor(private productComponent:ProductComponent, private cartComponent:CartComponent) { }

  ngOnInit(): void {
  }



}
