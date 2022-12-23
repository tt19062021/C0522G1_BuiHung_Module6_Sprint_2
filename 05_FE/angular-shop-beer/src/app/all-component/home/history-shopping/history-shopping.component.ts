import {Component, OnInit} from '@angular/core';
import {HomeService} from '../../../service/home.service';
import {TokenStorageService} from '../../../service/token-storage.service';
import {ICartDto} from '../../../dto/i-cart-dto';
import {ICustomerDto} from '../../../dto/i-customer-dto';

@Component({
  selector: 'app-history-shopping',
  templateUrl: './history-shopping.component.html',
  styleUrls: ['./history-shopping.component.css']
})
export class HistoryShoppingComponent implements OnInit {
  username: string;
  historyCartList: ICartDto[];
  customerDto: ICustomerDto;
  action: boolean;
  name: string;

  constructor(private homeService: HomeService,
              private tokenService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.getCustomer();
    this.getHistory();
    this.getCustomerInfo();
  }

  getHistory() {
    this.homeService.history(this.username).subscribe(value => {
      if (value != null) {
        this.action = true;
        this.historyCartList = value;
      } else {
        console.log(this.historyCartList);
        this.action = false;
      }
    });
  }

  getCustomerInfo() {
    this.homeService.customerDetail(this.username).subscribe(value => {
      console.log(value);
      this.customerDto = value;
    });
  }
  getInfoCustomer(name: string) {
    this.username = this.tokenService.getUser().name;
  }

  getCustomer() {
    this.username = this.tokenService.getUser().username;

  }
}
