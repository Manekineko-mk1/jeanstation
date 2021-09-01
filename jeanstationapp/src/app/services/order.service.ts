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

  baseurl:string = 'http://localhost:8082/api/v1/order/orders';

  constructor(private http: HttpClient) { }

  getOrder(): Observable<Order[]>{
    return this.http.get<Order[]>(this.baseurl);
  }

  getOrderById(id:string): Observable<Order>{
    return this.http.get<Order>(this.baseurl+'/'+id);
  }

  addOrder(data: Order): Observable<Order>{
    return this.http.post<Order>(this.baseurl,data,httpOptions);
  }

  updateOrder(data: Order): Observable<Order>{
    return this.http.put<Order>(this.baseurl,data,httpOptions);
  }

  deleteOrder(id: string): Observable<Order>{
    return this.http.delete<Order>(this.baseurl+'/'+id);
  }

  getOrderByUserId(id:string): Observable<Order[]>{
    return this.http.get<Order[]>(this.baseurl+'/user/'+id);
  }


}

