import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../model/Order';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  baseurl:string = 'http://localhost:8080/api/v1/';

  constructor(private http: HttpClient) { }

  getOrder(): Observable<Order[]>{
    return this.http.get<Order[]>(this.baseurl+'orders');
  }

  getOrderById(id:number): Observable<Order>{
    return this.http.get<Order>(this.baseurl+'order/'+id);
  }

  addOrder(data: Order): Observable<Order>{
    return this.http.post<Order>(this.baseurl+'order',data,httpOptions);
  }

  updateOrder(data: Order): Observable<Order>{
    return this.http.put<Order>(this.baseurl+'order',data,httpOptions);
  }

  deleteOrder(id: number): Observable<Order>{
    return this.http.delete<Order>(this.baseurl+'order/'+id);
  }

  getOrderByUserId(id:string): Observable<Order[]>{
    return this.http.get<Order[]>(this.baseurl+'orders/'+id);
  }


}

