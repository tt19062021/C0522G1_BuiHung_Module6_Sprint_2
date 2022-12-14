import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {SearchResult} from '../model/search-result';
import {Observable} from 'rxjs';
import {IBeerDto} from '../dto/i-beer-dto';

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

  paginate(page: number, limit: number, nameSearch: string): Observable<SearchResult<IBeerDto>> {

    return this.httpClient.get<SearchResult<IBeerDto>>(this.API_BEER + '/beer/list?page='
      + (page - 1) + '&size=' + limit + '&nameSearch=' + nameSearch);
  }
}

