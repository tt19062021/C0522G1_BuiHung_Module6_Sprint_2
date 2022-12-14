import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeRoutingModule} from './all-component/home/home-routing.module';


const routes: Routes = [
  {path: 'home', loadChildren: () => HomeRoutingModule}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
