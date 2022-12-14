import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PageHomeComponent} from './page-home/page-home.component';
import {HeaderComponent} from './header/header.component';


const routes: Routes = [
  {path: '', component: PageHomeComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
