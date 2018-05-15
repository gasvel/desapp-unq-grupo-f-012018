import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UsersService {

  url:String = "http://localhost:8080/rest/users";

  constructor(private http: HttpClient) { }

  getUsers():Observable<any> {
    return this.http.get(this.url + "/all");
  }

  getUser(id):Observable<any> {
    return this.http.get(this.url + "/user/" + id);
  }

  saveUser(user):Observable<any> {
    return this.http.post(this.url + "/new",user)
  }

  updateUser(user):Observable<any> {
    return this.http.put(this.url + "/1"+ "/update",user)
  }
}
