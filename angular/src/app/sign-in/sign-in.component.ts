import { Component, OnInit } from '@angular/core';
import {  FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginResponse } from '../model/LoginResponse';
import { AuthServiceService } from '../service/auth-service.service';
import {Router}  from '@angular/router'
import { NotificationService } from '../service/notification.service';
import { LoginService } from '../service/login.service';
import { UserDetailsService } from '../service/user-details.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  fg ! : FormGroup

  constructor( 
    private fb:FormBuilder,
    private loginService: LoginService,
    private authService : AuthServiceService,
    private router : Router,
    private notifyService : NotificationService,
    private userDetails : UserDetailsService
  ) { }
   
  ngOnInit(): void {
    this.fg = this.fb.group({
      username : this.fb.control('',[
        Validators.required,Validators.minLength(2)
      ]),
      password : this.fb.control('',[
        Validators.required
      ])
    })
  }

  showToasterSuccess(){
    console.log("success");
    this.notifyService.showSucess("Login Successfully !!"," ");
  }

  showToasterError(message : string){
    this.notifyService.showError(message,"")
  }

  cltr(nm : string) : FormControl {
    return this.fg.get(nm) as FormControl;
  }

  getFormGroup() : FormGroup{
    return this.fg as FormGroup;
  }
  
  onsubmit(){
    console.log("username is "+ this.cltr('username').value+ "password is :" + this.cltr('password').value);
    
    const body ={
      "userName" : this.cltr('username').value,
      "password" : this.cltr('password').value
    }
    this.authService.login(body).subscribe(
      res=>{
        console.log(res);
        localStorage.setItem("username",this.cltr('username').value);
        this.router.navigate(['/home/temp']);
        this.showToasterSuccess();
        this.loginService.login(res);
      },
      err=>{
        console.log(err);
        this.loginService.logout();
        if(err.status == 401){
        this.showToasterError("Password is Wrong");
        } else if(err.status==404){
          this.showToasterError("UserName Not Found");
        }

      }
    )

  
  }
  register(){
    this.router.navigate(['Register']);
  }
  forgot(){
    this.router.navigate(['/forgotPassword']);
  }
}
