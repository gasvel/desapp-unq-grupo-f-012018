import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { UsersService } from '../services/users.service';
import { switchMap } from 'rxjs/operators';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-retiro-creditos',
  templateUrl: '../alta-creditos/alta-creditos.component.html',
  styleUrls: ['../alta-creditos/alta-creditos.component.css'],
  providers: [UsersService]
})
export class RetiroCreditosComponent implements OnInit {

  credits:any;
  userId:any;
  textoLabel:String = "Ingrese la cantidad de creditos a retirar: ";
  textoBoton: String = "Retirar creditos";
  form: FormGroup = this.formBuilder.group({
    credits : new FormControl('',Validators.compose(
      [Validators.required]
    ))
  }
  );


  constructor(private route: ActivatedRoute,private routerServ: Router, private userServ: UsersService,public formBuilder: FormBuilder) { }

  ngOnInit() {
    this.credits =  this.route.snapshot.paramMap.get('credits');
    this.form.controls.credits.setValidators([Validators.required,Validators.max(this.credits)]);
    console.log(this.credits);
    this.userId = this.route.snapshot.paramMap.get('userId');
  }

  actionCredits(){
    this.userServ.retiroCreditos(this.userId,this.form.controls.credits.value).subscribe(
      res => {console.log(res);this.routerServ.navigate(["/posts"])},
      error => console.log(error)
    )
  }

  submit(){
    this.actionCredits();
  }

  cancel(){
    this.routerServ.navigate(["/posts"]);
  }

}
