import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { LoginService } from './../services/login.service';
import { Router } from '@angular/router';
import { AuthService } from "angularx-social-login";
import { GoogleLoginProvider } from "angularx-social-login";
import { PostsComponent } from '../posts/post-list/posts.component';
import { UpdateService } from '../services/update.service';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css'],
  providers: []
})
export class LoginUserComponent implements OnInit {

  //public credential = {email: '', password: ''};
  public user: any;
  @Output() Update = new EventEmitter();


  constructor(
    private loginServ: LoginService,
    private routerServ: Router,
    private authService: AuthService,private updateServ: UpdateService
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
            this.updateServ.setUpdate(true);
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
