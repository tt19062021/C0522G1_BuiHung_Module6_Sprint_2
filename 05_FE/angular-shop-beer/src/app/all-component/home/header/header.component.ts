import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../../../service/token-storage.service';
import {HomeService} from '../../../service/home.service';
import {ActivatedRoute, Router} from '@angular/router';
import Swal from 'sweetalert2';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {GoogleLoginProvider, SocialAuthService, SocialUser} from 'angularx-social-login';
import {AuthenticationService} from '../../../service/authentication.service';
import {AuthService} from '../../../service/auth.service';
import {ShareService} from '../../../service/share.service';
import {JwtResponseService} from '../../../service/jwt-response-service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  formGroup: FormGroup;
  username: string;
  roles: string[] = [];
  isCustomer = false;
  isAdmin = false;
  isEmployee = false;
  returnUrl: string;
  socialUser: SocialUser;
  constructor(private router: Router,
              private tokenService: TokenStorageService,
              private authSocialService: SocialAuthService,
              private auth: AuthenticationService,
              private ref: ChangeDetectorRef,
              private formBuild: FormBuilder,
              private authService: AuthService,
              private route: ActivatedRoute,
              private shareService: ShareService) {
    this.formGroup = this.formBuild.group({
        username: [''],
        password: [''],
        rememberMe: ['']
      }
    );
  }

  ngOnInit(): void {
    this.username = '';
    this.showUsername();
    // window.scroll({
    //   top: 0,
    //   left: 0,
    //   behavior: 'smooth'
    // });
    this.returnUrl = this.route.snapshot.queryParams.returnUrl || '';
    this.formGroup = this.formBuild.group({
        username: ['', Validators.required],
        password: ['', Validators.required],
        remember_me: ['']
      }
    );

    if (this.tokenService.getToken()) {
      const user = this.tokenService.getUser();
      this.authService.isLoggedIn = true;
      this.roles = this.tokenService.getUser().roles;
      this.username = this.tokenService.getUser().username;
    }
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
  onSubmit() {
    this.authService.login(this.formGroup.value).subscribe(
      data => {
        if (this.formGroup.value.remember_me) {
          this.tokenService.saveTokenLocal(data.accessToken);
          this.tokenService.saveUserLocal(data);
        } else {
          this.tokenService.saveTokenSession(data.accessToken);
          this.tokenService.saveUserLocal(data);
        }

        this.authService.isLoggedIn = true;
        this.username = this.tokenService.getUser().username;
        this.roles = this.tokenService.getUser().roles;
        this.formGroup.reset();

        Swal.fire({
          position: 'center',
          icon: 'success',
          title: this.username + ' Đăng nhập thành công !',
          showConfirmButton: false,
          timer: 2000
        });
        window.location.replace('');
      },
      err => {
        this.authService.isLoggedIn = false;
        Swal.fire({
          position: 'center',
          icon: 'warning',
          title: 'Đăng nhập thất bại, Sai tài khoản hoặc mật khẩu !',
          showConfirmButton: false,
          timer: 2000
        });
      }
    );
  }

  signInWithGoogle(): void {
    this.authSocialService.signIn(GoogleLoginProvider.PROVIDER_ID).then(data => {
      this.socialUser = data;
      const tokenGoogle = new JwtResponseService(this.socialUser.idToken);
      this.auth.google(tokenGoogle).subscribe(req => {
          if (req.token === '') {
            this.tokenService.saveUser(req.user);
          } else {
            this.tokenService.saveTokenLocal(req.token);
            req.username = null;
            this.tokenService.saveUserLocal(req.user);
            this.tokenService.saveUserLocal(req.username);
            this.router.navigateByUrl('');
          }
        },
        error => {
          console.log(error);
          this.logOut();
        });
    }).catch(
      err => {
        console.log(err);
      }
    );
  }

  logOut(): void {
    this.authSocialService.signOut().then(
      data => {
        this.tokenService.logOut();
        this.router.navigateByUrl('');
      }
    );
  }

  exit() {
    this.router.navigateByUrl('');
  }

}
