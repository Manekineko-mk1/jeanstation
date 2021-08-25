import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Address } from 'src/app/model/Address';
import { User } from 'src/app/model/User';

import { UserService } from './user.service';

const address:Address = {
  doorNumber : 100,
  street : 'Streetname',
  city: 'Montreal',
  country : 'Canada',
  postalCode : 'H5R0T3'
}

const user1:User = {
  username : 'NewUser',
  userRole : 'USER',
  userStatus : 'ACTIVE',
  creationDate : '2021-08-30',
  name : 'Real Name',
  address : address,
  telephone : '123-456-7890'
}

const user2:User = {
  username : 'NewUser2',
  userRole : 'USER',
  userStatus : 'ACTIVE',
  creationDate : '2021-08-30',
  name : 'Real Name2',
  address : address,
  telephone : '123-456-7890'
}

describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers: [UserService]
    });
    httpMock = TestBed.get(HttpTestingController);
    service = TestBed.get(UserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should have getUser()', () =>{
    expect(service.getUser).toBeTruthy();
  });

  it('should have getUserById()', () =>{
    expect(service.getUserById).toBeTruthy();
  });

  it('should have addUser()', () =>{
    expect(service.addUser).toBeTruthy();
  });

  it('should have updateUser()', () =>{
    expect(service.updateUser).toBeTruthy();
  });

  it('should have deleteUser()', () =>{
    expect(service.deleteUser).toBeTruthy();
  });

  it('getUser() method should return list of users', () => {
    service.getUser().subscribe();
    const req = httpMock.expectOne('http://localhost:8080/user/api/v1/users');
    expect(req.request.method).toEqual('GET');
  });

  it('getUserById() method should return respective user', () => {
    service.getUserById(user1.id).subscribe();
    const req = httpMock.expectOne('http://localhost:8080/user/api/v1/user/'+user1.id);
    expect(req.request.method).toEqual('GET');
  });

  it('addUser() method should add User', () => {
    service.addUser(user1).subscribe(
      data => {
        expect(data.name).toEqual('New User');
      }
    );
    const req = httpMock.expectOne('http://localhost:8080/user/api/v1/user');
    expect(req.request.method).toEqual('POST');
    expect(req.request.headers.get('Content-Type')).toEqual('application/json');
  });

  it('updateUser() method should update User', () => {
    user1.name = 'New name';
    service.updateUser(user1).subscribe(
      data => {
        expect(data.name).toEqual('New name');
      }
    );
    const req = httpMock.expectOne('http://localhost:8080/user/api/v1/user');
    expect(req.request.method).toEqual('PUT');
    expect(req.request.headers.get('Content-Type')).toEqual('application/json');
  });

  it('deleteUser() method should delete User', () => {
    service.deleteUser(user1.id).subscribe();
    const req = httpMock.expectOne('http://localhost:8080/user/api/v1/user/'+user1.id);
    expect(req.request.method).toEqual('DELETE');
  });

  afterEach( () => {
    httpMock.verify();
  });
});
