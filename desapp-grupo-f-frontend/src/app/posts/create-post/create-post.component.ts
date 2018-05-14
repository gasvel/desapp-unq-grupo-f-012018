import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { NgxInputFileUploadComponent } from 'ngx-input-file-upload'
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms'
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Post } from '../../model/post';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {
  isSave:boolean = true;
  imagePost:any = "assets/img/no-image.jpg";
  typeOptions:any = ["Moto", "Camioneta", "Auto", "Furgoneta"];
  capacityOptions:any = [ 1, 2, 3, 4, 5, 6, 7, 8 ];
  @ViewChild(NgxInputFileUploadComponent)

  private NgxInputFileUploadComponent: NgxInputFileUploadComponent;
  acceptHtml="image/*"
  acceptTs=/image-*/
  activeColor: string = '#3366CC';
  submitted:boolean = false;
  post:any;
  postForm : FormGroup;

  constructor(
    private service : PostService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) {
  }

  @ViewChild("addressToDropRef")
  public addressToDropRef: ElementRef;addressToPickUp
  @ViewChild("addressToPickUpRef")
  public addressToPickUpRef: ElementRef;

  ngAfterViewInit(){
      let component = this;
			let options = {
	      types: [],
	      componentRestrictions: {country: "ar"}
	    };
      let autocomplete1 = new google.maps.places.Autocomplete(this.addressToDropRef.nativeElement, options);
			autocomplete1.addListener('place_changed', function() {
				//this.postForm.get('addressToDrop').setValue(autocomplete.getPlace().formatted_address);
			});
      let autocomplete2 = new google.maps.places.Autocomplete(this.addressToPickUpRef.nativeElement, options);
      autocomplete2.addListener('place_changed', function() {});
	}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      let isEdit = Object.keys(params).length === 1;
      if(isEdit) {
        this.isSave = false;
        this.post = JSON.parse(params['post']);
        this.NgxInputFileUploadComponent.imageSrc = this.post.photo;
        this.setPostForm();
      }
      else {
        this.post = { "id":"", "title":"", "typeVehicle":"Auto", "description":"", "availability":"",
                    "photo":"","username":"", "capacity":"5", "addressToPickUp":"", "addressToDrop":"",
                    "priceDay":"", "priceHour":"", "phoneNumber":""};
        this.setPostForm();
      }
    });
  }

  setPostForm() {
    console.log(this.post);
    this.postForm = this.formBuilder.group({
      postTitle: new FormControl(this.post.title ,Validators.compose([
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(50)
      ])),
      availability: new FormControl(this.post.availability ,Validators.required),
      typeOption: new FormControl(this.post.typeVehicle ,Validators.required),
      capacity: new FormControl(parseInt(this.post.capacity) ,Validators.required),
      addressToPickUp: new FormControl(this.post.addressToPickUp ,Validators.required),
      addressToDrop: new FormControl(this.post.addressToDrop ,Validators.required),
      description: new FormControl(this.post.description ,Validators.required),
      priceDay: new FormControl(this.post.priceDay ,Validators.required),
      priceHour: new FormControl(this.post.priceHour ,Validators.required),
      phoneNumber: new FormControl(this.post.phoneNumber ,Validators.compose([
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(15)
      ])),
    });
  }

  updatePost(post){
    this.service.updatePost(post).subscribe(
      res => {
          console.log(res);
          this.router.navigate(['post', JSON.stringify(post)])
      },
      error => console.log(error)
    );
  }

  savePost(post){
    console.log(post);
    this.service.savePost(post).subscribe(
      res => {
          console.log(res);
          this.router.navigate(['']);
      },
      error => console.log(error)
    );
    //this.router.navigate(['']);
  }

  save(){
    console.log(this.postForm.valid);
    if(this.postForm.valid) {
      let post = this.getPost();
      if(this.isSave) {
        this.savePost(post);
      }
      else {
        this.updatePost(post)
      }
    }
  }

  getPost() {
    let post = new Post();
    post.title = this.postForm.get('postTitle').value;
    post.availability = this.postForm.get('availability').value;
    post.description = this.postForm.get('description').value;
    post.typeVehicle = this.postForm.get('typeOption').value;
    post.capacity = this.postForm.get('capacity').value;
    post.priceDay = this.postForm.get('priceDay').value;
    post.priceHour = this.postForm.get('priceHour').value;
    post.phoneNumber = this.postForm.get('phoneNumber').value;
    post.addressToPickUp = this.postForm.get('addressToPickUp').value;
    post.addressToDrop = this.postForm.get('addressToDrop').value;
    post.photo = this.NgxInputFileUploadComponent.imageSrc;
    return post;
  }

  onSubmit() {
    this.submitted = true;
  }

  back() {
    if(this.isSave) {
      this.router.navigate(['']);
    }
    else {
      this.router.navigate(['post', JSON.stringify(this.post)]);
    }
  }
}
