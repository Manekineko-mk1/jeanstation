import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Address } from '../model/Address';
import { User } from '../model/User';
import { ApprouteService } from '../services/approute.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {

  form;
  user:User;
  id:string;
  isAdmin:boolean;
  update:boolean;
  message:string;

  constructor(private userService:UserService, private approute:ApprouteService, private formBuilder:FormBuilder) {
    this.form = this.formBuilder.group({
      username: new FormControl('',Validators.required),
      name: new FormControl('',Validators.required),
      doornumber: new FormControl('',Validators.required),
      street: new FormControl('',Validators.required),
      city: new FormControl('',Validators.required),
      country: new FormControl('',Validators.required),
      postalcode: new FormControl('',Validators.required),
      status: new FormControl('',Validators.required),
      telephone: new FormControl('', Validators.required)
    });
   }

  ngOnInit(): void {
    this.update = false;
    let address:Address = new Address();
    address.id="1";
    address.city="mycity";
    address.country="mycounter";
    address.doorNumber=100;
    address.postalCode="post";
    address.street="mystreet";
    this.user = new User();
    this.user.id="1";
    this.user.username="myusername";
    this.user.userRole="User";
    this.user.userStatus="Active";
    this.user.creationDate="2021";
    this.user.name="myRealname";
    this.user.address = address;
    this.user.telephone = "11111111";
    this.approute.isAdmin.subscribe(
      value => {
        this.isAdmin = value;
      }
    );
  }

  getUser(){
    this.userService.getUserById(this.id).subscribe(
      data => {
        this.user = data;
      }
    );
  }

  showUpdate(){
    this.update = true;
    this.form.get('username').setValue(this.user.username);
    this.form.get('name').setValue(this.user.name);
    this.form.get('doornumber').setValue(this.user.address.doorNumber);
    this.form.get('street').setValue(this.user.address.street);
    this.form.get('city').setValue(this.user.address.city);
    this.form.get('country').setValue(this.user.address.country);
    this.form.get('postalcode').setValue(this.user.address.postalCode);
    this.form.get('status').setValue(this.user.userStatus);
    this.form.get('telephone').setValue(this.user.telephone);
  }

  updateUser(){
    if(this.form.valid){
      let address:Address = new Address();
      address.doorNumber = this.form.value.doornumber;
      address.street = this.form.value.street;
      address.city = this.form.value.city;
      address.country = this.form.value.country;
      address.postalCode = this.form.value.postalcode;
      this.user.username = this.form.value.username;
      this.user.address = address;
      this.user.name = this.form.value.name;
      this.user.telephone = this.form.value.telephone;
      this.user.userStatus = this.form.value.status;
      this.userService.updateUser(this.user).subscribe(
        data => {
          this.message = "Updated";
          this.clearForm();
          
        },
        err => {
          this.message = "Failed";
          this.clearForm();
        }
      );
      this.update = false;
    }
    
  }

  clearForm(){
    this.form.reset();
    this.message = '';
  }

}
