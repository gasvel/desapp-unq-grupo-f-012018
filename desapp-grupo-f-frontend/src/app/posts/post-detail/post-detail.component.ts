import { Component, OnInit } from '@angular/core';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import {MatDialog, MatDialogConfig} from "@angular/material";
import { Router, ActivatedRoute } from '@angular/router';
import { PostService } from '../../services/post.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})

export class PostDetailComponent implements OnInit {
  post:any;
  isFromUser:boolean = false;

  constructor(
    private service : PostService,
    private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog,
    private spinner: NgxSpinnerService) {
  }

  ngOnInit() {
    this.route.params.subscribe((params) => {
        let id = params['id'];
        this.getPost(id);
    });
  }

  rent() {
    console.log('Rent');
    //si no funca lo del localstorage le puedo mandar el post.creator.id y alv
    this.router.navigate(['reservation', this.post.id])
  }

  getPost(id) {
    this.spinner.show();
    this.service.getPost(id).subscribe(
      res => {this.post = res; this.isFromUser = (JSON.parse(localStorage.getItem("userInfo")) !== null) && (this.post.creator.email == JSON.parse(localStorage.getItem("userInfo")).email);this.spinner.hide();},
      error => {console.log(error);this.spinner.hide();}
    );
  }

  edit() {
    this.router.navigate(['editPost', this.post.id ,this.post.creator.id]);
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

  logged(){
    return localStorage.getItem("token") != null;
  }


}
