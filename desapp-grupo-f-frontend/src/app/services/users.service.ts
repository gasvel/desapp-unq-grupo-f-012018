import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UsersService {

  //url:String = "https://desapp-unq-grupof.herokuapp.com/rest/users";

  url:String = "http://localhost:8080/rest/users";

  constructor(private http: HttpClient) { }

  getUsers():Observable<any> {
    return this.http.get(this.url + "/all");
  }

  getUser(id):Observable<any> {
    let httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};
    return this.http.get(this.url + "/user/" + id,httpOptions);
  }

  getUserByEmail(mail):Observable<any> {
    let httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.get(this.url + "/user/byEmail/" + mail,httpOptions);
  }

  saveUser(user):Observable<any> {
    let httpOptions = {headers: new HttpHeaders({"Authorization": JSON.parse(localStorage.getItem("userInfo")).idToken})};

    return this.http.post(this.url + "/new",user,httpOptions)
  }

  updateUser(user):Observable<any> {
    let httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.put(this.url + "/1"+ "/update",user,httpOptions)
  }

  altaCreditos(idUser,creditos){
    let httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.put(this.url + "/user/credits/add/" + idUser + "/"+ creditos,{},httpOptions)
  }

  retiroCreditos(idUser,creditos){
    let httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.put(this.url + "/user/credits/remove/" + idUser + "/"+ creditos,{},httpOptions)
  }
}
