import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HomeService} from '../../../service/home.service';
import {IBeerDto} from '../../../dto/i-beer-dto';
import {Title} from '@angular/platform-browser';
import {IImage} from '../../../model/i-image';
import {ICustomer} from '../../../model/i-customer';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-beer-detail',
  templateUrl: './beer-detail.component.html',
  styleUrls: ['./beer-detail.component.css']
})
export class BeerDetailComponent implements OnInit {
  id: number;
  beerList: IBeerDto;
  imageList: IImage[];
  quantityChoose = 1;
  customer: ICustomer;
  idUser: number;
  beerIdChoose = 0;

  constructor(private activatedRoute: ActivatedRoute,
              private homeService: HomeService,
              private title: Title,
              private router: Router) {
    this.title.setTitle('Chi tiết beer');
  }

  ngOnInit(): void {
    this.id = Number(this.activatedRoute.snapshot.params.id);
    this.getBeerIdDetail();
    this.getImageBeer();
    window.scroll(0, 0);
  }

  getBeerIdDetail() {
    this.homeService.findById(this.id).subscribe(value => {
      // console.log(this.id);
      this.beerList = value;
    });
  }

  getImageBeer() {
    this.homeService.findAllImage(this.id).subscribe(value => {
      // console.log(this.id);
      this.imageList = value;
    });
  }

  descQuantity(): void {
    this.quantityChoose--;
  }

  ascQuantity(): void {
    this.quantityChoose++;
  }


}
