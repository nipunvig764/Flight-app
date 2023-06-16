import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CitiesService {

  constructor(private http: HttpClient) { }

  getFlightUrl:String="http://localhost:8082/infygo/flights/get";


  getSources():Observable<any>{
    return this.http.get<any>('http://localhost:8082/infygo/flights/sources');
  }
  getDestionations():Observable<any>{
    return this.http.get<any>('http://localhost:8082/infygo/flights/destination');
  }
  searchFlights(source:string ,destination:string, date:Date): Observable<any>
  {
    return this.http.get<any>('http://localhost:8082/infygo/flights/query;source='+source+';destination='+destination+';date='+date);
  }

  getFlightDetails(flightId:string): Observable<any>
  {
    return this.http.get<any>(`${this.getFlightUrl}/${flightId}`);
  }
  
}

