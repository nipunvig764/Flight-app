import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDetails } from '../shared/User-Details';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  loginUser(data:any): Observable<any>{
    return this.http.get("http://localhost:8082/infygo/user/login?emailId="+data.email+"&password="+data.password);
  }

  registerUser(data:any): Observable<UserDetails>{
    return this.http.post<UserDetails>("http://localhost:8082/infygo/user/register",data);
  }

  setUser(user:any){
    localStorage.setItem("userDetails",JSON.stringify(user));
  }

  getUser(){
    let userStr = localStorage.getItem("userDetails");
    if(userStr !=null){
      return JSON.parse(userStr)
    }
  }

  
  isLoggedIn(){
    let tokenString = localStorage.getItem("userDetails");
    if(tokenString==undefined || tokenString==null || tokenString==''){
      return false;
    }
    return true;
  }

  logout(){
    localStorage.removeItem("userDetails");
  }

}