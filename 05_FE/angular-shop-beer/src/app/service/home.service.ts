import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {SearchResult} from '../model/search-result';
import {Observable} from 'rxjs';
import {IBeerDto} from '../dto/i-beer-dto';
import {IImage} from '../model/i-image';
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

  getCartList(username: string): Observable<ICartDto[]> {
    return this.httpClient.get<ICartDto[]>(this.API_BEER + '/beer/cart' + '?username=' + username);
  }

  getTotalBill(username: string): Observable<ICartDto> {
    console.log(this.API_BEER + '/beer/total-bill' + '?username=' + username);
    return this.httpClient.get<ICartDto>(this.API_BEER + '/beer/total-bill' + '?username=' + username);
  }

  updateCart(ibeerDto: IBeerDto, username: string): Observable<void> {
    return this.httpClient.post<void>(this.API_BEER + '/beer/cart-update' + '?id=' + ibeerDto.beerId + '&username=' + username, ibeerDto);
  }

  updateQty(cartDto: ICartDto, username: string): Observable<void> {
    return this.httpClient.patch<void>(this.API_BEER + '/beer/qty-update' + '?id=' + cartDto.beerId +
      '&quantity=' + cartDto.quantity + '&username=' + username, cartDto);
  }

  removeProduct(id: number): Observable<void> {
    return this.httpClient.get<void>(this.API_BEER + '/beer/remove-cart/' + id);
  }

  payment(username: string): Observable<void> {
    return this.httpClient.get<void>(this.API_BEER + '/beer/payment/' + username);
  }
  history(username: string): Observable<ICartDto[]> {
    return this.httpClient.get<ICartDto[]>(this.API_BEER + '/beer/history/' + username);
  }
}
