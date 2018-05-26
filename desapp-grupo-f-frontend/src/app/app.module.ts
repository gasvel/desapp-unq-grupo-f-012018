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
import { SharedSearchFilterService } from './services/shared-search-filter.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import {MatDialogModule, MatMenuModule} from "@angular/material";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { DeleteDialogComponent } from './posts/delete-dialog/delete-dialog.component';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { EditarUserComponent } from './editar-user/editar-user.component';
import { LoginUserComponent } from './login-user/login-user.component';
import { LoginService } from './services/login.service';
import { PipesModule } from './pipes/pipes.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { AltaCreditosComponent } from './alta-creditos/alta-creditos.component';
import { RetiroCreditosComponent } from './retiro-creditos/retiro-creditos.component';
import {
    SocialLoginModule,
    AuthServiceConfig,
    GoogleLoginProvider
} from "angular5-social-login";

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

export function getAuthServiceConfigs() {
  let config = new AuthServiceConfig(
      [
        {
          id: GoogleLoginProvider.PROVIDER_ID,
          provider: new GoogleLoginProvider("663771392055-5epsk5veaf196v3qmnsnjuo9f1erndeb.apps.googleusercontent.com")
        }
      ]
  );
  return config;
}

@NgModule({
  declarations: [
    AppComponent,
    PostsComponent,
    EjemploGmapsComponent,
    NuevoUserComponent,
    CreatePostComponent,
    PostDetailComponent,
    DeleteDialogComponent,
    EditarUserComponent,
    LoginUserComponent,
    AltaCreditosComponent,
    RetiroCreditosComponent,

  ],
  imports: [
    BrowserModule,
    GooglePlaceModule,
    SocialLoginModule,
    MatMenuModule,
    MatDialogModule,
    BrowserAnimationsModule,
    NgxInputFileUploadModule,
    RouterModule.forRoot([
    		{
    			path:'posts',
    			component:PostsComponent
    		},
        {
    			path:'post/:id',
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
					path:'editUser',
					component:EditarUserComponent
				}
				,
        {
					path:'newPost',
					component:CreatePostComponent
				},
        {
					path:'editPost/:id',
					component:CreatePostComponent
				},
				{
					path:'login',
					component: LoginUserComponent
				},
				{
					path:'credits/add/:userId/:credits',
					component:AltaCreditosComponent
				},
				{
					path:'credits/extract/:userId/:credits',
					component: RetiroCreditosComponent
				}
			]),
			TranslateModule.forRoot({
				loader: {
					provide: TranslateLoader,
					useFactory: (createTranslateLoader),
					deps: [HttpClient]
				}
			}),
			ReactiveFormsModule,
			FormsModule,
			HttpClientModule,
			PipesModule,
			NgxPaginationModule
  ],
  providers: [
    UsersService,
    PostService,
    LoginService,
    SharedSearchFilterService,
    {
      provide: AuthServiceConfig,
      useFactory: getAuthServiceConfigs
    }
  ],
  bootstrap: [AppComponent],
  entryComponents: [DeleteDialogComponent]
})
export class AppModule { }
