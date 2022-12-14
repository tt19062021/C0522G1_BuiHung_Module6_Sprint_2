import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { PageHomeComponent } from './page-home/page-home.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BeerDetailComponent } from './beer-detail/beer-detail.component';


@NgModule({
  declarations: [FooterComponent, HeaderComponent, PageHomeComponent, BeerDetailComponent],
  exports: [
    FooterComponent, HeaderComponent, PageHomeComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class HomeModule { }
