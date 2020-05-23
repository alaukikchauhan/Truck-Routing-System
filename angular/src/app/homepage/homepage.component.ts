import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators, AbstractControl} from '@angular/forms';
import { RoutesRecognized } from '@angular/router';
declare var google;

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit, AfterViewInit {

  submitted = false;
  public cdistance: number;
  public cduration: number;
  public src;
  public dest;
  @ViewChild('mapElement') mapNativeElement: ElementRef;
  // tslint:disable-next-line:new-parens
  directionsService = new google.maps.DirectionsService;
  // tslint:disable-next-line:new-parens
  directionsDisplay = new google.maps.DirectionsRenderer;
  directionForm: FormGroup;
  constructor(private fb: FormBuilder) {
    this.createDirectionForm();
  }

  ngOnInit() {
  }

  createDirectionForm() {
    this.directionForm = this.fb.group({
      source: ['', Validators.required],
      destination: ['', Validators.required]
    });
  }

  ngAfterViewInit(): void {
    const map = new google.maps.Map(this.mapNativeElement.nativeElement, {
      zoom: 5,
      center: {lat: 20.5937, lng: 78.9629}
    });
    this.directionsDisplay.setMap(map);
  }

  calculateAndDisplayRoute(formValues) {
    const that = this;
    this.directionsService.route({
      origin: formValues.source,
      destination: formValues.destination,
      travelMode: 'DRIVING'
    }, (response, status) => {
      if (status === 'OK') {
        that.directionsDisplay.setDirections(response);
        console.log(response);
        console.log(response.routes[0].legs[0].distance);
        this.cdistance = response.routes[0].legs[0].distance.text;
        this.cduration = response.routes[0].legs[0].duration.text;
      } else {
        window.alert('Directions request failed due to ' + status);
      }
    });
  }
  get source(): AbstractControl { // name property
    return this.directionForm.get('source');
 }
 get destination(): AbstractControl { // name property
  return this.directionForm.get('destination');
}
}
