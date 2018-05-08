import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { PostsComponent } from './posts/post-list/posts.component';
import { EjemploGmapsComponent } from './ejemplo-gmaps/ejemplo-gmaps.component';
import { NuevoUserComponent } from './nuevo-user/nuevo-user.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CreatePostComponent } from './posts/create-post/create-post.component';
import { NgxInputFileUploadModule } from 'ngx-input-file-upload'
import { UsersService } from './services/users.service';
import { PostService } from './services/post.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    PostsComponent,
    EjemploGmapsComponent,
    NuevoUserComponent,
    CreatePostComponent
  ],
  imports: [
    BrowserModule,
    NgxInputFileUploadModule,
    RouterModule.forRoot([
    		{
    			path:'posts',
    			component:PostsComponent
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
