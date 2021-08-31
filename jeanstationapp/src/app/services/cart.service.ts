import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../model/Product';
import { Cart } from '../model/Cart';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class CartService {

  baseurl:string = 'http://localhost:8080/cart/api/v1/';

  constructor(private http: HttpClient) { }

  getAllCarts(): Observable<Cart[]>{
    return this.http.get<Cart[]>(this.baseurl + 'cart');
  }

  getCartById(id:String): Observable<Cart>{
    return this.http.get<Cart>(this.baseurl+'cart/' + id);
  }

  createCart(data: Cart): Observable<Cart>{
    return this.http.post<Cart>(this.baseurl+'cart', data, httpOptions);
  }

  updateCart(data: Cart): Observable<Cart>{
    return this.http.put<Cart>(this.baseurl+'cart', data, httpOptions);
  }

  deleteCart(id: String): Observable<Cart>{
    return this.http.delete<Cart>(this.baseurl+'cart/' + id);
  }
}
