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

  baseurl:string = 'http://localhost:8080/api/v1/user/';

  constructor(private http: HttpClient) { }

  getUser(): Observable<User[]>{
    return this.http.get<User[]>(this.baseurl+'users');
  }

  getUserById(id:string): Observable<User>{
    return this.http.get<User>(this.baseurl+'user/'+id);
  }

  getUserByUsername(username:string): Observable<User>{
    return this.http.get<User>(this.baseurl+'username/'+username);
  }

  addUser(data: User): Observable<User>{
    return this.http.post<User>(this.baseurl+'register',data,httpOptions);
  }

  updateUser(data: User): Observable<User>{
    return this.http.put<User>(this.baseurl+'update/password',data,httpOptions);
  }

  deleteUser(id: string): Observable<User>{
    return this.http.delete<User>(this.baseurl+'user/'+id);
  }


}

