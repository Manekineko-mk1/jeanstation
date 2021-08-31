import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../model/Product';
import { Cart } from '../model/Cart';
import { Order } from '../model/Order';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  baseurl:string = 'http://localhost:8080/api/v1/';

  constructor(private http: HttpClient) { }

  getAllOrders(): Observable<Order[]>{
    return this.http.get<Order[]>(this.baseurl + 'orders');
  }

  getOrderById(id:String): Observable<Order>{
    return this.http.get<Order>(this.baseurl+ 'order/' + id);
  }

  createOrder(data: Order): Observable<Order>{
    return this.http.post<Order>(this.baseurl+ 'order', data, httpOptions);
  }

  updateOrder(data: Order): Observable<Order>{
    return this.http.put<Order>(this.baseurl+ 'order', data, httpOptions);
  }

  deleteOrder(id: String): Observable<Order>{
    return this.http.delete<Order>(this.baseurl+ 'order/' + id);
  }

  getOrderByUserId(id:string): Observable<Order[]>{
      return this.http.get<Order[]>(this.baseurl+ 'order/user/' + id);
  }
}
