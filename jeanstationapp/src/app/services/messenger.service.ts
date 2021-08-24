import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Product } from 'src/app/model/Product';
import { CartStatus } from 'src/app/model/CartStatus';

@Injectable({
  providedIn: 'root'
})
export class MessengerService {

  subject = new Subject();

  constructor() { }

  sendMsg(product: Product) {
    // Triggering an event
    this.subject.next(product);
  }

  getMsg() {
    return this.subject.asObservable();
  }

}
