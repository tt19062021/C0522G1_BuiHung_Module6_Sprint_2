import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {IBeerDto} from '../../../dto/i-beer-dto';
import {HomeService} from '../../../service/home.service';
import {Title} from '@angular/platform-browser';
import {Router} from '@angular/router';
import {ICartDto} from '../../../dto/i-cart-dto';
import {TokenStorageService} from '../../../service/token-storage.service';
import Swal from 'sweetalert2';

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
username: string;
  constructor(private homeService: HomeService,
              private title: Title,
              private router: Router,
              private tokenService: TokenStorageService) {
    this.title.setTitle('THOR-BEER');
  }

  ngOnInit(): void {
    this.paginate();
    this.getCustomer();
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
  getCustomer(): void {
    this.username = this.tokenService.getUser().username;
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
    if (this.username == null) {
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'Bạn chưa đăng nhập, vui lòng đăng nhập trước !',
        showConfirmButton: false,
        timer: 2000
      });
      this.router.navigateByUrl('/login');
    } else {
      this.homeService.updateCart(item, this.username).subscribe(() => {
        console.log(item);
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Thêm vào giỏ hàng thành công',
          showConfirmButton: false,
          timer: 1500
        });
      });
    }
  }
}
