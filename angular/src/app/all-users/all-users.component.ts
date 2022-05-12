import { Component, OnInit } from '@angular/core';
import { RegisterEntity } from '../model/RegisterEntity';
import { UserDetailsService } from '../service/user-details.service';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {
  search : string ="";
  arr : string[] = []; 
  users ! : RegisterEntity[];
  constructor(
    private userDetailsService : UserDetailsService
  ) { 
    this.userDetailsService.getAllUsers().subscribe(
      (data)=>{
        this.users = data as RegisterEntity[];
        for (let i = 0; i < this.users.length; i++) {
            this.arr.push(this.users[i].userName);
        }
        console.log(this.users)
      }
    )
  }

  Search(){
    console.log(this.search);
    this.arr = [];
    for (let i = 0; i < this.users.length; i++) {
      if(this.users[i].userName.toLocaleLowerCase().startsWith(this.search.toLocaleLowerCase())){
        this.arr.push(this.users[i].userName);
      }
    }
    console.log(this.arr);
  }

  ngOnInit(): void {

    
  }



}
