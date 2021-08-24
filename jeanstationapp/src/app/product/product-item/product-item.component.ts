import { Component, OnInit, Input } from '@angular/core';
import { Product } from 'src/app/model/Product';
import { MessengerService } from 'src/app/services/messenger.service'

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent implements OnInit {

  @Input() productItem: Product;

  constructor(private msg: MessengerService) { }

  ngOnInit(): void {
  }

  // Handles when user click on "Add to Cart"
  handleAddToCart() {
    this.msg.sendMsg(this.productItem);
  }
}
