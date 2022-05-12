import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TweetPostEntity } from '../model/TweetPostEntity';
import { TweetUpdateRequest } from '../model/TweetUpdateRequest';
import { NotificationService } from '../service/notification.service';
import { TweetService } from '../service/tweet.service';

@Component({
  selector: 'app-my-tweets',
  templateUrl: './my-tweets.component.html',
  styleUrls: ['./my-tweets.component.css']
})
export class MyTweetsComponent implements OnInit {
  tw !: TweetPostEntity[];
  fg ! :FormGroup;
  display :number = 0;
  replyId :  number =0;
  constructor(
    private tweetService : TweetService,
    private fb : FormBuilder,
    private notifyService:NotificationService
  ) { 

    this.fg = this.fb.group({
      newTweet : this.fb.control([''],[Validators.required])
    })
    
 
  }
  showToasterSuccess(message : string){
    console.log("success");
    this.notifyService.showSucess(message," ");
  }

  showToasterError(message : string){
    this.notifyService.showError(message,"")
  }
  hide : string='';
  ngOnInit(): void {
    this.tweetService.viewMyTweets().subscribe(
      data => {
        this.tw = data as TweetPostEntity[] ; 
        console.log(this.tw);
      }
    );
  }
  editTweet(i:number){
  
    var tweetSelected : TweetPostEntity = this.tw[i];
    this.hide = tweetSelected.id;
  }
  DeleteTweet(i : number){
    var tweetSelected : TweetPostEntity = this.tw[i];
    var tweetId = tweetSelected.id
      this.tweetService.deleteTweet(tweetId).subscribe({});
      this.ngOnInit();
  }

  onSubmit(i : number){
   var tweetSelected : TweetPostEntity = this.tw[i];
   var newTweet : TweetUpdateRequest ={
     newTweet:this.fg.get('newTweet')?.value,
     tweetId: tweetSelected.id
   }
   this.tweetService.updateTweet(newTweet).subscribe(
     (data)=>{
     
       this.showToasterSuccess("Tweet Updated Successfully");
       this.ngOnInit();
     }, error=>{
          this.showToasterError("Tweet Not updated");
     }
   )
  }
  reply(i:number){
    console.log(this.display);
    this.display = 1;
    console.log(this.display);
    this.replyId = i;
  }
}
