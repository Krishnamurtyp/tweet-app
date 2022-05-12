import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginForm } from '../model/LoginForm';
import { LoginResponse } from '../model/LoginResponse';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(
    private httpClient: HttpClient
  ) { }

  baseUrl : string  = "http://localhost/api/v1.0/tweets";
  
  login(loginData : LoginForm){
    return this.httpClient.post<string>("http://localhost/api/v1.0/tweets", loginData, {  responseType: 'text' as 'json' });
  }
}
