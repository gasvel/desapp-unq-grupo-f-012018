import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { UsersService } from '../services/users.service';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-alta-creditos',
  templateUrl: './alta-creditos.component.html',
  styleUrls: ['./alta-creditos.component.css'],
  providers: [UsersService]
})
export class AltaCreditosComponent implements OnInit {

  credits:any;
  userId:any;
  textoBoton:String = "Agregar a la cuenta";
  textoLabel:String = "Ingrese la cantidad de creditos a agregar: ";
  form: FormGroup = this.formBuilder.group({
    credits : new FormControl('',Validators.compose(
      [Validators.required,Validators.max(this.credits)]
    ))
  }
  );

  constructor(private routerServ: Router, private route: ActivatedRoute, private userServ: UsersService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.credits =  this.route.snapshot.paramMap.get('credits');
    console.log(this.credits);
    this.userId = this.route.snapshot.paramMap.get('userId');
  }

  actionCredits(){
    this.userServ.altaCreditos(this.userId,this.form.controls.credits.value).subscribe(
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
