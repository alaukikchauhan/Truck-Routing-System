import { Component, OnInit } from '@angular/core';
import { MapsService } from '../maps.service';
declare var MapmyIndia: any; // Declaring Mapmyindia
declare var L: any;
@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  submitted = false;
  public map: any;
  token: any;
  constructor(private mapsservice: MapsService) { }

  ngOnInit(): void {
    this.map = new MapmyIndia.Map('map',
    {
        center: [28.04, 78.2],
        zoom: 12
    });

    this.mapsservice.getToken().then((data) => {
        this.token = data['access_token'];
    });
  }
  auto() {
    this.mapsservice.autoSuggest(this.token).then((data) => {
        console.log(data);
    });
  }
  nearby() {
    this.mapsservice.nearby(this.token).then((data) => {
        console.log(data);
    });
  }

}
