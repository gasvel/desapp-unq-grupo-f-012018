import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable()
export class UpdateService {

  private updateRequired = new Subject<boolean>();
  private updatePostReq = new Subject<boolean>();

  update$ = this.updateRequired.asObservable();
  updatePost$ = this.updatePostReq.asObservable();

  constructor() {
    this.updateRequired.next(false);
    this.updatePostReq.next(false);
   }

   setUpdatePost(val){
     this.updatePostReq.next(val);
   }

   setUpdate(val){
     console.log(val);
     this.updateRequired.next(val);
     console.log(this.updateRequired);
   }



}
