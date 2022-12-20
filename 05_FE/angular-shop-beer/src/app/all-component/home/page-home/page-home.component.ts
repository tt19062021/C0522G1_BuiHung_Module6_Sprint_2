import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {IBeerDto} from '../../../dto/i-beer-dto';
import {HomeService} from '../../../service/home.service';
import {Title} from '@angular/platform-browser';
import {Router} from '@angular/router';
import {ICartDto} from '../../../dto/i-cart-dto';

@Component({
  selector: 'app-page-home',
  templateUrl: './page-home.component.html',
  styleUrls: ['./page-home.component.css']
})
export class PageHomeComponent implements OnInit {

  page = 1;
  pageSize = 12;
  beerListDto$: Observable<IBeerDto[]>;
  total$: Observable<number>;
  action: boolean;
  beerNameSearch = '';
  endPrice = 0;
  startPrice = 0;
  price = 0;
  startAlcohol = 0;
  endAlcohol = 0;
  alcohol = '';
  item: ICartDto[];
  constructor(private homeService: HomeService,
              private title: Title,
              private router: Router) {
    this.title.setTitle('THOR-BEER');
  }

  ngOnInit(): void {
    this.paginate();
  }

  paginate() {
    this.homeService.findAllBeer(this.page, this.pageSize, this.beerNameSearch,
      this.startPrice, this.endPrice).subscribe(data => {
      if (data != null) {
        // console.log(data.content);
        this.action = true;
        this.beerListDto$ = new BehaviorSubject<IBeerDto[]>(data.content);
        this.total$ = new BehaviorSubject<number>(data.totalElements);
      } else {
        this.action = false;
      }
    });
  }

  getIdBeer(beerId: number) {
    // console.log(beerId + 'ok');
    this.router.navigateByUrl('/detail/' + beerId);
  }

  nextPage() {
    this.pageSize += 4;
    this.paginate();
  }
  searchByPrice() {
    console.log(this.price);
    switch (this.price) {
      case 1:
        this.startPrice = 0;
        this.endPrice = 199999;
        break;
      case 2:
        this.startPrice = 200000;
        this.endPrice = 399999;
        break;
      case 3:
        this.startPrice = 400000;
        this.endPrice = 599999;
        break;
      case 4:
        this.startPrice = 600000;
        this.endPrice = 799999;
        break;
      case 5:
        this.startPrice = 800000;
        this.endPrice = 999999;
        break;
      case 6:
        this.startPrice = 1000000;
        this.endPrice = 10000000;
        break;
      default:
        this.startPrice = 0;
        this.endPrice = 0;
        break;
    }
    this.paginate();
  }

  // searchByAlcohol() {
  //   switch (Number(this.alcohol)) {
  //     case 1:
  //       this.endAlcohol = 2.9;
  //       break;
  //     case 2:
  //       this.startAlcohol = 3.0;
  //       this.endAlcohol = 3.9;
  //       break;
  //     case 3:
  //       this.startAlcohol = 4.0;
  //       this.endAlcohol = 4.9;
  //       break;
  //     case 4:
  //       this.startAlcohol = 5.0;
  //       break;
  //     default:
  //       this.startAlcohol = 0.0;
  //       this.endAlcohol = 0.0;
  //       break;
  //   }
  //   this.paginateByAlcohol();
  // }
  addToCart(item: IBeerDto) {
    this.homeService.updateCart(item).subscribe(() => {
      // this.messageService.add({severity: 'success', summary: 'Success', detail: 'Add successfully'});
    });
  }
}
