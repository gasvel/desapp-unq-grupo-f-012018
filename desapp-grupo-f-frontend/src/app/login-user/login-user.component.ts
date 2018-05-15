import { Component, OnInit } from '@angular/core';
import { LoginService } from './../services/login.service';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {

  public credential = {email: '', password: ''};
  public user: any;

  constructor(private loginServ: LoginService, private routerServ: Router) { }

  ngOnInit() {
  }

  onSubmit() { this.login(); }

  login() {
    this.loginServ.logIn(this.credential).subscribe(
      result => {
        this.user = result;
        console.log(this.user);
        localStorage.setItem("id", this.user.id);
        this.routerServ.navigate(['/posts']);
      },
      error => {
        console.log(error);
      }
      );
  }
}
