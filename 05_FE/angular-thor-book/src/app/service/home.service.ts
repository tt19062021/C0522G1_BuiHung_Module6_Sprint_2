import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {SearchResult} from '../model/search-result';
import {Observable} from 'rxjs';
import {IBeerDto} from '../dto/i-beer-dto';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  private API_BEER = environment.api_url;
  constructor(private http: HttpClient) { }
  paginate(page: number, limit: number): Observable<SearchResult<IBeerDto>> {
    return this.http.get<SearchResult<IBeerDto>>(this.API_BEER + '/beer/list?page=' + (page - 1) + '&size=' + limit);
  }
}

