import { Component, OnInit } from '@angular/core';
import { LoginService } from './../services/login.service';
import { Router } from '@angular/router';
import { AuthService } from "angularx-social-login";
import { GoogleLoginProvider } from "angularx-social-login";

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {

  //public credential = {email: '', password: ''};
  public user: any;

  constructor(
    private loginServ: LoginService,
    private routerServ: Router,
    private authService: AuthService
  ) {

  }

  ngOnInit() {

  }
/*
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
  */

  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then(
      (userData) => {
        console.log(userData);
        this.loginServ.checkUser(userData.email).subscribe(
          res => {if(res){
            localStorage.setItem("token",userData.idToken);
            localStorage.setItem("userInfo",JSON.stringify(userData));
            location.reload();
            this.routerServ.navigate(['/posts']);
          }
          else{
            localStorage.setItem("userInfo",JSON.stringify(userData));
            this.routerServ.navigate(['registro']);

          }}
        );
        //this.routerServ.navigate(['/posts']);
      },
      error => console.log(error)
    );
  }
}
