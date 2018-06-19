import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class LoginService {
  //url:String = "https://desapp-unq-grupof.herokuapp.com/rest/users";
  url:String = "http://localhost:8080/rest/users";

  constructor(private http: HttpClient) { }

  /*logIn(cred): Observable<any> {
    return this.http.post(this.url + "/login", cred);
  }*/

  checkUser(mail):Observable<any> {
    return this.http.get(this.url + "/checkUser/" + mail);
  }
}
