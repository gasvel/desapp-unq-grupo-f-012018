import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { SharedSearchFilterService } from './services/shared-search-filter.service'
import { UsersService } from './services/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [UsersService]
})
export class AppComponent implements OnInit{
  title = 'app';
  searchText = "";
  user:any = {};

  constructor(private translateService: TranslateService, private sharedSearchService: SharedSearchFilterService,private userServ : UsersService,private routerServ : Router) {
    this.translateService.setDefaultLang('es');
    this.translateService.use('es');
  }

  ngOnInit(){
    if(this.logged()){
      this.getUser();
    }
  }

  getUser(){
    this.userServ.getUser(localStorage.getItem("id")).subscribe(
      res => {console.log(res);this.user = res},
      error => console.log(error)
    );
  }

  changeLang(lang){
    this.translateService.use(lang);
  }

  logged(){
    return localStorage.getItem("id") != null;
  }

  setSearch(){
    console.log(this.searchText);
    this.sharedSearchService.setSetSearchText(this.searchText);
  }

  cleanSearch(){
    this.searchText = "";
    console.log(this.searchText);
    this.sharedSearchService.setSetSearchText(this.searchText);
  }

  addCredits(){
    this.routerServ.navigate(["/credits/add",this.user.id,this.user.credits]);
  }

  extractCredits(){
    this.routerServ.navigate(["/credits/extract",this.user.id,this.user.credits]);
  }
}
