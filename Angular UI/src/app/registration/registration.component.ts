import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { RegistrationService } from '../registration.service';
import { from } from 'rxjs';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  form = new FormGroup({
    fname: new FormControl('', [Validators.required]),
    lname: new FormControl('', [Validators.required]),
    uname: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    tel: new FormControl('', [ Validators.required, Validators.maxLength(10)])
  });
  get f(){
      return this.form.controls;
  }
  constructor(private rservice: RegistrationService) { }

  ngOnInit(): void {
  }

  onSubmit()
  {
    this.rservice.register(this.form.value)
    .subscribe(
      data => console.log('Success!', data),
      error => console.log('Error!', error)
    );
    console.log(this.form.value);
  }
}
