import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TweetPostEntity } from '../model/TweetPostEntity';


@Injectable({
  providedIn: 'root'
})
export class JwtClientService {

  constructor(private httpClient: HttpClient) { }


  public generateToken(request:any) {
    return this.httpClient.post<string>("http://localhost:8080/api/v1.0/tweets/authenticate", request, {  responseType: 'text' as 'json' });
  }


  public welcome(token:JSON) {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.httpClient.get<TweetPostEntity[]>("http://localhost:8080/api/v1.0/tweets/all", {headers});
  }
}