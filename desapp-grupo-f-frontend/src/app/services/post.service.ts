import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class PostService {

  url:String = "https://desapp-unq-grupof.herokuapp.com/rest/posts";

  //url:String = "http://localhost:8080/rest/posts";

  constructor(private http: HttpClient) { }


  getVehicleTypes():Observable<any> {
    const httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.get(this.url + "/vehicle/types", httpOptions);
  }

  getAll():Observable<any> {
    return this.http.get(this.url + "/all");
  }

  getPost(id):Observable<any> {
    return this.http.get(this.url + "/post/" + id);
  }

  savePost(post,id):Observable<any> {
    const httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.post(this.url + "/" + id + "/new",post,httpOptions)
  }

  updatePost(post,id):Observable<any> {
    const httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.put(this.url +'/'+ id + "/update", post,httpOptions)
  }

  delete(id):Observable<any> {
    const httpOptions = {headers: new HttpHeaders({"Authorization": localStorage.getItem("token")})};

    return this.http.delete(this.url + "/delete/" + id,httpOptions);
  }
}
