import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {GoogleLoginProvider, SocialAuthServiceConfig, SocialLoginModule} from 'angularx-social-login';
import {HomeModule} from './all-component/home/home.module';

const googleLoginOptions = {
  scope: 'profile email',
  plugin_name: 'login'
};

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HomeModule,
    HttpClientModule,
    SocialLoginModule
  ],
  providers: [ {
    provide: 'SocialAuthServiceConfig',
    useValue: {
      autoLogin: false,
      providers: [
        {
          id: GoogleLoginProvider.PROVIDER_ID,
          provider: new GoogleLoginProvider(
            '612774287153-uthnsrl25on17doe8413il68ebv9c969.apps.googleusercontent.com',
            googleLoginOptions
          )
        },
      ]
    } as SocialAuthServiceConfig,
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
