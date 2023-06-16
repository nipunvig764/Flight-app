import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookingFormComponent } from './booking-form/booking-form.component';
import { ControlsGuard } from './controlsGuard';
import { FlightComponent } from './flight/flight.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { TicketDetailsComponent } from './ticket-details/ticket-details.component';

const routes: Routes = [
  {path: "register", component: RegisterComponent},
  {path: "login", component: LoginComponent},
  {path: "flights", component: FlightComponent},
  {path: 'booking/:flightId', component: BookingFormComponent,canActivate:[ControlsGuard]},
  {path : "ticketDetails", component: TicketDetailsComponent,canActivate:[ControlsGuard]},
  {path:"", component: LoginComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
