import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisterEntity } from '../model/RegisterEntity';
import { TweetPostEntity } from '../model/TweetPostEntity';
import { TweetService } from '../service/tweet.service';
import { UserDetailsService } from '../service/user-details.service';

@Component({
  selector: 'app-temp',
  templateUrl: './temp.component.html',
  styleUrls: ['./temp.component.css']
})
export class TempComponent implements OnInit {

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
  private userDetailsService : UserDetailsService
  ) { 
//     const navigation = this.route.getCurrentNavigation();
//  const state = navigation?.extras.state as {
//   example: string,
//  }
//  this.username = state.example;

   
  
}
display :number = 0;
fg ! : FormGroup;
select ! : any;
username :any;
replyId :  number =0;

ngOnInit(): void {

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
}


num:any;
postATweet(){
  this.num=1;
}

myTweets(){
  this.num=2
}
allUsers(){
  this.num=3
}
home(){
  console.log("home clicked");
  this.num=0;
}
reply(i:number){
  console.log(this.display);
  this.display = 1;
  console.log(this.display);
  this.replyId = i;
}

}
