import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../model/Product';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  baseurl:string = 'http://localhost:8080/product/api/v1/';

  constructor(private http: HttpClient) { }

  getProduct(): Observable<Product[]>{
    return this.http.get<Product[]>(this.baseurl+'products');
  }

  // getProductById(id:number): Observable<Product>{
  //   return this.http.get<Product>(this.baseurl+'product/'+id);
  // }

  addProduct(data: Product): Observable<Product>{
    return this.http.post<Product>(this.baseurl+'product',data,httpOptions);
  }

  uploadImage(data) {
    return this.http.post(this.baseurl+'upload', data, {observe: 'response'});
  }

  updateProduct(data: Product): Observable<Product>{
    return this.http.put<Product>(this.baseurl+'product',data,httpOptions);
  }

  deleteProduct(id: string): Observable<Product>{
    return this.http.delete<Product>(this.baseurl+'product/'+id);
  }
}
