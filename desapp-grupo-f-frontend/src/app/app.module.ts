import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';


import { AppComponent } from './app.component';
import { PostsComponent } from './posts/posts.component';
import { EjemploGmapsComponent } from './ejemplo-gmaps/ejemplo-gmaps.component';


@NgModule({
  declarations: [
    AppComponent,
    PostsComponent,
    EjemploGmapsComponent
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
    		}
    	])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
