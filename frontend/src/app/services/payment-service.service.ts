import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentServiceService {
  

  paymentUrl:String="http://localhost:8082/infygo/payment/pay";
  
  sendEmailUrl:String = "http://localhost:8082/infygo/sendEmail"

  constructor(private http:HttpClient) { }

  
  makePayment(data:any):Observable<any>{
    return this.http.post<any>("http://localhost:8082/infygo/payment/pay",data)
  }

  sendMail(userId: string):Observable<any> {
    return this.http.post<any>(`${this.sendEmailUrl}/${userId}`,{})
  }
  
}