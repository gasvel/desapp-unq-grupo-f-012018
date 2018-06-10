import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ReservationService } from '../services/reservation.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-confirmations',
  templateUrl: './confirmations.component.html',
  styleUrls: ['./confirmations.component.css']
})
export class ConfirmationsComponent implements OnInit {

  reservs = [];
  mailUser:any;

  constructor(private reservService: ReservationService, private formBuilder: FormBuilder, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.mailUser = JSON.parse(localStorage.getItem("userInfo")).email;
    console.log(this.mailUser);
    this.getReservs();
  }

  confirmReserv(reser){
    this.reservService.confirmReserv(reser).subscribe(
      res => console.log(res),
      error => console.log(error)
    );
  }

  cancelReserv(reser){
    this.reservService.cancelReserv(reser).subscribe(
      res => console.log(res),
      error => console.log(error)
    );
  }

  getReservs(){
    this.reservService.getAll(this.mailUser).subscribe(
      res => {console.log(res); this.reservs = res},
      error => console.log(error)
    );
    console.log(this.reservs);
  }
}