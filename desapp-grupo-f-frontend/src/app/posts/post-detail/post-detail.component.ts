import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})

export class PostDetailComponent implements OnInit {
  post:any;

  constructor(
    private route: ActivatedRoute,
    private router: Router) {

  }

  ngOnInit() {
    this.route
      .queryParams
        .subscribe(params => {
          this.post = JSON.parse(params['post']);
    });
  }

  rent() {
    console.log('Rent');
  }

  edit() {
    console.log('Edit');
  }

  delete() {
    console.log('Delete');
    this.router.navigate(['']);
  }
}
