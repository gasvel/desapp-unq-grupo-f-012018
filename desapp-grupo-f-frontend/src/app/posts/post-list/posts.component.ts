import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription }   from 'rxjs';
import { SharedSearchFilterService } from './../../services/shared-search-filter.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css'],
  providers: [PostService]
})
export class PostsComponent implements OnInit {

  searchText;
  subscription: Subscription;
  posts= [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private postServ: PostService,
    private searchFilterServ: SharedSearchFilterService
    ){
      this.subscription = searchFilterServ.sharedSearchText$.subscribe(
        text => {
          this.searchText = text;
        }
      );
  }

  ngOnInit() {
    this.traerPosts()
  }

  traerPosts(){
    this.postServ.getAll().subscribe(
      res => {
        console.log(res);
        this.posts = res;
      },
      error => console.log(error)
    )
  }

  viewPost(post) {
    this.router.navigate(['post', post.id ]);
  }

  viewMap() {
    this.router.navigate(['posts/map']);
  }
}
