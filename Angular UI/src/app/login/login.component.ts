import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])});
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  onSubmit(user)
  {
    var body="username="+user.user+"&password="+user.password;

    this.http.post<any>("http://localhost:8083/profitabilityenhancement/signin", body)
    .subscribe((data)=>
    {
        localStorage.setItem("currentUser",data);
    });
  }
}
