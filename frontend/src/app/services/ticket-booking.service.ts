import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketBookingService {

  bookTicketUrl:String = "http://localhost:8082/infygo/book";
  ticketPnr : String | undefined;

  constructor(private http:HttpClient) {

   }

   setTicketPnr(pnr:String){
    this.ticketPnr = ""+Math.floor(Math.random() * 10000000000);
  
   }

   getTicketPnr(){
    return this.ticketPnr;
   }

  bookTicket(data:any,flightId:any,userId:any):Observable<any>{
    console.log(data);
    return this.http.post<any>(`${this.bookTicketUrl}/${flightId}/${userId}`,data)
  }


  
}