import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription }   from 'rxjs';
import { SharedSearchFilterService } from './../../services/shared-search-filter.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { UpdateService } from '../../services/update.service';

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
  isMap = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private postServ: PostService,
    private searchFilterServ: SharedSearchFilterService,
    private spinner: NgxSpinnerService,
    private updateServ: UpdateService
    ){
      updateServ.updatePost$.subscribe(res => {if(res){
        this.traerPosts();this.updateServ.setUpdatePost(false);
      }});
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
    this.spinner.show();
    this.postServ.getAll().subscribe(
      res => {
        this.posts = res;
        this.spinner.hide();
      },
      error => {console.log(error);this.spinner.hide();}
    )
  }

  print(){
    console.log(this.isMap);
  }

  viewPost(post) {
    this.router.navigate(['post', post.id ]);
  }

  viewMap() {
    this.router.navigate(['posts/map']);
  }
}
