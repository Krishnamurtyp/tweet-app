import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllUsersComponent } from './all-users/all-users.component';
import { AuthGuard } from './auth/auth.guard';
import { LoggedInGuard } from './auth/logged-in.guard';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { HomeComponent } from './home/home.component';

import { MyTweetsComponent } from './my-tweets/my-tweets.component';
import { PostaTweetComponent } from './posta-tweet/posta-tweet.component';
import { RegisterComponent } from './register/register.component';
import { ReplyComponent } from './reply/reply.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { TempComponent } from './temp/temp.component';


const routes: Routes = [
 

  {path:'signIn',component : SignInComponent ,canActivate : [LoggedInGuard]} ,
  {path :'home' , component :HomeComponent , canActivate : [AuthGuard] ,
children : [
  {path:  'temp',component:TempComponent,canActivate:[AuthGuard] ,
children :[
  {path :'reply',component : ReplyComponent , canActivate : [AuthGuard]}
]},
  {path:  'allUsers',component:AllUsersComponent,canActivate:[AuthGuard]},
  {path :'myTweets',component : MyTweetsComponent , canActivate : [AuthGuard]},
  {path :'postATweet',component : PostaTweetComponent , canActivate : [AuthGuard]},
]},
  {path : 'Register', component:RegisterComponent, canActivate:[LoggedInGuard] },
  {path : 'forgotPassword',component:ForgotPasswordComponent,canActivate:[LoggedInGuard]},
  {path:"",pathMatch:"full",redirectTo:"signIn"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
