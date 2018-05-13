import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css'],
  providers: [PostService]
})
export class PostsComponent implements OnInit {

  posts= [];

  constructor(private postServ: PostService){
  	
  }

  ngOnInit() {
    this.traerPosts()
  }

  traerPosts(){
    this.postServ.getAll().subscribe(
      res => this.posts = res,
      error => console.log(error)
    )
  }

  abrirPublicacion(){

  }

}
