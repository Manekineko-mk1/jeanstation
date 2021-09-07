import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Order } from '../model/Order';
import { ApprouteService } from '../services/approute.service';
import { OrderService } from '../services/order.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-myorder',
  templateUrl: './myorder.component.html',
  styleUrls: ['./myorder.component.css']
})
export class MyorderComponent implements OnInit {

  orders:Order[];
  id:string;
  showOrder:Order;
  closeModal;
  step:number;
  isRejected:boolean = false;

  constructor(private orderservice:OrderService, private approute:ApprouteService, private userservice:UserService,
    private modalService: NgbModal) { }

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
    // this.userservice.getUserByUsername(sessionStorage.getItem('username')).subscribe(
    //   data => {
    //     this.id = data.id;
    //   },
    //   err => {
    //     console.log(err);
    //     console.log(sessionStorage.getItem('username'));
    //   }
    // )
    this.getOrders();
  }

  getOrders(){
    this.orderservice.getOrderByUserId(sessionStorage.getItem('username')).subscribe(
      data => {
        this.orders = data;
      },
      err => {
        console.log(err);
      }
    )
  }

  atStep(status:string){
    switch(status){
      case "SUBMITTED" : {this.step=1;break;}
      case "IN_PROGRESS" : {this.step=2;break;}
      case "SHIPPED" : {this.step=3;break;}
      case "DELIVERED" : {this.step=4;break;}
      case "REJECTED" : {this.step=0; break;}
    }
  }

  triggerModal(content, order) {
    this.showOrder = order;
    this.atStep(order.status);
    if(this.step==0){
      this.isRejected = true;
    } else {
      this.isRejected = false;
    }
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
