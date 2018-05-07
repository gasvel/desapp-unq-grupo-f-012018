import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class PostService {

  url:String = "http://localhost:8080/rest/posts";

  constructor(private http: HttpClient) { }

  getAll():Observable<any> {
    return this.http.get(this.url + "/all");
  }

  getPost(id):Observable<any> {
    return this.http.get(this.url + "/post/" + id);
  }

  savePost(post):Observable<any> {
    return this.http.post(this.url + "/new",post)
  }
}
