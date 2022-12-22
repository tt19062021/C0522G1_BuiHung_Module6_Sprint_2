import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PageHomeComponent} from './page-home/page-home.component';
import {BeerDetailComponent} from './beer-detail/beer-detail.component';
import {BeerCartComponent} from './beer-cart/beer-cart.component';
import {HistoryShoppingComponent} from './history-shopping/history-shopping.component';


const routes: Routes = [
  {path: '', component: PageHomeComponent},
  {path: 'detail/:id', component: BeerDetailComponent},
  {path: 'cart', component: BeerCartComponent},
  {path: 'history', component: HistoryShoppingComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
