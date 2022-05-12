import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { userNewTweetRequest } from '../model/userNewTweetRequest';
import { TweetService } from '../service/tweet.service';

@Component({
  selector: 'app-posta-tweet',
  templateUrl: './posta-tweet.component.html',
  styleUrls: ['./posta-tweet.component.css']
})
export class PostaTweetComponent implements OnInit {
 
  fg! : FormGroup;
   
  constructor(
    private route : Router,
    private fb : FormBuilder,
    private tweetService : TweetService
  ) { }

  ngOnInit(): void {

    this.fg = this.fb.group({
      Tweet : this.fb.control('',[])
    })
  }
  cltr(nm : string) : FormControl {
    return this.fg.get(nm) as FormControl;
  }
  
  onSubmit(){
     var newTweet  : userNewTweetRequest = {
      tweet : this.cltr('Tweet').value,
      like : 0,
      reply : "",
      userName : ""
    }
    console.log(this.cltr('Tweet').value);
    this.tweetService.postATweet(newTweet).subscribe((data)=>{
      
    })
    this.route.navigate(['/home/temp']);
  }
  onCancal(){
    this.route.navigate(['/home/temp'])
  }

}
