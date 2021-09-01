import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { User } from '../model/User';
import { ApprouteService } from '../services/approute.service';
import { AuthenticationService } from '../services/authentication.service';
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
  profile:User;

  constructor(private formBuilder:FormBuilder, private userservice:UserService, private approute:ApprouteService, private authservice:AuthenticationService) {
    this.form = this.formBuilder.group({
      oldPassword: new FormControl('',Validators.required),
      newPassword: new FormControl('',Validators.required),
      confirmPassword: new FormControl('',Validators.required)
    });
   }

  ngOnInit(): void {
    this.userservice.getUserByUsername(sessionStorage.getItem('username')).subscribe(
      data => {
        this.profile = data;
        console.log(this.profile.password);
      },
      err => {
        console.log(err);
        console.log(sessionStorage.getItem('username'));
      }
    )
  }

  onSubmit(){
    this.authservice.authenticate(sessionStorage.getItem('username'), this.form.value.oldPassword).subscribe(
      data => {
        if(this.form.value.newPassword == this.form.value.confirmPassword){
          this.profile.password = this.form.value.newPassword;
          this.userservice.updateUser(this.profile).subscribe(
            data => {
              this.message = "Password Updated";
              this.approute.openProfile();
            }, 
            err => {
              this.message = "Update failed";
            }
          );
        } else {
          this.message = "Passwords do not match";
        }
      }, 
      err => {
        this.message = 'Password Incorrect';
      }
    )
  
  }

}
