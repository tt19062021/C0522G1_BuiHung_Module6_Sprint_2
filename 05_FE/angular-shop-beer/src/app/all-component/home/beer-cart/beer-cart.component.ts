import {Component, OnInit} from '@angular/core';
import Swal from 'sweetalert2';
import {HomeService} from '../../../service/home.service';
import {Router} from '@angular/router';
import {ICartDto} from '../../../dto/i-cart-dto';
import {render} from 'creditcardpayments/creditCardPayments';


@Component({
  selector: 'app-beer-cart',
  templateUrl: './beer-cart.component.html',
  styleUrls: ['./beer-cart.component.css']
})
export class BeerCartComponent implements OnInit {
  cart: ICartDto[];
  totalBill: number;

  constructor(private homeService: HomeService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAllCart();
    this.getTotalBill();
  }
  payPaltoBill() {
    render({
      id: '#myPayPal',
      currency: 'USD',
      value: '100.00',
      onApprove: details => {
        alert('Transaction Successful');
      }
    });
  }

  getAllCart() {
    this.homeService.getCartList().subscribe(value => {
      console.log(value);
      this.cart = value;
    });
  }

  getTotalBill() {
    this.homeService.getTotalBill().subscribe(value => {
      console.log(value);
      this.totalBill = value.totalBill;
    });
  }

  updateQty(cart: ICartDto) {
    console.log(cart);
    this.homeService.updateQty(cart).subscribe(value => {
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
            timer: 2300,
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
