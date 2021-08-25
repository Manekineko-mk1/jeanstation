import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent implements OnInit {

  form:any;

  constructor(private formBuilder:FormBuilder) {
    this.form = this.formBuilder.group({
      oldPassword: new FormControl('',Validators.required),
      newPassword: new FormControl('',Validators.required),
      confirmPassword: new FormControl('',Validators.required)
    });
   }

  ngOnInit(): void {
  }

  onSubmit(){

  }

}
