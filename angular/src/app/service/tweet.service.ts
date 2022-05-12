import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, ObservedValueOf } from 'rxjs';
import { TweetPostEntity } from '../model/TweetPostEntity';
import { TweetPostReplyEntity } from '../model/TweetPostReplyEntity';
import { TweetUpdateRequest } from '../model/TweetUpdateRequest';
import { userNewTweetRequest } from '../model/userNewTweetRequest';
import { userReplyTweetRequest } from '../model/userReplyTweetRequest';

@Injectable({
  providedIn: 'root'
})
export class TweetService {

  constructor(private http:HttpClient) { 
   
  }
  baseUrl: string = "http://localhost/api/v1.0/tweets" 
  token:string=localStorage.getItem("token") ||'{}';
  viewAllTweets() : Observable<TweetPostEntity[]> {
    let tokenStr = 'Bearer ' + this.token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
  return this.http.get<TweetPostEntity[]>(`${this.baseUrl}all`,{headers}) ;
  }

  viewMyTweets() : Observable<TweetPostEntity[]> {
    let tokenStr = 'Bearer ' + this.token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<TweetPostEntity[]>(this.baseUrl+localStorage.getItem('username')+'/all',{headers}) ;
    }

    postATweet(userNewTweet : userNewTweetRequest){
      console.log(userNewTweet)
      let tokenStr = 'Bearer ' + this.token;
      const headers = new HttpHeaders().set('Authorization', tokenStr);
      return this.http.post(this.baseUrl+localStorage.getItem('username')+'/add', userNewTweet,{headers});
    }

    likeATweet(tweetPostEntity : TweetPostEntity){
      console.log(tweetPostEntity)
      let tokenStr = 'Bearer ' + this.token;
      const headers = new HttpHeaders().set('Authorization', tokenStr);
      return this.http.put(this.baseUrl + tweetPostEntity.userName+'/like/'+ tweetPostEntity.id, tweetPostEntity,{headers});
    }

    replyATweet(userReplyRequest : userReplyTweetRequest){
      console.log(userReplyRequest);
      let tokenStr = 'Bearer ' + this.token;
      const headers = new HttpHeaders().set('Authorization', tokenStr);
      return this.http.post(this.baseUrl +'reply/'+ userReplyRequest.tweetId, userReplyRequest,{headers});
    }

    getAllReplyTweets(tweetId : string):Observable<TweetPostReplyEntity[]>{
      let tokenStr = 'Bearer ' + this.token;
      const headers = new HttpHeaders().set('Authorization', tokenStr);
      return this.http.get<TweetPostReplyEntity[]>(this.baseUrl+'reply/'+tweetId,{headers});
    }

    updateTweet(tweetUpdateRequest : TweetUpdateRequest){
      let tokenStr = 'Bearer ' + this.token;
      const headers = new HttpHeaders().set('Authorization', tokenStr);
      return this.http.put(this.baseUrl+'updateTweet',tweetUpdateRequest,{headers});
    }

    deleteTweet(Id : string){
      let tokenStr = 'Bearer ' + this.token;
      const headers = new HttpHeaders().set('Authorization', tokenStr);
      return this.http.delete(this.baseUrl+'delete/'+Id,{headers});
    }
}
