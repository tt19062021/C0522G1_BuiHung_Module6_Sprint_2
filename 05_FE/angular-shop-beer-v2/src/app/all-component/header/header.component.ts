import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../../../service/token-storage.service';
import {HomeService} from '../../../service/home.service';
import {ActivatedRoute, Router} from '@angular/router';
import Swal from 'sweetalert2';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SocialAuthService, SocialUser} from 'angularx-social-login';
import {AuthenticationService} from '../../../service/authentication.service';
import {AuthService} from '../../../service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  username: string;
  roles: string[] = [];
  isCustomer = false;
  isAdmin = false;
  isEmployee = false;

  constructor(private router: Router,
              private tokenService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.username = '';
    this.showUsername();
    // window.scroll({
    //   top: 0,
    //   left: 0,
    //   behavior: 'smooth'
    // });
  }

  showUsername() {
    this.username = this.tokenService.getUser().username;
    this.roles = this.tokenService.getUser().roles;
    this.isCustomer = this.roles.indexOf('ROLE_CUSTOMER') !== -1;
    this.isEmployee = this.roles.indexOf('ROLE_EMPLOYEE') !== -1;
    this.isAdmin = this.roles.indexOf('ROLE_ADMIN') !== -1;
  }

  whenLogout() {
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: ' Đăng xuất thành công !',
      showConfirmButton: false,
      timer: 1000
    });
    this.tokenService.logOut();
    this.router.navigateByUrl('');
    this.username = '';
    this.isCustomer = false;
    this.isEmployee = false;
    this.isAdmin = false;
  }

}
