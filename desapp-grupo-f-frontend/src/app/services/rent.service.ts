import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class RentService {

  url:String = "http://localhost:8080/rest/rents";

  constructor(private http: HttpClient) { }


  saveRent(rent): Observable<any>{
    let httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.post(this.url + "/new",rent,httpOptions)
  }

  getRents(): Observable<any>{
    let httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.get(this.url + "/all", httpOptions);
  }

  toConfirmClient(mail):Observable<any>{
    let httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.get(this.url + "/toConfirm/client/" + mail, httpOptions);
  }

  confirmPickUp(rent,mail): Observable<any>{
    let httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.put(this.url + "/confirmPickUp/" + mail, rent, httpOptions)
  }

  confirmVehicleReturns(rent,mail,score): Observable<any>{
    let httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};
    return this.http.put(this.url + "/confirmVehicleReturn/" + mail +"/" +score, rent, httpOptions)
  }

  toConfirmOwner(mail):Observable<any>{
    let httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.get(this.url + "/toConfirm/owner/" + mail , httpOptions)
  }

}
