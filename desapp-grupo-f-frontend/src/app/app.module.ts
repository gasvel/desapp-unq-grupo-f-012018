import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';


import { AppComponent } from './app.component';
import { PostsComponent } from './posts/posts.component';


@NgModule({
  declarations: [
    AppComponent,
    PostsComponent
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
    		}
    	])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
