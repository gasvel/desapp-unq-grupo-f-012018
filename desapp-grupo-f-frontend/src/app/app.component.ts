import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { SharedSearchFilterService } from './services/shared-search-filter.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  searchText = "";

  constructor(private translateService: TranslateService, private sharedSearchService: SharedSearchFilterService) {
    this.translateService.setDefaultLang('es');
    this.translateService.use('es');
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
}
