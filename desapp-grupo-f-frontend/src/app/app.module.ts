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
import { SocialLoginModule, AuthServiceConfig } from "angularx-social-login";
import { GoogleLoginProvider } from "angularx-social-login";
import { ReservationComponent } from './reservation/reservation.component';
import { ReservationService } from './services/reservation.service';
import { HttpModule } from '@angular/http';
import {CalendarModule} from 'primeng/calendar';
import { PostMapComponent } from './posts/post-map/post-map.component';
import { ConfirmationsComponent } from './confirmations/confirmations.component';
import { NgxSpinnerModule } from 'ngx-spinner';
import { LOCALE_ID } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/es-AR';
import {environment} from '../environments/environment';
import { AngularFireModule } from 'angularfire2';
import { AngularFireStorageModule } from 'angularfire2/storage';
import { RentService } from './services/rent.service';
import { InfoCuentaComponent } from './info-cuenta/info-cuenta.component';


registerLocaleData(localePt);

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

let config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider("663771392055-5epsk5veaf196v3qmnsnjuo9f1erndeb.apps.googleusercontent.com")
  }
]);

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
    ReservationComponent,
    PostMapComponent,
    ConfirmationsComponent,
    InfoCuentaComponent

  ],
  imports: [
    BrowserModule,
    GooglePlaceModule,
    SocialLoginModule.initialize(config),
		MatMenuModule,
		AngularFireModule.initializeApp(environment.firebase),
		AngularFireStorageModule,
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
					path:'reservation/:id',
					component: ReservationComponent
				},
				{
					path:'confirmations',
					component: ConfirmationsComponent
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
					path:'editUser/:id',
					component:EditarUserComponent
				},
				{
					path:'infoCuenta/:id',
					component:InfoCuentaComponent
				},
        {
					path:'newPost/:userId',
					component:CreatePostComponent
				},
        {
					path:'editPost/:id/:userId',
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
				},
        {
					path:'posts/map',
					component: PostMapComponent
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
			NgxPaginationModule,
			HttpModule,
			CalendarModule,
			NgxSpinnerModule
  ],
  providers: [
    UsersService,
    PostService,
		LoginService,
		RentService,
		SharedSearchFilterService,
		ReservationService,
    { provide: LOCALE_ID, useValue: 'es-AR' }
  ],
  bootstrap: [AppComponent],
  entryComponents: [DeleteDialogComponent]
})
export class AppModule { }
