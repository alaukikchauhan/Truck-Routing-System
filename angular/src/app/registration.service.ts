import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { RegistrationComponent } from './registration/registration.component';
import { FpasswordComponent } from './fpassword/fpassword.component';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {


  constructor(private _http: HttpClient) { }

  register(user: RegistrationComponent)
  {
    return this._http.post<any>('http://localhost:8083/signup', user);
  }
  esubmit(email: FpasswordComponent)
  {
    const body = 'username=' + email;
    return this._http.post<any>('http://localhost:8083/forgotpassword', body);
  }
}
