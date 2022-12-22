import {Component, OnInit} from '@angular/core';
import {HomeService} from '../../../service/home.service';
import {TokenStorageService} from '../../../service/token-storage.service';
import {ICartDto} from '../../../dto/i-cart-dto';

@Component({
  selector: 'app-history-shopping',
  templateUrl: './history-shopping.component.html',
  styleUrls: ['./history-shopping.component.css']
})
export class HistoryShoppingComponent implements OnInit {
  username: string;
  historyCartList: ICartDto[];
  action: boolean;

  constructor(private homeService: HomeService,
              private tokenService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.username = this.tokenService.getUser().username;
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

}
