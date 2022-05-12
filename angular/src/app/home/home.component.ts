import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisterEntity } from '../model/RegisterEntity';
import { TweetPostEntity } from '../model/TweetPostEntity';
import { TweetService } from '../service/tweet.service';
import { UserDetailsService } from '../service/user-details.service';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user ! : RegisterEntity ;
    tw !: TweetPostEntity[];
    likes ! : number;
    male : string = "male";
    female : string ="female";

  constructor(
    private router:ActivatedRoute,
    private fb:FormBuilder,
    private tweetService :TweetService,
    private route : Router,
    private userDetailsService : UserDetailsService,
    private loginService:LoginService
    ) { 
  //     const navigation = this.route.getCurrentNavigation();
  //  const state = navigation?.extras.state as {
  //   example: string,
  //  }
//  this.username = state.example;


    
  }
  fg ! : FormGroup;
  select ! : any;
  username :any;
  token:string="";

  ngOnInit(): void {
  
    this.select  = localStorage.getItem("Select");
    //  this.route.navigate(['/home']);
    this.fg = this.fb.group({
      Tweet : this.fb.control('',[])
    });

    this.tweetService.viewAllTweets().subscribe(
      data => {
        this.tw = data as TweetPostEntity[] ; 
        console.log(this.tw);
      }
    );
    this.userDetailsService.getUserDetails().subscribe(
      (data)=>{
        console.log(data);
        this.user = data as RegisterEntity;
      }
      );
  }

  cltr(nm : string) : FormControl {
    return this.fg.get(nm) as FormControl;
  }

  getFormGroup() : FormGroup{
    return this.fg as FormGroup;
  }

  onsubmit(){
    console.log(this.cltr('Tweet').value);
  }

  changeIcon(){
    this.likes = this.likes+1;
  }

  onPostATweet(){
    // this.route.navigate(['/postATweet'])
  }

  likeButton(i : number){

    this.tweetService.likeATweet(this.tw[i]).subscribe((data)=>{
    this.ngOnInit();
    });
  }

  commentButton(i : number){
    this.route.navigate(['/reply',i]);
  }
  navigator : number =4;

  logout(){
    localStorage.removeItem("username");
    localStorage.setItem("loggedIn","false");
    this.route.navigate(['signIn']);


  }
  num : any ;


  postATweet(){
    this.num=1;
  }

  myTweets(){
    this.num=2
  }
  allUsers(){
    this.num=3
  }
  temp(){
    console.log("home clicked");
    this.num=0;
  }
}
