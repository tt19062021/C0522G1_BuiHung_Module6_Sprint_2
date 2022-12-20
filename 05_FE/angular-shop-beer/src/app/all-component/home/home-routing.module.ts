import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PageHomeComponent} from './page-home/page-home.component';
import {BeerDetailComponent} from './beer-detail/beer-detail.component';
import {BeerCartComponent} from './beer-cart/beer-cart.component';


const routes: Routes = [
  {path: '', component: PageHomeComponent},
  {path: 'detail/:id', component: BeerDetailComponent},
  {path: 'cart', component: BeerCartComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
