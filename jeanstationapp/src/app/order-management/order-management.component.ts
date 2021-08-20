import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Order } from '../model/Order';
import { ApprouteService } from '../services/approute.service';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-order-management',
  templateUrl: './order-management.component.html',
  styleUrls: ['./order-management.component.css']
})
export class OrderManagementComponent implements OnInit {

  form;
  orders:Order[];
  message:string;
  status:boolean = false;
  closeModal: string;
  orderToUpdate:Order;

  constructor(private formBuilder:FormBuilder, private orderService:OrderService, private modalService: NgbModal, private approute:ApprouteService) { 
    this.form = this.formBuilder.group({
      status: new FormControl('', Validators.required)
    })
  }

  ngOnInit(): void {
    this.orders = new Array();
    let some = new Order();
    some.deliveryDate = "2021";
    some.id = 1;
    some.orderItem = ["this","that"];
    some.orderStatus = "ready";
    some.priceTotal = 23.12;
    some.shipmentId = "1145";
    some.userId = "45";
    this.orders.push(some);
    //this.getOrders();
  }

  getOrders(){
    this.orderService.getOrder().subscribe(
      data => {
        this.orders = data;
      }
    )
  }

  updateStatus(){
    this.orderToUpdate.orderStatus = this.form.value.status;
    this.orderService.updateOrder(this.orderToUpdate).subscribe();
    this.approute.openOrderManagement();
  }

  changeStatus(){
    this.status = !this.status;
  }

  triggerModal(content, order) {
    this.orderToUpdate = order;
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
