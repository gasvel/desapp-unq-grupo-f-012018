import { Component, OnInit, ViewChild } from '@angular/core';
import { NgxInputFileUploadComponent } from 'ngx-input-file-upload'
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms'
import { Post } from '../../model/post'

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {
  imagePost:any = "assets/img/no-image.jpg";
  typeOptions:any = ["Moto", "Camioneta", "Auto", "Furgoneta"];
  capacityOptions:any = [ 1, 2, 3, 4, 5, 6, 7, 8 ];
  @ViewChild(NgxInputFileUploadComponent)
  private NgxInputFileUploadComponent: NgxInputFileUploadComponent;
  acceptHtml="image/*"
  acceptTs=/image-*/
  activeColor: string = '#3366CC';
  postForm : FormGroup = this.formBuilder.group({
    postTitle: new FormControl('',Validators.compose([
      Validators.required,
      Validators.minLength(4),
      Validators.maxLength(50)
    ])),
    availability: new FormControl('',Validators.required),
    typeOption: new FormControl('',Validators.required),
    capacity: new FormControl('',Validators.required),
    addressToPickUp: new FormControl('',Validators.required),
    addressToDrop: new FormControl('',Validators.required),
    description: new FormControl('',Validators.required),
    priceDay: new FormControl('',Validators.required),
    priceHour: new FormControl('',Validators.required),
    phoneNumber: new FormControl('',Validators.compose([
      Validators.required,
      Validators.minLength(8),
      Validators.maxLength(15)
    ])),
  });


  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit() {
  }

  save(){
    console.log(this.postForm.valid);
    if(this.postForm.valid) {
      let post = new Post();
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
      console.log(post);
    }
  }
}
