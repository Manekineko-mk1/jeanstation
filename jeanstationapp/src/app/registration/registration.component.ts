import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Address } from '../model/Address';
import { User } from '../model/User';
import { ApprouteService } from '../services/approute.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  form;
  message;

  constructor(private userservice:UserService, private formBuilder:FormBuilder, private approute:ApprouteService) {
    this.form = this.formBuilder.group({
      username: new FormControl('',Validators.required),
      password: new FormControl('', [Validators.required, Validators.minLength(8)]),
      name: new FormControl('',Validators.required),
      doornumber: new FormControl('',Validators.required),
      street: new FormControl('',Validators.required),
      city: new FormControl('',Validators.required),
      country: new FormControl('',Validators.required),
      postalcode: new FormControl('',[Validators.required, Validators.pattern('[A-Za-z][0-9][A-Za-z] [0-9][A-Za-z][0-9]')]),
      telephone: new FormControl('', [Validators.required])
    });
  }

  ngOnInit(): void {
  }

  onSubmit(){

    if(this.form.valid){
      let address:Address = new Address();
      address.doorNumber = this.form.value.doornumber;
      address.street = this.form.value.street;
      address.city = this.form.value.city;
      address.country = this.form.value.country;
      address.postalCode = this.form.value.postalcode;

      let user:User = new User();
      user.username = this.form.value.username;
      user.password = this.form.value.password;
      user.realName = this.form.value.name;
      user.creationDate = (new Date()).getDate().toString();
      user.userRole = 'USER';
      user.userStatus = 'ACTIVE';
      user.telephone = this.form.value.telephone;
      user.address = address;

      this.userservice.addUser(user).subscribe(
        data => {
          this.approute.openLogin();
        },
        err => {
          this.message = 'Registration failed!';
        }
      );
    } else {
      this.message = 'Fields cannot be empty! Please verify provided information'
    }

    

  }

}
