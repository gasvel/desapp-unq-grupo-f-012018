import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ReservationService {

  url:String = "http://localhost:8080/rest/reservations";

  constructor(private http: HttpClient) { }


  saveReservation(reservation,idPost, mailUser): Observable<any>{
    let httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.post(this.url + "/new/" + idPost + "/" + mailUser ,reservation,httpOptions)
  }

  getReservations(): Observable<any>{
    let httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};
    
    return this.http.get(this.url + "/all");
  }
}
