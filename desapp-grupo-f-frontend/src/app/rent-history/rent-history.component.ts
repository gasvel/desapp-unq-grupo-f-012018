import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { RentService } from '../services/rent.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-rent-history',
  templateUrl: './rent-history.component.html',
  styleUrls: ['./rent-history.component.css']
})
export class RentHistoryComponent implements OnInit {

  mailUser:any;
  rentsOwner= [];
  rentsClient= [];

  constructor(private route: ActivatedRoute,
    private router: Router,
    private spinner: NgxSpinnerService,
    private rentServ : RentService) {
  }

  ngOnInit() {
    this.mailUser = JSON.parse(localStorage.getItem("userInfo")).email;
    this.loadData();
  }

  loadData(){
    this.spinner.show();
    Observable.forkJoin(
      this.rentServ.rentDoneOwner(this.mailUser),
      this.rentServ.rentDoneClient(this.mailUser)
    ).subscribe(
      response =>{
        this.rentsClient = <any>response[0];
        this.rentsOwner = <any>response[1];
        this.spinner.hide();
      },error => {
        console.log(error);this.spinner.hide();
      }
    );

  }

}
