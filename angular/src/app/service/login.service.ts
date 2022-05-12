import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {


  login(data:string){
    localStorage.setItem("loggedIn","true");
    localStorage.setItem("token",data);
  }

  logout(){
    localStorage.setItem("loggedIn","false");
  }

  isLoggedIn(){
    return localStorage.getItem("loggedIn");
  }
  constructor() { }
}
