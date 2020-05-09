import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
providedIn: 'root'
    })
export class MapsService {
constructor(private httpClient: HttpClient) {}

private grantType: string = 'client_credentials';
private clientId: string = 'oFfQpJlLN19Xe72tuIDb6tl8hvjbHjhOdkUujvCVrMwtvNo9u9bphOLEfY_j3GQow4x5Jsi3Kxj2HhpWyZnJSg==';
private clientSecret: string = 'ebEc8GH231cpKS2IV_Ye3E-TqT-w5CUa5A_pUW6zB7a4sE9_jlr-wqPfxIyef6TDdcCB1wNsx8xJxugsAM9PqU_Ujw0exoK4';
// tslint:disable-next-line:max-line-length
private securityURL: string = 'https://outpost.mapmyindia.com/api/security/v3.0.5/oauth/token?grant_type=' + this.grantType + '&client_id=' + this.clientId + '&client_secret=' + this.clientSecret;
private autoSuggestURL: string = 'https://atlas.mapmyindia.com/api/places/search/json?query=agra&location=28.5454,77.455454&bridge&explain&username=balmukand';
private nearbyURL: string = 'https://atlas.mapmyindia.com/api/places/nearby/json?explain&richData&username=balmukand&refLocation=28.467470,77.077518&keywords=FINATM';

getToken() {
  const promise = new Promise((resolve, reject) => {
      const apiURL = this.securityURL;
      this.httpClient.post(apiURL, null)
          .toPromise()
          .then(
          res => { // Success
              resolve(res);
              }
          );
      });
  return promise;
}
autoSuggest(token: string) {
  const _url = this.autoSuggestURL + '&access_token=' + token;
  const promise = new Promise((resolve, reject) => {
  this.httpClient.get(_url)
      .toPromise()
      .then(
          res => { // Success
              // console.log(res);
              resolve(res);
              }
          );
      });
  return promise;
}
nearby(token: string) {
  const _url = this.nearbyURL + '&access_token=' + token;
  const promise = new Promise((resolve, reject) => {
  this.httpClient.get(_url)
      .toPromise()
      .then(
          res => { // Success
              resolve(res);
              }
          );
      });
  return promise;
}

}
