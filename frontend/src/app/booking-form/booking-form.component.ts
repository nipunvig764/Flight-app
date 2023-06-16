import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { CitiesService } from '../services/cities.service';
import { PaymentServiceService } from '../services/payment-service.service';
import { TicketBookingService } from '../services/ticket-booking.service';
import { UserService } from '../services/user.service';
import { Passenger } from '../shared/Passenger';




@Component({
  selector: 'app-booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.css']
})
export class BookingFormComponent implements OnInit {
  passengerListForBackend:any[]=[];
  isPassengerListEmpty=true;
  fare:any;
  display = "none";
  flightDetailsCollapsed = true;
  flightIdFromParams:any;
  flight:any;
  passengerDetailsCollapsed=false;
  addPassengerForm!: FormGroup;
  paymentDetailsCollapsed=false;
  passengersList:any[]=[];
  paymentForm!: FormGroup;
  proceed:boolean=false;
  totalBill:any;
  seatsFull = false;
  errorMessage:any;
  userId!: string;
  date:any

  constructor(private ticketBookingService:TicketBookingService,private userService:UserService,private paymentService:PaymentServiceService,private cityService:CitiesService,private router:Router,private activatedRoute:ActivatedRoute,private fb:FormBuilder) {}


  ngOnInit(): void {
    this.userId = this.userService.getUser().email;
    this.flightIdFromParams = this.activatedRoute.snapshot.paramMap.get('flightId');
    
    this.cityService.getFlightDetails(this.flightIdFromParams).subscribe({
      next:flight=>{
        this.flight=flight;
        console.log(this.flight);
        
        this.date = this.flight.flightAvailableDate;
        // this.date = new DatePipe('en-Us').transform(this.date, 'dd-MM-yyyy hh:mm:ss', 'GMT+6:00');
        // this.flight.flightAvailableDate = this.date;
        
        console.log(this.date);
      }
    })

    this.addPassengerForm = this.fb.group({
      passengerName:['',Validators.required],
      passengerGender:['',Validators.required],
      passengerAge:['',[Validators.required,Validators.min(1)]]
    })

    this.paymentForm = this.fb.group({
      cardNumber:['',[Validators.minLength(16),Validators.maxLength(16),Validators.required]],
      cardHolderName:['',Validators.required],
      cvv:['',[Validators.minLength(3),Validators.maxLength(3),Validators.required]],
      apin:['',[Validators.minLength(4),Validators.maxLength(4),Validators.required]], 
      expiryMonth: ['',Validators.required],
      expiryYear: ['',Validators.required],
      totalBill:[this.totalBill]
    })
  }

  changeSuit(e:any) {
    console.log(e);
    
  }
  addPassenger(){
    if(this.passengersList.length==this.flight.seatCount){
        this.seatsFull=true;
        this.errorMessage = "No more seats available";
    }
    else{

      this.passengersList.push(this.addPassengerForm.value);
      this.passengerListEmptyOrNot();
   //   this.passengersList.push({id:this.passengersList.length,passengerData:this.addPassengerForm.value});
      console.log(this.passengersList);

    }
    this.addPassengerForm.reset();
  }

  removePassenger(id:number){
    console.log("index to be removed "+id);
    console.log(this.passengersList.length);
    this.passengersList.splice(id, 1)
    this.passengerListEmptyOrNot();
  }


  makePayment(){
    this.display="none";
    this.paymentForm.controls['totalBill'].setValue(this.fare); 
    
    console.log(this.totalBill);
    console.log(this.paymentForm.value);


    this.paymentService.makePayment(this.paymentForm.value)
    .subscribe({
      next:data=>{
        console.log(data)
        this.bookTickets();
        this.sendMail();
      },
      error:error=>{
        console.log(error.error.message);
        this.errorMessage=error.error.message
      }
    })
    this.paymentForm.reset();
  }

  openModal() {
    this.errorMessage="";
    this.fare = this.flight.fare*this.passengersList.length;
    this.display = "block";
  }
  onCloseHandled() {
    this.display = "none";
  }

  passengerListEmptyOrNot(){
    if(this.passengersList.length!==0){
      this.isPassengerListEmpty= false;
    }
    else{
      this.isPassengerListEmpty = true;
    }
  }

  bookTickets() {
    this.ticketBookingService.bookTicket(this.passengersList,this.flight.flightId,this.userId).subscribe({
      next:data=>{
        console.log(data);
        this.ticketBookingService.setTicketPnr(data.pnr);
        this.router.navigate(['/ticketDetails'])
      },
      error:error=>console.log(error)
    })
  }

  sendMail() {
    this.paymentService.sendMail(this.userId).subscribe({
      next:data=>{
        console.log(data);
      },
      error:error=>console.log(error)
    })
  }
}