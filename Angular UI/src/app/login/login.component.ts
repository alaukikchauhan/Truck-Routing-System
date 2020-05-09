import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])});
  submitted=false;  
  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(user)
  {
    const body = 'username=' + user.username + '&password=' + user.password;

    this.http.post<any>('http://localhost:8083/signin', body)
    .subscribe((data) =>
    {
        localStorage.setItem('currentUser', data);
    });
    this.router.navigate(['homepage']);
  }
}
