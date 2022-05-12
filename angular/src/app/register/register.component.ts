import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterEntity } from '../model/RegisterEntity';
import { NotificationService } from '../service/notification.service';
import { UserDetailsService } from '../service/user-details.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  fg ! : FormGroup;

  hide = true;

  constructor(
    private fb:FormBuilder,
    private userDetailsService : UserDetailsService,
    private route : Router,
    private notificationService : NotificationService
  ) { }

  ngOnInit(): void {

    this.fg = this.fb.group({
      firstname : this.fb.control('',[
        Validators.required,Validators.minLength(2)
      ]),
      username : this.fb.control('',[
        Validators.required
      ]),
      email : this.fb.control('',
      [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
    ]),
      password : this.fb.control('',[Validators.required, Validators.minLength(6)]),
      gender : this.fb.control('',[
        Validators.required
      ]),
      dob : this.fb.control('',[
        Validators.required
      ]),
    })
  }

  onSubmit(){
    console.log( this.fg.value);
   var  user : RegisterEntity ={
      firstName : this.cltr('firstname').value,
      gender : this.cltr('gender').value,
      userName : this.cltr('username').value,
      email : this.cltr('email').value,
     password : this.cltr('password').value,
     dob : this.cltr('dob').value
     }
     this.userDetailsService.register(user).subscribe(
     );
     this.notificationService.showSucess("Successfully Registered","Sign Up");
     this.route.navigate(['/signIn']);
  
  }

  cltr(name : string) : FormControl{
    return this.fg.get(name) as FormControl;
  }

}
