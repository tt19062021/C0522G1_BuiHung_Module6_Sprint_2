import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DecentralizationRoutingModule } from './decentralization-routing.module';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';



@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    DecentralizationRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
  ]
})
export class DecentralizationModule { }
