import { Component, OnInit, ViewChild } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { SharedSearchFilterService } from './services/shared-search-filter.service'
import { UsersService } from './services/users.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService, SocialUser } from 'angularx-social-login';
import { PostsComponent } from './posts/post-list/posts.component';
import { UpdateService } from './services/update.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [UsersService,UpdateService]
})
export class AppComponent implements OnInit{
  title = 'app';
  searchText = "";
  user:any = {};

  constructor(
    private translateService: TranslateService,
    private sharedSearchService: SharedSearchFilterService,
    private userServ : UsersService,
    private routerServ : Router,
    private socialAuthService: AuthService,
    private activeRoute: ActivatedRoute,
    private updateServ: UpdateService
  ) {
    this.translateService.setDefaultLang('es');
    this.translateService.use('es');
    updateServ.update$.subscribe(res => {if(res){
      this.updateData();this.updateServ.setUpdate(false);
    }});

  }

  ngOnInit(){

    if(this.logged()){
      this.getUser();
    }
  }


  updateData(){
    console.log("actualizado");
    if(this.logged()){
      this.getUser();
    }
  }

  getUser(){
    let userInfo:SocialUser = JSON.parse(localStorage.getItem("userInfo"));
    this.userServ.getUserByEmail(userInfo.email).subscribe(
      res => {console.log(res);this.user = res},
      error => console.log(error)
    );
  }

  changeLang(lang){
    this.translateService.use(lang);
  }

  logged(){
    return localStorage.getItem("token") != null;
  }

  signOut(){
    this.socialAuthService.signOut().then(
      () => {
        localStorage.clear();
        this.updateServ.setUpdatePost(true);
        this.routerServ.navigate(["/posts"]);
   
      }
    );
  }

  setSearch(){
    if(this.activeRoute.snapshot.url.join('') === "posts"){
    	this.sharedSearchService.setSetSearchText(this.searchText);
    }
    else{
    	this.routerServ.navigate(["/posts"]).then(
    		() => this.sharedSearchService.setSetSearchText(this.searchText)
    		);
    }

  }

  cleanSearch(){
    this.searchText = "";
    this.sharedSearchService.setSetSearchText(this.searchText);
  }

  addCredits(){
    this.routerServ.navigate(["/credits/add",this.user.id,this.user.credits]);
  }

  extractCredits(){
    this.routerServ.navigate(["/credits/extract",this.user.id,this.user.credits]);
  }

  routeTo(routeName){
    this.routerServ.navigate(["/"+routeName]);
  }
}
