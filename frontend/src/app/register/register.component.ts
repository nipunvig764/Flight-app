import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { UserDetails } from '../shared/User-Details';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;
  successMsg!: UserDetails;
  errorMsg!: String;
  constructor(private fb: FormBuilder, private userService: UserService,private router:Router) { }

  ngOnInit(): void {
    this.registerForm=this.fb.group({
      name:[null,Validators.required],
      city:[null,Validators.required],
      email:[null,[Validators.required,Validators.email]],
      phone:[null,[Validators.required,Validators.maxLength(10),Validators.minLength(10)]],
      password:[null,[Validators.required,Validators.pattern("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"
      )]]
    })
  }

  register(){
    console.log(this.registerForm);
    this.userService.registerUser(this.registerForm.value).subscribe({
      next: succMsg => {
          this.successMsg=succMsg;
          this.errorMsg="";
          this.router.navigate(['/login']);
      },
      error: errorMsg => {
          this.errorMsg=errorMsg.error.message;
      }
    })
  }

}