import { Component, ViewChild, ElementRef, OnInit, AfterViewInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import * as L from 'leaflet';
declare var google;
@Component({
  selector: 'app-truckinfo',
  templateUrl: './truckinfo.component.html',
  styleUrls: ['./truckinfo.component.css']
})
export class TruckinfoComponent implements OnInit, AfterViewInit {

  submitted = false;
  public fuel_required: number;
  public fuel_cost: number;
  public fstops: number;
  public tdist: number;
  public mil: number;
  public fl: number;
  public tcap: number;
  public rfuel: number;
  public fcount: number;

  @ViewChild('mapElement') mapNativeElement: ElementRef;
  // tslint:disable-next-line:new-parens
  directionsService = new google.maps.DirectionsService;
  // tslint:disable-next-line:new-parens
  directionsDisplay = new google.maps.DirectionsRenderer;
  form = new FormGroup({
    tdistance: new FormControl('', [Validators.required]),
    mileage: new FormControl('', [Validators.required]),
    fleft: new FormControl('', [Validators.required]),
    tcapacity: new FormControl('', [Validators.required])
  });
  get f()
  {
    return this.form.controls;
  }
  constructor() { }

  ngOnInit(): void {
  }
  // placing a marker on the map
  createMarker(Map, coords, title) {
    const marker = new google.maps.Marker({
     position: coords,
     map: Map,
     // tslint:disable-next-line:object-literal-shorthand
     title: title,
     draggable: false
    });
    return marker;
   }
  findPlaces(boxes) {
    let data = '';
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < boxes.length; i++) {
     // form the query string that will be sent via ajax
     if (data !== '') {
      data += '&';
     }
     // tslint:disable-next-line:quotemark
     // tslint:disable-next-line:max-line-length
     data += 'boxes[]=' + boxes[i].getNorthEast().lat() + ':' + boxes[i].getNorthEast().lng() + '-' + boxes[i].getSouthWest().lat() + ":" + boxes[i].getSouthWest().lng();
    }
    if (data !== '') {
     // make an ajax request to query the database
     // suppose that the response would be a JSON similar to this
     const response = '[{"title":"Dodona Site","latitude":"39.546135","longitude":"20.785105"},{"title":"Perama Cave","latitude":"39.695000","longitude":"20.846457"},{"title":"Trikorfo Castle","latitude":"39.298645","longitude":"20.367601"}]';
     const places = JSON.parse(response);
     // tslint:disable-next-line:forin
     for (const i in places) {
      const coords = new google.maps.LatLng(places[i].latitude, places[i].longitude);
      this.createMarker(Map, coords, places[i].title);
     }
    }
   }
  ngAfterViewInit(): void {
    const map = new google.maps.Map(this.mapNativeElement.nativeElement, {
      zoom: 5,
      center: {lat: 20.5937, lng: 78.9629}
    });
    this.directionsDisplay.setMap(map);
  }
  calculateAndDisplayRoute() {
    const that = this;
    this.directionsService.route({
      origin: 'delhi',
      destination: 'bareilly',
      travelMode: 'DRIVING'
    }, (response, status) => {
      if (status === 'OK') {
        that.directionsDisplay.setDirections(response);
        console.log(response);
        console.log(response.routes[0].legs[0].distance);
        const route = new L.Polyline(L.PolylineUtil.decode(response.routes[0].overview_path));
        const distance = 10; // Distance in km
        const boxes = L.RouteBoxer.box(route, distance);
      } else {
        window.alert('Directions request failed due to ' + status);
      }
    });
  }

  onSubmit()
  {
    console.log(this.form.value);
    this.rfuel = this.tdist / this.mil;
    this.fuel_required = this.rfuel - this.fl;
    this.fuel_required.toFixed(2);
    this.fuel_cost = this.fuel_required * 65;
    this.fuel_cost.toFixed(1);
    this.fcount = this.tcap * this.mil;
    this.fstops = this.tdist / this.fcount;
    if (this.fstops < 1)
    {
        this.fstops = 1;
    }
    else{
      this.fstops += 1;
    }

    if (this.fuel_required === 0 || this.fuel_required < 0)
    {
       this.fstops = 0;
       this.fuel_required = 0;
       this.fuel_cost = 0;
    }
  }

}
