import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { RegistrationService } from '../registration.service';
@Component({
  selector: 'app-fpassword',
  templateUrl: './fpassword.component.html',
  styleUrls: ['./fpassword.component.css']
})
export class FpasswordComponent implements OnInit {

  form = new FormGroup({
    email: new FormControl('')
  });
  get f(){
    return this.form.controls;
  }
  constructor(private emailservice: RegistrationService) { }

  ngOnInit(): void {
  }
  onSubmit()
  {
    this.emailservice.esubmit(this.form.value)
    .subscribe(
      data => console.log('Success!', data),
      error => console.log('Error!', error)
    );
    console.log(this.form.value);
  }
}
