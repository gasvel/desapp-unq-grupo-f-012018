import { Component, ElementRef, OnInit, ViewChild, ViewEncapsulation } from "@angular/core";
import "dhtmlx-scheduler";
import {} from "@types/dhtmlxscheduler";
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ReservationService } from '../services/reservation.service';
import { UsersService } from '../services/users.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { SocialUser } from 'angularx-social-login';
@Component({
  encapsulation: ViewEncapsulation.None,
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  errorNewReser = false;
  errorNewReserMessage = '';
  successModal = false;
  successModalMessage = '';

  @ViewChild("scheduler_here") schedulerContainer: ElementRef;
  formReserv:FormGroup = this.formBuilder.group({
    startDate: new FormControl('', Validators.required),
    startHour: new FormControl('', Validators.required),
    endHour: new FormControl('', Validators.required),
    endDate: new FormControl('', Validators.required)
  })
  newReservation;
  idPost:any;
  mailUser:any;
  priceDay:any;
  user:any = {};

  constructor(private userService: UsersService, private reservService: ReservationService, private formBuilder: FormBuilder, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    // scheduler.init(this.schedulerContainer.nativeElement, new Date());
    // this.reservService.getReservations().subscribe(
    //   res => {console.log(res);scheduler.parse(res, "json");},
    //   error => console.log(error)
    // );
    this.route.params.subscribe((params) => {
      this.idPost = params["id"];
      this.priceDay = params["price"];
      console.log(this.idPost);
      console.log('Precio:' + this.priceDay);
    });
    this.newReservation = {startDate:'', endDate: ''};

    let userInfo:SocialUser = JSON.parse(localStorage.getItem("userInfo"));
    this.userService.getUserByEmail(userInfo.email).subscribe(
      res => {console.log(res);this.user = res},
      error => console.log(error)
    );
  }

  saveReservation(){
    console.log(this.formReserv.controls.startDate.value);
    console.log(this.formReserv.controls.endDate.value);
    this.setDates();
    this.getClient();
    this.setNewReservation();

    this.reservService.saveReservation(this.newReservation, this.idPost, this.mailUser).subscribe(
        res => {this.handleSuccess(res);console.log(res); this.router.navigate(['posts']);},
  			error => {this.handleError(error);console.log(error)}
    );
  }

  handleSuccess(response:any){
    this.successModal=true;
    console.log(response);
    this.successModalMessage = response.body;
  }

  handleError(response:any){
    this.errorNewReser = true;
    console.log(response);
    this.errorNewReserMessage = response.error;
  }

  setNewReservation(){
    this.newReservation.startDate = this.formReserv.controls.startDate.value;
    this.newReservation.endDate = this.formReserv.controls.endDate.value;
  }

  setDates(){
    this.formReserv.controls.startDate.setValue(new Date(this.formReserv.controls.startDate.value + " GMT-0300").setHours(this.formReserv.controls.startHour.value));
    this.formReserv.controls.endDate.setValue(new Date(this.formReserv.controls.endDate.value + " GMT-0300").setHours(this.formReserv.controls.endHour.value));
  }

  backToPostDetail(){
    this.router.navigate(['post', this.idPost ]);
  }

  getClient(){
    this.mailUser = JSON.parse(localStorage.getItem("userInfo")).email;
    console.log(this.mailUser);
  }

}
