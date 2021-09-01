import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { User } from '../model/User';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent implements OnInit {

  form:any;
  id:string;
  message:string;

  constructor(private formBuilder:FormBuilder, private userservice:UserService) {
    this.form = this.formBuilder.group({
      oldPassword: new FormControl('',Validators.required),
      newPassword: new FormControl('',Validators.required),
      confirmPassword: new FormControl('',Validators.required)
    });
   }

  ngOnInit(): void {
    this.id = sessionStorage.getItem('userId');
  }

  onSubmit(){
    let profile:User; 
    this.userservice.getUserById(this.id).subscribe(
      data => {
        profile = data;
      },
    );
    if((profile.password == this.form.value.oldPassword)&&(this.form.value.newPassword == this.form.value.confirmPassword)){
      profile.password = this.form.value.newPassword;
    } else {
      this.message = 'Password Incorrect';
    }
  }

}
