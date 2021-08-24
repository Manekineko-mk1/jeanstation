import { Component, OnInit, Input } from '@angular/core';
import { Product } from 'src/app/model/Product';
import { MessengerService } from 'src/app/services/messenger.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent implements OnInit {

  @Input() productItem: Product;
  showDetails: boolean;
  closeModal: string;
  showProduct: Product;
  isLoggedIn: boolean;

  constructor(private msg: MessengerService, private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  // Handles when user click on "Add to Cart"
  handleAddToCart() {
    this.msg.sendMsg(this.productItem);
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
