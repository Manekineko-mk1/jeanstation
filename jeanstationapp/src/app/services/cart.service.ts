import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../model/Product';
import { Cart } from '../model/Cart';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'}),
  mode: 'cors'
};

@Injectable({
  providedIn: 'root'
})
export class CartService {

  baseurl:string = 'http://localhost:8081/api/v1/cart/';

  constructor(private http: HttpClient) { }

  getAllCarts(): Observable<Cart[]>{
    return this.http.get<Cart[]>(this.baseurl + 'carts');
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
