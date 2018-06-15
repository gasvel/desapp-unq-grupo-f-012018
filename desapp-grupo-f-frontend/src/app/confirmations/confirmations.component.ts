import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ReservationService } from '../services/reservation.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-confirmations',
  templateUrl: './confirmations.component.html',
  styleUrls: ['./confirmations.component.css']
})
export class ConfirmationsComponent implements OnInit {

  reservs = [];
  reservaSeleccionada;
  mailUser:any;

  constructor(private reservService: ReservationService, private formBuilder: FormBuilder, private route: ActivatedRoute, private router: Router, private spinner: NgxSpinnerService) { }

  ngOnInit() {
    this.mailUser = JSON.parse(localStorage.getItem("userInfo")).email;
    console.log(this.mailUser);
    this.getReservs();
  }

  confirmReserv(reser){
    this.spinner.show();
    this.reservService.confirmReserv(reser).subscribe(
      res => {console.log(res);this.spinner.hide();},
      error => {console.log(error);this.spinner.hide();}
    );
  }

  cancelReserv(reser){
    this.spinner.show();
    this.reservService.cancelReserv(reser).subscribe(
      res => {console.log(res);this.spinner.hide();},
      error => {console.log(error);this.spinner.hide();}
    );
  }

  seleccionarReser(reser){
    this.reservaSeleccionada = reser;
    this.reservaSeleccionada.startDate = new Date(this.reservaSeleccionada.startDate);
    this.reservaSeleccionada.endDate = new Date(this.reservaSeleccionada.endDate);
  }

  getReservs(){
    this.spinner.show();
    this.reservService.getAll(this.mailUser).subscribe(
      res => {console.log(res); this.reservs = res; this.spinner.hide();},
      error => {console.log(error); this.spinner.hide();}
    );
    console.log(this.reservs);
  }
}