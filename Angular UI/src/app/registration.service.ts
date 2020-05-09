import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { RegistrationComponent } from './registration/registration.component';
import { FpasswordComponent } from './fpassword/fpassword.component';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  url = '';

  constructor(private _http: HttpClient) { }

  register(user: RegistrationComponent)
  {
    return this._http.post<any>('localhost:8083/signup', user);
  }
  esubmit(email: FpasswordComponent)
  {
    var body="username="+email;
    return this._http.post<any>('localhost:8083/forgotpassword', body);
  }
}
