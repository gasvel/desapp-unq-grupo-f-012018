import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { NgxInputFileUploadComponent } from 'ngx-input-file-upload'
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms'
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Post } from '../../model/post';
import { PostService } from '../../services/post.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { AngularFireStorage, AngularFireUploadTask } from 'angularfire2/storage';
import {Observable} from 'rxjs/Observable';
import { finalize } from 'rxjs/operators';
import { UpdateService } from '../../services/update.service';
declare var jQuery:any;

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  errorNewPost = false;
  successModal = false;
  successModalMessage = '';
  errorNewPostMessage = '';

  @ViewChild('confirmationModal') modal: ElementRef;

  isSave:boolean = true;
  imagePost:any = "assets/img/no-image.jpg";
  typeOptions:any = ["Car", "Motorcycle", "Van", "Pickup"];

  task: AngularFireUploadTask;
  percentage: Observable<any>;
  snapshot: Observable<any>;
  downloadUrl: Observable<any>;
  capacityOptions:any = [ 1, 2, 3, 4, 5, 6, 7, 8 ];
  @ViewChild(NgxInputFileUploadComponent)

  public NgxInputFileUploadComponent: NgxInputFileUploadComponent;
  acceptHtml="image/*"
  acceptTs=/image-*/
  activeColor: string = '#3366CC';
  submitted:boolean = false;
  post:any;
  path:string;
  location: string;
  postForm : FormGroup = this.formBuilder.group({
    postTitle: new FormControl('' ,Validators.compose([
      Validators.required,
      Validators.minLength(4),
      Validators.maxLength(50)
    ])),
    availability: new FormControl('' ,Validators.required),
    typeOption: new FormControl('' ,Validators.required),
    capacity: new FormControl('' ,Validators.required),
    addressToPickUp: new FormControl('' ,Validators.required),
    addressToDrop: new FormControl('' ,Validators.required),
    description: new FormControl('',Validators.required),
    priceDay: new FormControl('',Validators.required),
    priceHour: new FormControl('' ,Validators.required),
    phoneNumber: new FormControl('' ,Validators.compose([
      Validators.required,
      Validators.minLength(8),
      Validators.maxLength(15)
    ])),
  });
  filePorcentage = 0;
  userId:any;
  imageUrl:String = "";

  constructor(
    private service : PostService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private spinner: NgxSpinnerService,
    private cloud: AngularFireStorage) {
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
      let self = this;
      let autocomplete1 = new google.maps.places.Autocomplete(this.addressToDropRef.nativeElement, options);
			autocomplete1.addListener('place_changed', function() {
				self.postForm.get('addressToDrop').setValue(autocomplete1.getPlace().formatted_address);
			});
      let autocomplete2 = new google.maps.places.Autocomplete(this.addressToPickUpRef.nativeElement, options);
      autocomplete2.addListener('place_changed', function() {
        self.location = autocomplete2.getPlace().geometry.location.lat() +','+
                        autocomplete2.getPlace().geometry.location.lng();
        self.postForm.get('addressToPickUp').setValue(autocomplete2.getPlace().formatted_address);
      });
	}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      let isEdit = params["id"] !== undefined;
      if(isEdit) {
        this.isSave = false;
        this.userId = params["userId"];
        this.getPost(params['id']);

      }
      else {
        this.post = { "id":"", "title":"", "typeVehicle":"Auto", "location":"",
                    "description":"", "availability":"",
                    "photo":"", "capacity":"5", "addressToPickUp":"", "addressToDrop":"",
                    "priceDay":"", "priceHour":"", "phoneNumber":""};
        this.userId = params["userId"];
        this.setPostForm();
      }
    });
  }

  getPost(id) {
    this.spinner.show();
    this.service.getPost(id).subscribe(
      res => {
        this.post = res;
        this.spinner.hide();
        console.log(this.post);
        this.NgxInputFileUploadComponent.imageSrc = this.post.photo;
        this.setPostForm();
      },
      error => {console.log(error);this.spinner.hide();}
    );
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
    this.service.updatePost(post,this.userId).subscribe(
      res => {
          console.log(res);
          this.router.navigate(['post', post.id])
      },
      error => console.log(error)
    );
  }

  savePost(post){
    console.log(post);
    this.service.savePost(post,this.userId).subscribe(
      res => {
          console.log(res);
          this.handleSuccess(res);
      },
      err => {this.handleError(err);console.log(err)}
    );
  }

  volverAtras(){
    this.router.navigate(['']);
  }

  handleSuccess(response:any){
    this.spinner.hide();
    this.successModal=true;
    console.log(response);
    this.successModalMessage = response.body;
    jQuery(this.modal.nativeElement).modal('show');
  }

  handleError(response:any){
    this.spinner.hide();
    this.cloud.storage.ref(this.path).delete().then(val => console.log(val)).catch(err => console.log(err));
    this.errorNewPost = true;
    console.log(response);
    this.errorNewPostMessage = response.error;
  }

  save(){
    console.log(this.postForm.valid);
    if(this.postForm.valid) {
      let post = this.getPostToSave();
      if(this.isSave) {
        this.uploadAndSave(post);
      }
      else {
        this.updatePost(post)
      }
    }
  }

  onChange(){
    console.log(this.NgxInputFileUploadComponent.imageData);
  }

  uploadAndSave(post){
    this.spinner.show();
    this.path = 'post/' + this.userId + "/" + new Date();
    const customMetadata = {app: 'Carpnd'};
    const ref = this.cloud.ref(this.path);
    this.task = this.cloud.upload(this.path,
     this.NgxInputFileUploadComponent.imageData, { customMetadata });
          
     this.percentage = this.task.percentageChanges();
     this.percentage.subscribe(res=> {this.filePorcentage = res;console.log(res);},error => console.log(error));
     this.task.snapshotChanges().subscribe(res => res.ref.getDownloadURL().then(url =>{console.log(url);this.imageUrl = url;if(this.imageUrl != ""){this.post.photo = this.imageUrl;this.savePost(post)}}));

   
  }

  getPostToSave() {
    this.post.creator = null;
    this.post.title = this.postForm.get('postTitle').value;
    this.post.availability = this.postForm.get('availability').value;
    this.post.description = this.postForm.get('description').value;
    this.post.typeVehicle = this.postForm.get('typeOption').value;
    this.post.capacity = this.postForm.get('capacity').value;
    this.post.priceDay = this.postForm.get('priceDay').value;
    this.post.priceHour = this.postForm.get('priceHour').value;
    this.post.phoneNumber = this.postForm.get('phoneNumber').value;
    this.post.addressToPickUp = this.postForm.get('addressToPickUp').value;
    this.post.addressToDrop = this.postForm.get('addressToDrop').value;
    this.post.photo = this.imageUrl;
    this.post.location = this.location;
    return this.post;
  }

  onSubmit() {
    this.submitted = true;
  }

  back() {
    if(this.isSave) {
      this.router.navigate(['']);
    }
    else {
      this.router.navigate(['post', this.post.id]);
    }
  }
}
