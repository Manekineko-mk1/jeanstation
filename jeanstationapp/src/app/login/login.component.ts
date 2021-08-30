import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { ApprouteService } from '../services/approute.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form;
  message:string ="";

  constructor(private formBuilder: FormBuilder, private approute: ApprouteService) {
    this.form = this.formBuilder.group({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });
    sessionStorage.setItem('isLoggedIn', 'false');
    sessionStorage.setItem('isAdmin', 'false');
  }

  ngOnInit(): void {
    
  }

  onSubmit(){
    if(!this.form.valid){
      this.message = 'Username and Password should not be empty!!! Please verify details'
    } else {
      if((this.form.value.username=='admin') && (this.form.value.password=='password')){
        sessionStorage.setItem('isLoggedIn', 'true');
        sessionStorage.setItem('isAdmin', 'true');
        this.approute.openAdmin();
      } else if((this.form.value.username=='user') && (this.form.value.password=='password')){
        sessionStorage.setItem('isLoggedIn', 'true');
        this.approute.openProduct();
      } else {
        this.message = 'Username and Password incorrect!!! Please verify details'
      }
    }
  }

  openRegistration(){
    this.approute.openRegistration();
  }

}
