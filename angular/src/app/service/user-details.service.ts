import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ForgotPasswordEntity } from '../model/ForgotPasswordEntity';
import { RegisterEntity } from '../model/RegisterEntity';

@Injectable({
  providedIn: 'root'
})
export class UserDetailsService {


  baseUrl: string = "http://20.31.156.177/api/v1.0/tweets/" 
  token:string=localStorage.getItem("token") ||'{}';
  constructor(
    private http: HttpClient
  ) { }
  username! : string; 
  setUserDetails(username : string){

  }

  getUserDetails():Observable<RegisterEntity>{
    let tokenStr = 'Bearer ' + this.token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
     return this.http.get<RegisterEntity>(this.baseUrl+ localStorage.getItem("username"),{headers});
  }

  setUsername(username : string){
    localStorage.setItem("username",username);
  }

  register(registerEntity : RegisterEntity){
    let tokenStr = 'Bearer ' + this.token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(this.baseUrl+'register',registerEntity,{headers});
  }


  getAllUsers():Observable<RegisterEntity[]>{
    let tokenStr = 'Bearer ' + this.token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<RegisterEntity[]>(this.baseUrl+'users/all',{headers});
  }

  forgotPassword(forgotPasswordEntity : ForgotPasswordEntity){
    let tokenStr = 'Bearer ' + this.token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put(this.baseUrl+'forgotPassword',forgotPasswordEntity,{headers});
  }

  

}
