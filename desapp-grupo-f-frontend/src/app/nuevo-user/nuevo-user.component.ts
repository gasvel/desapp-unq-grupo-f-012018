import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { UsersService} from '../services/users.service';
import { Router } from '@angular/router';
import { SocialUser } from 'angularx-social-login';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-nuevo-user',
  templateUrl: './nuevo-user.component.html',
  styleUrls: ['./nuevo-user.component.css'],
  providers: [UsersService]
})
export class NuevoUserComponent implements OnInit {

  EMAIL_REGEXP = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;


  errorNewUser = false;
  successModal = false;
  successModalMessage = '';
  errorNewUserMessage = '';

  usuario : FormGroup = this.formBuilder.group({
    imgsrc: new FormControl(),
    cuil : new FormControl('',Validators.compose([
      Validators.required,
      Validators.minLength(10)
    ])),
    address: new FormControl('',Validators.required),
    name: new FormControl('',Validators.compose([
      Validators.required,
      Validators.minLength(4),
      Validators.maxLength(50)
    ])),
    email: new FormControl('',Validators.compose([
      Validators.pattern(this.EMAIL_REGEXP),
      Validators.required
    ]))
  });
  isEdit:boolean = false;
  userInfo:SocialUser;

  constructor(private formBuilder: FormBuilder, private userServ : UsersService,private router : Router, private spinner: NgxSpinnerService) {
      this.userInfo = JSON.parse(localStorage.getItem("userInfo"));
   }

  ngOnInit() {
    console.log(this.userInfo);
    this.usuario.controls.imgsrc.setValue(this.userInfo.photoUrl);
    this.usuario.controls.name.setValue(this.userInfo.name);
    this.usuario.controls.email.setValue(this.userInfo.email);
  }

  volverAtras(){
    // location.reload();
    this.router.navigate(['/posts']);
  }

  validForm(){
    let invalid = false;
    for(let control in this.usuario.controls){
      invalid = invalid || this.usuario.controls[control].invalid;
		}
    return (invalid || (!(this.usuario.dirty)));
  }


  onSubmit(){   
    this.spinner.show() ;
    this.userServ.saveUser(this.usuario.value).subscribe(
      res => {console.log(res);localStorage.setItem("token",this.userInfo.idToken);this.spinner.hide();this.handleSuccess(res);location.reload();this.volverAtras();},
      error => {this.handleError(error);console.log(error);this.spinner.hide();}
    );
  }

  handleSuccess(response:any){
    this.successModal=true;
    console.log(response);
    this.successModalMessage = response.body;
  }

  handleError(response:any){

    this.errorNewUser = true;
    console.log(response);
    this.errorNewUserMessage = response.error;
  }


}
