import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css'],
  providers: [PostService]
})
export class PostsComponent implements OnInit {

  posts= [];

  constructor(private route: ActivatedRoute,
    private router: Router,private postServ: PostService
    ){
  }

  ngOnInit() {
    this.traerPosts()
  }

  traerPosts(){
    this.postServ.getAll().subscribe(
      res => {console.log(res);this.posts = res;},
      error => console.log(error)
    )
  }

  viewPost(post) {
    this.router.navigate(['id', post.id ]);
  }
}
