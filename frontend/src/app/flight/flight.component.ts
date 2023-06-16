import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { CitiesService } from '../services/cities.service';
import { Flights } from '../shared/Flight';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.css']
})
export class FlightComponent implements OnInit {

  sources!:string[];
  destinations!:string[];
  errorMsg!:string;
  source!:string
  destination!:string
  date!: Date
  flights!:Flights[]
  todayDate!:string
  flag:number=2
  displayMsg!:string

  constructor(private cities: CitiesService,private router:Router) { }

  ngOnInit(): void {
    this.sources=this.getSources();
    this.destinations=this.getDestinations();

    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();
    this.todayDate=yyyy+"-"+mm+"-"+dd;
    
  }

  getSources():any{
    this.cities.getSources().subscribe(
      {
        next: (res)=>{this.sources=res;console.log("sources: "+res)},
        error: (err)=>{console.log(err);this.errorMsg=err.error['message'];}
      }
    )
  }

  getDestinations():any{
  this.cities.getDestionations().subscribe(
    {
      next: (res)=>{this.destinations=res;console.log("destionations: "+res)},
      error: (err)=>this.errorMsg=err.message
    }
  )
}

searchFlights(){
  this.cities.searchFlights(this.source, this.destination, this.date).subscribe(
    {
      next: (res)=>{console.log(res);this.flights=res;if((this.flights).length){
        this.flag=0

      }
      else {
        this.flag=1
        alert("Sorry No flights found..")
      }
      
    },
      error: (err)=>{
        this.displayMsg=err
        
      }
    }
  );

}

bookFlight(flight:any){
  console.log(flight); 
  this.router.navigate(['/booking', flight.flightId]);
}

}