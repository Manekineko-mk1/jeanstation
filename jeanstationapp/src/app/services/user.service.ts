import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseurl:string = 'http://localhost:8080/user/api/v1/';

  constructor(private http: HttpClient) { }

  getUser(): Observable<User[]>{
    return this.http.get<User[]>(this.baseurl+'users');
  }

  getUserById(id:string): Observable<User>{
    return this.http.get<User>(this.baseurl+'user/'+id);
  }

  addUser(data: User): Observable<User>{
    return this.http.post<User>(this.baseurl+'user',data,httpOptions);
  }

  updateUser(data: User): Observable<User>{
    return this.http.put<User>(this.baseurl+'user',data,httpOptions);
  }

  deleteUser(id: string): Observable<User>{
    return this.http.delete<User>(this.baseurl+'user/'+id);
  }


}

