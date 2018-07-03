import { Component, OnInit, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { UsersService} from '../services/users.service';
import { Router } from '@angular/router';
import { SocialUser } from 'angularx-social-login';
import { NgxSpinnerService } from 'ngx-spinner';
import { UpdateService } from '../services/update.service';
declare var jQuery:any;


@Component({
  selector: 'app-nuevo-user',
  templateUrl: './nuevo-user.component.html',
  styleUrls: ['./nuevo-user.component.css'],
  providers: [UsersService]
})
export class NuevoUserComponent implements OnInit {

  EMAIL_REGEXP = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;


  public errorNewUser = false;
  public successModal = false;
  public successModalMessage = '';
  public errorNewUserMessage = '';


  @ViewChild('confirmationModal') modal: ElementRef;

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

  constructor(private formBuilder: FormBuilder, private userServ : UsersService,private router : Router, private spinner: NgxSpinnerService,private updateServ : UpdateService) {
      this.userInfo = JSON.parse(localStorage.getItem("userInfo"));
   }

  ngOnInit() {
    console.log(this.userInfo);
    this.usuario.controls.imgsrc.setValue(this.userInfo.photoUrl);
    this.usuario.controls.name.setValue(this.userInfo.name);
    this.usuario.controls.email.setValue(this.userInfo.email);
  }

  volverAtras(){
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
      res => {console.log(res);this.handleSuccess(res);},
      err => {this.handleError(err);console.log(err);this.spinner.hide();}
    );
  }

  handleSuccess(response){
    localStorage.setItem("token",this.userInfo.idToken);
    this.spinner.hide();
    this.successModalMessage = response.body;
    this.updateServ.setUpdate(true);
    jQuery(this.modal.nativeElement).modal('show');
  }

  handleError(response:any){
    this.errorNewUser = true;
    console.log(response);
    this.errorNewUserMessage = response.error;
  }


}
