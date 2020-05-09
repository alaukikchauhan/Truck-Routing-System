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

  register(user: RegistrationComponent){
    return this._http.post<any>(this.url, user);
  }
  esubmit(email: FpasswordComponent)
  {
    return this._http.post<any>(this.url, email);
  }
}
