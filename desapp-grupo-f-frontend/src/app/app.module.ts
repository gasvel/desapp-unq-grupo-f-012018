import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';


import { AppComponent } from './app.component';
import { PostsComponent } from './posts/posts.component';
import { EjemploGmapsComponent } from './ejemplo-gmaps/ejemplo-gmaps.component';
import { NuevoUserComponent } from './nuevo-user/nuevo-user.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    PostsComponent,
    EjemploGmapsComponent,
    NuevoUserComponent
  ],
  imports: [
    BrowserModule,
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
				}
			]),
			ReactiveFormsModule,
			FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
