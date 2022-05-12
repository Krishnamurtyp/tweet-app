import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  logout(){
    localStorage.removeItem("username");
    localStorage.setItem("loggedIn","false");
  }
  num : any;

  onPostATweet(){
    this.num=2;
    
  }  
  postATweet(){
    this.num=2;
  }

  select(i : string){
    console.log(i);
    localStorage.setItem("Select",i);
  }

}
