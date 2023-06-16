import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  successMsg!: String;
  errorMsg!: String;
  constructor(private fb: FormBuilder,private userService: UserService, private route:Router) { }

  ngOnInit(): void {
    this.loginForm=this.fb.group({
      email:[null, [Validators.required,Validators.email]],
      password:[null, Validators.required]
    });
  }

  login(){
    console.log(this.loginForm);
    this.userService.loginUser(this.loginForm.value).subscribe({
      next: succMsg => {
        this.userService.setUser(this.loginForm.value);
        this.successMsg="Login Successful";
        this.errorMsg=""
        this.route.navigate(['/flights']);
      },
      error: errMsg => {
        this.errorMsg=errMsg.error.message;
        this.successMsg="";
      }
    })
  }

}