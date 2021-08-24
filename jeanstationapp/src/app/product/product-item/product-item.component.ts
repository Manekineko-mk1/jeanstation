import { Component, OnInit, Input } from '@angular/core';
import { Product } from 'src/app/model/Product'

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent implements OnInit {

  @Input() productItem: Product;

  constructor() { }

  ngOnInit(): void {
  }

}
