import {Component, OnInit} from '@angular/core';
import Swal from 'sweetalert2';
import {HomeService} from '../../../service/home.service';
import {Router} from '@angular/router';
import {ICartDto} from '../../../dto/i-cart-dto';
import {render} from 'creditcardpayments/creditCardPayments';
import {TokenStorageService} from '../../../service/token-storage.service';
import {IBeerDto} from '../../../dto/i-beer-dto';


@Component({
  selector: 'app-beer-cart',
  templateUrl: './beer-cart.component.html',
  styleUrls: ['./beer-cart.component.css']
})
export class BeerCartComponent implements OnInit {
  cart: ICartDto[];
  totalBill: number;
  username: string;
  action = true;

  constructor(private homeService: HomeService,
              private router: Router,
              private tokenService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.username = this.tokenService.getUser().username;
    this.getAllCart();
    this.getTotalBill();
  }

  paidBill() {
    this.homeService.payment(this.username).subscribe(value => {
      console.log(this.username);
    });
  }

  payment(): void {
    this.action = false;
    render(
      {
        id: '#myPaypal',
        value: String((this.totalBill / 23000).toFixed(2)),
        currency: 'VND',
        onApprove: (details) => {
          this.paidBill();
          Swal.fire({
            position: 'center',
            icon: 'success',
            text: 'Cảm ơn quý khách !',
            timer: 2000,
            title: 'Đã thanh toán thành công',
            showConfirmButton: false,
          }).then(r => window.location.reload());
        }
      }
    );
  }

  getAllCart() {
    this.homeService.getCartList(this.username).subscribe(value => {
      console.log(value);
      this.cart = value;
    });
  }

  getTotalBill() {
    this.homeService.getTotalBill(this.username).subscribe(value => {
      console.log(value);
      this.totalBill = value.totalBill;
    });
  }

  updateQty(cartDto: ICartDto) {
    this.homeService.updateQty(cartDto, this.username).subscribe(value => {
      // this.getAllCart();
      // this.getTotalBill();
      this.ngOnInit();
    });
  }

  removeCart(id: number): void {
    Swal.fire({
      title: 'Bạn có chắc?',
      text: 'Xóa sản phẩm này khỏi giỏ hàng!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Có, tôi muốn xóa!',
      cancelButtonText: 'Đóng'
    }).then((result) => {
      if (result.isConfirmed) {
        this.homeService.removeProduct(id).subscribe(() => {
          const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer);
              toast.addEventListener('mouseleave', Swal.resumeTimer);
            }
          });

          Toast.fire({
            icon: 'success',
            title: 'Xóa khỏi giỏ hàng thành công!'
          });

          location.reload();
        }, error => {
          console.log(error);
        });
      }
    });
  }
}
