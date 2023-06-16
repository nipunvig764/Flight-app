import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TicketBookingService } from '../services/ticket-booking.service';

@Component({
  selector: 'app-ticket-details',
  templateUrl: './ticket-details.component.html',
  styleUrls: ['./ticket-details.component.css']
})
export class TicketDetailsComponent implements OnInit {

  ticketPnr:String | undefined;
  constructor(private tbService: TicketBookingService, private route: Router) { }

  ngOnInit(): void {
    this.ticketPnr = this.tbService.getTicketPnr();
  }

  changeRoute(){
    this.route.navigate(['/flights']);
  }

}
