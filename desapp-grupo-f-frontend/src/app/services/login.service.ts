import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class LoginService {

  url:String = "http://localhost:8080/rest/posts";

  constructor(private http: HttpClient) { }

  logIn(cred): Observable<any> {
    return this.http.post(this.url + "login", cred);
  }
}
