import { Component, OnInit } from '@angular/core';
import { Order } from '../model/Order';
import { ApprouteService } from '../services/approute.service';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-myorder',
  templateUrl: './myorder.component.html',
  styleUrls: ['./myorder.component.css']
})
export class MyorderComponent implements OnInit {

  orders:Order[];
  id:string;

  constructor(private orderservice:OrderService, private approute:ApprouteService) { }

  ngOnInit(): void {
    // this.orders = new Array();
    // let some = new Order();
    // some.deliveryDate = "2021";
    // some.id = "1";
    // some.orderItem = ["this","that"];
    // some.orderStatus = "ready";
    // some.priceTotal = 23.12;
    // some.shipmentId = "1145";
    // some.userId = "45";
    // this.orders.push(some);
    this.id = sessionStorage.getItem('username');
    this.getOrders();
  }

  getOrders(){
    this.orderservice.getOrderByUserId(this.id).subscribe(
      data => {
        this.orders = data;
      }
    )
  }

}
