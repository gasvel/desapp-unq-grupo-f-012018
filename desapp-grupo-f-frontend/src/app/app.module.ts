import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GooglePlaceModule } from "ngx-google-places-autocomplete";

import { AppComponent } from './app.component';
import { PostsComponent } from './posts/post-list/posts.component';
import { PostDetailComponent } from './posts/post-detail/post-detail.component';
import { EjemploGmapsComponent } from './ejemplo-gmaps/ejemplo-gmaps.component';
import { NuevoUserComponent } from './nuevo-user/nuevo-user.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CreatePostComponent } from './posts/create-post/create-post.component';
import { NgxInputFileUploadModule } from 'ngx-input-file-upload'
import { UsersService } from './services/users.service';
import { PostService } from './services/post.service';
import { HttpClientModule } from '@angular/common/http';
import {MatMenuModule} from '@angular/material/';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'

@NgModule({
  declarations: [
    AppComponent,
    PostsComponent,
    EjemploGmapsComponent,
    NuevoUserComponent,
    CreatePostComponent,
    PostDetailComponent
  ],
  imports: [
    BrowserModule,
    GooglePlaceModule,
    MatMenuModule,
    BrowserAnimationsModule,
    NgxInputFileUploadModule,
    RouterModule.forRoot([
    		{
    			path:'posts',
    			component:PostsComponent
    		},
        {
    			path:'post/:title',
    			component:PostDetailComponent
    		},
    		{

    			path:'',
    			redirectTo:'/posts',
    			pathMatch:'full'
    		},
    		{
    			path:'ejemploGMaps',
    			component:EjemploGmapsComponent
				},
				{
					path:'registro',
					component:NuevoUserComponent
				},
        {
					path:'newPost',
					component:CreatePostComponent
				}
			]),
			ReactiveFormsModule,
			FormsModule,
			HttpClientModule
  ],
  providers: [UsersService,PostService],
  bootstrap: [AppComponent]
})
export class AppModule { }
