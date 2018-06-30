import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UsersService} from '../services/users.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-info-cuenta',
  templateUrl: './info-cuenta.component.html',
  styleUrls: ['./info-cuenta.component.css']
})
export class InfoCuentaComponent implements OnInit {

  user:any;
  userScore:any;
  userId:any;

  constructor(private userServ : UsersService,private router : Router, private route : ActivatedRoute, private spinner: NgxSpinnerService) {
    this.userId = this.route.snapshot.paramMap.get("id");
  }

  ngOnInit() {
    this.spinner.show();
    this.getUser();
    this.getUserScore();
    this.spinner.hide();
  }

  getUser(){
    // this.spinner.show();
    this.userServ.getUser(this.userId).subscribe(
      res => {console.log(res);this.user = res;},
      error => {console.log(error);this.spinner.hide();}
    );
  }

  getUserScore(){
    // this.spinner.show();
    this.userServ.getUserScore(this.userId).subscribe(
      res => {console.log(res); this.userScore = res;},
      error => {console.log(error); this.spinner.hide();}
    );
  }

  volverAtras(){
    this.router.navigate(['/posts']);
  }
}
