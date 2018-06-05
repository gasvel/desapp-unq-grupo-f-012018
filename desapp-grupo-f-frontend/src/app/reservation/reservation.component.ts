import { Component, ElementRef, OnInit, ViewChild, ViewEncapsulation } from "@angular/core";
import "dhtmlx-scheduler";
import {} from "@types/dhtmlxscheduler";
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ReservationService } from '../services/reservation.service';
import { UsersService } from '../services/users.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  encapsulation: ViewEncapsulation.None,
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  @ViewChild("scheduler_here") schedulerContainer: ElementRef;
  formReserv:FormGroup = this.formBuilder.group({
    startDate: new FormControl('', Validators.required),
    endDate: new FormControl('', Validators.required),
    timeOfRent: new FormControl(),
    client: new FormControl()
  })
  newReservation;
  idPost:any;
  

  constructor(private userService: UsersService, private reservService: ReservationService, private formBuilder: FormBuilder, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    // scheduler.init(this.schedulerContainer.nativeElement, new Date());
    // this.reservService.getReservations().subscribe(
    //   res => {console.log(res);scheduler.parse(res, "json");},
    //   error => console.log(error)
    // );
    this.route.params.subscribe((params) => {
      this.idPost = params["idPost"];
    });
    console.log(this.idPost);
    this.newReservation = {startDate:'', endDate: '', timeOfRent:'', client:''};
  }

  saveReservation(){
    console.log(this.formReserv.controls.startDate.value);
    console.log(this.formReserv.controls.endDate.value);
    this.getClient();
    this.calculateTimeRent();
    this.reservService.saveReservation(this.formReserv.value, this.idPost).subscribe(
        res => console.log(res),
  			error => console.log(error)
    );
  }

  calculateTimeRent(){
    let startDate = this.formReserv.controls.startDate.value;
    let endDate = this.formReserv.controls.endDate.value;
    let time = endDate.getTime() - startDate.getTime();
    this.formReserv.controls.timeOfRent.setValue(time);
    console.log(this.formReserv.controls.timeOfRent.value);
  }

  backToPostDetail(){

  }

  getClient(){
    let idUser = localStorage.getItem("id");
    console.log(idUser);
    this.userService.getUser(idUser).subscribe(
      res => {console.log(res);this.formReserv.controls.client.setValue(res);},
      error => console.log(error)
    )
  }

}
