import { Component, OnInit } from '@angular/core';
import { Form, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ForgotPasswordEntity } from '../model/ForgotPasswordEntity';
import { NotificationService } from '../service/notification.service';
import { UserDetailsService } from '../service/user-details.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  hide = true;
  hide2 = true;
  constructor(
    private userDetailsService: UserDetailsService,
    private router:Router,
    private fb: FormBuilder,
    private notifiyService: NotificationService
  ) {
    this.fg = this.fb.group({
      username: this.fb.control('', [
        Validators.required
      ]),
      email: this.fb.control('',
        [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
        ]),
      password: this.fb.control('', [Validators.required, Validators.minLength(6)]),
      confirmPassword: this.fb.control('', [Validators.required, Validators.minLength(6)])
    })
  }
  showToasterSuccess(message: string) {

    this.notifiyService.showSucess(message, "");
  }
  showToasterError(message: string) {
    this.notifiyService.showError(message, "")
  }

  fg!: FormGroup;
  onSubmit() {
    console.log(this.fg.value);
    if (this.fg.get('password')?.value === this.fg.get('confirmPassword')?.value) {
      var forgotPasswordEntity: ForgotPasswordEntity = {
        emailId: this.fg.get('email')?.value,
        password: this.fg.get('password')?.value,
        username: this.fg.get('username')?.value,
      };
      this.userDetailsService.forgotPassword(forgotPasswordEntity).subscribe
        ((data) => {
          this.showToasterSuccess("Password changed successfully",);
          this.router.navigate(['signIn'])
        },
          err => {
            console.log(err);
            if (err.status == 400) {
              this.showToasterError("Enter Correct Username or EmailId");
            } else if (err.status == 404) {
              this.showToasterError("UserName Not Found");
            }
          }
        )
    }
    else{
      this.showToasterError("Password And confirmPassword Should be Same");
    }
  } 
ngOnInit(): void {

}

cltr(name : string){
  this.fg.get(name) as FormControl
}

}
