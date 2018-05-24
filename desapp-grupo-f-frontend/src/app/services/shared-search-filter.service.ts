import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class SharedSearchFilterService {

  private sharedSearchTextSource = new Subject<string>();
  sharedSearchText$ = this.sharedSearchTextSource.asObservable();

  constructor() { }

  setSetSearchText(search){
    this.sharedSearchTextSource.next(search);
  }

  // getSearchText(){
  //   return this.sharedSearchText;
  // }
}
