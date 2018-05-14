import { Component, OnInit } from '@angular/core';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import {MatDialog, MatDialogConfig} from "@angular/material";
import { Router, ActivatedRoute } from '@angular/router';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})

export class PostDetailComponent implements OnInit {
  post:any;

  constructor(
    private service : PostService,
    private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog) {
  }

  ngOnInit() {
    this.route.params.subscribe((params) => {
        this.post = JSON.parse(params['post']);
    });
  }

  rent() {
    console.log('Rent');
  }

  edit() {
    let postToEdit = JSON.stringify(this.post);
    this.router.navigate(['editPost', postToEdit ]);
  }

  delete() {
    this.service.delete(this.post.id).subscribe(
      res => {
          console.log(res);
          this.router.navigate(['']);
      },
      error => console.log(error)
    );
  }

  openDialog() {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = true;
        dialogConfig.autoFocus = true;
        const component = this;
        const dialogRef = this.dialog.open(DeleteDialogComponent, dialogConfig);

        dialogRef.afterClosed().subscribe(data => {
          if(data) {
            component.delete()
          }
        });
    }
}
