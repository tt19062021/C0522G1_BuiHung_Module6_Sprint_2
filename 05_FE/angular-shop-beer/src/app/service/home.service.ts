import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {SearchResult} from '../model/search-result';
import {Observable} from 'rxjs';
import {IBeerDto} from '../dto/i-beer-dto';
import {IImage} from '../model/i-image';
import {ICustomer} from '../model/i-customer';
import {ICart} from '../model/i-cart';
import {ICartDto} from '../dto/i-cart-dto';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  httpOptions: any;
  isLoggedIn: boolean | undefined;
  private API_BEER = environment.api_url;

  constructor(private httpClient: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    };
  }

  login(obj: { username: string; password: string; }): Observable<any> {
    return this.httpClient.post(this.API_BEER + '/beer/login', {
      username: obj.username,
      password: obj.password
    }, this.httpOptions);
  }

  findAllBeer(page: number, limit: number, nameSearch: string,
              startPrice: number, endPrice: number): Observable<SearchResult<IBeerDto>> {
    console.log(this.API_BEER + '/beer/list?page='
      + (page - 1) + '&size=' + limit + '&nameSearch=' + nameSearch +
      '&startPrice=' + startPrice + '&endPrice=' + endPrice);
    return this.httpClient.get<SearchResult<IBeerDto>>(this.API_BEER + '/beer/list?page='
      + (page - 1) + '&size=' + limit + '&nameSearch=' + nameSearch +
      '&startPrice=' + startPrice + '&endPrice=' + endPrice);
  }

  findById(id: number): Observable<IBeerDto> {
    return this.httpClient.get<IBeerDto>(this.API_BEER + '/beer/find-id-beer/' + id);
  }

  findAllImage(id: number): Observable<IImage[]> {
    return this.httpClient.get<IImage[]>(this.API_BEER + '/beer/find-all-image/' + id);
  }

  getCartList(): Observable<ICartDto[]> {
    return this.httpClient.get<ICartDto[]>(this.API_BEER + '/beer/cart');
  }

  getTotalBill(): Observable<ICartDto> {
    return this.httpClient.get<ICartDto>(this.API_BEER + '/beer/total-bill');
  }

  updateCart(beerDto: IBeerDto): Observable<void> {
    return this.httpClient.post<void>(this.API_BEER + '/beer/cart-update' + '?id=' + beerDto.beerId, beerDto);
  }

  updateQty(cartDto: ICartDto): Observable<void> {
    return this.httpClient.patch<void>(this.API_BEER + '/beer/qty-update' + '?id=' + cartDto.id + '&quantity=' + cartDto.quantity, cartDto);
  }

  removeProduct(id: number): Observable<void> {
    return this.httpClient.get<void>(this.API_BEER + '/beer/remove-cart/' + id);
  }
}
