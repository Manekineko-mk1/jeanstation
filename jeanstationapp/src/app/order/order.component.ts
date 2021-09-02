import { Component, OnInit } from '@angular/core';
import { Order } from '../model/Order';
import { MessengerService } from '../services/messenger.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  order: Order;

  constructor(private msg:MessengerService) { }

  ngOnInit(): void {
    this.handleSubscription;
  }

  handleSubscription() {
    // Add listener to MessengerService
    this.msg.getMsg().subscribe(order => {
      this.order = order;
    });
  }

}
