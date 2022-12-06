import { Component, OnInit } from '@angular/core';
import {IBeerDto} from '../../../dto/i-beer-dto';
import {BehaviorSubject, Observable} from 'rxjs';
import {HomeService} from '../../../service/home.service';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  page = 1;
  pageSize = 5;
  beerListDto$: Observable<IBeerDto[]>;
  total$: Observable<number>;
  action: boolean;
  totalElement: number;
  beerNameSearch: string;
  constructor(private homeService: HomeService,
              private title: Title) {
    this.title.setTitle('THOR-BEER');
  }

  ngOnInit(): void {
    this.paginate();
  }
  paginate() {
    this.homeService.paginate(this.page, this.pageSize).subscribe(data => {
      if (data != null) {
        console.log(data.content);
        this.action = true;
        this.beerListDto$ = new BehaviorSubject<IBeerDto[]>(data.content);
        this.total$ = new BehaviorSubject<number>(data.totalElements);
        this.totalElement = data.totalElements;
      } else {
        this.action = false;
      }
    });
  }
  nextPage() {
    this.pageSize += 3;
    this.paginate();
  }
}
