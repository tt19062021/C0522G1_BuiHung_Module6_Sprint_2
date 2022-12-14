import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home/home.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [HomeComponent,
    HeaderComponent,
    FooterComponent
  ],
  exports: [
    FooterComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class HomeModule { }
