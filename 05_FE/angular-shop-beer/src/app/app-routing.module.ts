import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DecentralizationModule} from './all-component/decentralization/decentralization.module';
import {HomeModule} from './all-component/home/home.module';
import {HeaderComponent} from './all-component/home/header/header.component';


const routes: Routes = [
  {path: '', loadChildren: () => HomeModule},
  {path: 'login', loadChildren: () => DecentralizationModule},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
