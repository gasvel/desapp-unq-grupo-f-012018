import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { UsersService} from '../services/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-editar-user',
  templateUrl: '../nuevo-user/nuevo-user.component.html',
  styleUrls: ['../nuevo-user/nuevo-user.component.css'],
  providers: [UsersService]
})
export class EditarUserComponent implements OnInit {

  EMAIL_REGEXP = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;


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

  constructor(private formBuilder: FormBuilder, private userServ : UsersService,private router : Router) { }

  ngOnInit() {
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
    this.userServ.saveUser(this.usuario.value).subscribe(
      res => {console.log(res);this.volverAtras()},
      error => console.log(error)
    );
  }

}
