import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { ApprouteService } from '../services/approute.service';
import { AuthenticationService } from '../services/authentication.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form;
  message:string ="";

  constructor(private formBuilder: FormBuilder, private approute: ApprouteService, private authservice:AuthenticationService, private userservice:UserService) {
    this.form = this.formBuilder.group({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });
    this.approute.isLoggedIn.next(false);
    this.approute.isAdmin.next(false);
    
  }

  ngOnInit(): void {
    // sessionStorage.setItem('isLoggedIn', 'false');
    // sessionStorage.setItem('isAdmin', 'false');
      if(this.authservice.isUserLoggedIn()){
        this.authservice.logOut();
      }
  }

  onSubmit(){
    if(!this.form.valid){
      this.message = 'Username and Password should not be empty!!! Please verify details'
    } else {
      if((this.form.value.username=='admin') && (this.form.value.password=='password')){
        sessionStorage.setItem('username', 'admin');
        this.approute.isLoggedIn.next(true);
        this.approute.isAdmin.next(true);
        // sessionStorage.setItem('isLoggedIn', 'true');
        // sessionStorage.setItem('isAdmin', 'true');
        this.approute.openAdmin();
      } else {
        this.authservice.authenticate(this.form.value.username, this.form.value.password).subscribe(
          userData => {
            this.approute.isLoggedIn.next(true);
            //sessionStorage.setItem('isLoggedIn', 'true');
            this.approute.openProduct();
          },
          err => {
            console.log(err);
            this.message = 'Username and Password incorrect!!! Please verify details ';
          }
        );
      }

      
    }
  }

  openRegistration(){
    this.approute.openRegistration();
  }

}
