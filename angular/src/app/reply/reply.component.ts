import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { TweetPostEntity } from '../model/TweetPostEntity';
import { TweetPostReplyEntity } from '../model/TweetPostReplyEntity';
import { userReplyTweetRequest } from '../model/userReplyTweetRequest';
import { TweetService } from '../service/tweet.service';

@Component({
  selector: 'app-reply',
  templateUrl: './reply.component.html',
  styleUrls: ['./reply.component.css']
})
export class ReplyComponent implements OnInit {
  
   tw! : TweetPostEntity[];
   Tweet! : TweetPostEntity ;
   replies ! : TweetPostReplyEntity[];
   tweetId ! : number ;
   fg ! : FormGroup;
   @Input() replyId : number=0;
   username :string = ""+localStorage.getItem('username');
  constructor(
    private route :ActivatedRoute,
    private tweetService : TweetService,
    private fb : FormBuilder,
  ) {
   console.log(this.replyId);

   }

     ngOnInit():void{
    // this.route.params.subscribe((data)=>{
    //   this.tweetId = data['id'];
    // });
    this.tweetService.viewAllTweets().subscribe(
      data => {
        this.tw = data as TweetPostEntity[] ; 
        console.log(this.tw[this.replyId]);
        this.Tweet = this.tw[this.replyId];
        this.tweetService.getAllReplyTweets(this.Tweet.id).subscribe(
          data => {
            this.replies = data as TweetPostReplyEntity[] ; 
          }
        );
      }

    );
   

    this.fg = this.fb.group({
      comment : this.fb.control('')
    })
    
  }

  commentSubmit(){
    console.log(this.fg.get('comment')?.value);
    var userNewReplyTweet : userReplyTweetRequest = {
      tweetId : this.Tweet.id,
      reply   : this.fg.get('comment')?.value,
      replyfrom : this.username
    }
    this.tweetService.replyATweet(userNewReplyTweet).subscribe((data)=>{

    });
    this.ngOnInit();
  }
}
