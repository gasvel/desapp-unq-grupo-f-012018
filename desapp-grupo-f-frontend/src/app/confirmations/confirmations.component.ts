import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ReservationService } from '../services/reservation.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { RentService } from '../services/rent.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-confirmations',
  templateUrl: './confirmations.component.html',
  styleUrls: ['./confirmations.component.css']
})
export class ConfirmationsComponent implements OnInit {
  formRent:FormGroup = this.formBuilder.group({
    score: new FormControl('', Validators.compose([
      Validators.required, Validators.min(0), Validators.max(10)
    ]))
  })
  reservs = [];
  rentsOwner = [];
  rentsClient = [];
  reservaSeleccionada;
  rentSelected;
  mailUser:any;

  constructor(private reservService: ReservationService, private formBuilder: FormBuilder,
     private route: ActivatedRoute, private router: Router, private spinner: NgxSpinnerService,private rentServ : RentService) { }

  ngOnInit() {
    this.mailUser = JSON.parse(localStorage.getItem("userInfo")).email;
    console.log(this.mailUser);
    this.loadData();
  }

  loadData(){
    this.spinner.show();
    Observable.forkJoin(
      this.reservService.getAll(this.mailUser),
      this.rentServ.toConfirmClient(this.mailUser),
      this.rentServ.toConfirmOwner(this.mailUser)
    ).subscribe(
      response =>{
        this.reservs = <any>response[0];
        this.rentsClient = <any>response[1];
        this.rentsOwner = <any>response[2];
        this.spinner.hide();
      },error => {
        console.log(error);this.spinner.hide();
      }
    );

  }

  confirmReserv(reser){
    this.spinner.show();
    this.reservService.confirmReserv(reser).subscribe(
      res => {console.log(res);this.spinner.hide();window.location.reload();},
      error => {console.log(error);this.spinner.hide();}
    );
  }

  confirmRent(rent){
    this.rentServ.confirmPickUp(rent,this.mailUser).subscribe(
      res => {console.log(res); this.loadData()},
      error => console.log(error)
    )
  }

  confirmVehicleReturns(){
    var score = this.formRent.controls.score.value;
    this.rentServ.confirmVehicleReturns(this.rentSelected,this. mailUser, score).subscribe(
      res => {console.log(res)},
      error => console.log(error)
    )
  }

  cancelRent(rent){

  }

  cancelReserv(reser){
    this.spinner.show();
    this.reservService.cancelReserv(reser.id).subscribe(
      res => {console.log(res);this.spinner.hide();window.location.reload();this.loadData();},
      error => {console.log(error);this.spinner.hide();}
    );
  }

  seleccionarReser(reser){
    console.log(reser.id);
    this.reservaSeleccionada = reser;
    this.reservaSeleccionada.startDate = new Date(this.reservaSeleccionada.startDate);
    this.reservaSeleccionada.endDate = new Date(this.reservaSeleccionada.endDate);
  }

  seleccionarRent(rent){
    console.log(rent.id);
    this.rentSelected = rent;
  }

  getReservs(){
    this.spinner.show();
    this.reservService.getAll(this.mailUser).subscribe(
      res => {console.log(res); this.reservs = res; this.spinner.hide();},
      error => {console.log(error); this.spinner.hide();}
    );
    console.log(this.reservs);
  }

  getRentsClient(){
    this.spinner.show();
    this.rentServ.toConfirmClient(this.mailUser).subscribe(
      res => {this.rentsClient = res; console.log(res);this.spinner.hide()},
      error => {console.log(error);this.spinner.hide()}
    );
  }

  getRentsOwner(){
    this.spinner.show();
    this.rentServ.toConfirmOwner(this.mailUser).subscribe(
      res => {this.rentsOwner = res; console.log(res);this.spinner.hide()},
      error => {this.spinner.hide();console.log(error)}
    );
  }
}
