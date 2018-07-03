import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { UsersService} from '../services/users.service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-editar-user',
  templateUrl: '../nuevo-user/nuevo-user.component.html',
  styleUrls: ['../nuevo-user/nuevo-user.component.css'],
  providers: [UsersService]
})
export class EditarUserComponent implements OnInit {

  EMAIL_REGEXP = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

  errorNewUser = false;
  successModal = false;
  successModalMessage = '';
  errorNewUserMessage = '';

  usuario : FormGroup = this.formBuilder.group({
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

  oldUser:any;
  isEdit:boolean = true;
  userId:any;

  constructor(private formBuilder: FormBuilder, private userServ : UsersService,private router : Router, private route : ActivatedRoute, private spinner: NgxSpinnerService) {
    this.userId = this.route.snapshot.paramMap.get("id");
   }

  ngOnInit() {
    this.getUser();
  }

  getUser(){
    this.spinner.show();
    this.userServ.getUser(this.userId).subscribe(
      res => {console.log(res);this.setUsuario(res); this.spinner.hide();},
      error => {console.log(error);this.spinner.hide();}
    );;
  }

  setUsuario(res){
    this.oldUser= res;
    this.usuario.controls.cuil.setValue(res.cuil);
    this.usuario.controls.address.setValue(res.address);
    this.usuario.controls.name.setValue(res.name);
    this.usuario.controls.email.setValue( res.email);
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

  setFinalUser(){
    this.oldUser.cuil = this.usuario.controls.cuil.value;
    this.oldUser.name = this.usuario.controls.name.value;
    this.oldUser.email = this.usuario.controls.email.value;
    this.oldUser.address = this.usuario.controls.address.value;
    console.log(this.oldUser);
    return this.oldUser;
  }


  onSubmit(){    
    this.spinner.show();
    this.userServ.updateUser(this.setFinalUser()).subscribe(
      res => {console.log(res);this.spinner.hide();this.handleSuccess(res);},
      error => {this.handleError(error);console.log(error);this.spinner.hide();}
    );
  }

  handleSuccess(response:any){
    this.successModal=true;
    this.successModalMessage = response.error;
  }

  handleError(response:any){

    this.errorNewUser = true;
    console.log(response);
    this.errorNewUserMessage = response.error;
  }

}
