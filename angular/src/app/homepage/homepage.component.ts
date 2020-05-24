import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators, AbstractControl} from '@angular/forms';
import * as L from 'leaflet';
import 'leaflet-routeboxer';
import * as decodePolyline from 'decode-google-map-polyline';
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
  directionsService = new google.maps.DirectionsService();
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
        this.cdistance = response.routes[0].legs[0].distance.text;
        this.cduration = response.routes[0].legs[0].duration.text;
        const route = decodePolyline(response.routes[0].overview_polyline);
        console.log(route);
        const distance = 15; // Distance in km
        const boxes = L.RouteBoxer.box(route, distance);
        console.log(boxes);
        this.findPlaces(0);
      } else {
        window.alert('Directions request failed due to ' + status);
      }
    });
  }
  findPlaces(searchIndex) {
    const type = '';
    const keyword = '';
    const name = '';
    const request = {
      bounds: boxes[searchIndex],
    };
    if (!!type && (type !== '')) {
      if (type.indexOf(',') > 0) {
        request.types = type.split(',');
      }
      else {
        request.types = [type];
      }
    }
    if (!!keyword && (keyword !== '')) { request.keyword = keyword; }
    if (!!name && (name !== '')) { request.name = name; }
    // tslint:disable-next-line:only-arrow-functions
    service.radarSearch(request, function(results, status) {
      if (status === google.maps.places.PlacesServiceStatus.OK) {
        document.getElementById('side_bar').innerHTML += 'bounds[' + searchIndex + '] returns ' + results.length + ' results<br>';
        // tslint:disable-next-line:no-conditional-assignment
        for (let i = 0, result; result = results[i]; i++) {
          let marker = createMarker(result);
        }
      } else {
        document.getElementById('side_bar').innerHTML += 'bounds[' + searchIndex + '] returns 0 results<br>&nbsp;status=' + status + '<br>';
      }
      if (status !== google.maps.places.PlacesServiceStatus.OVER_QUERY_LIMIT) {
        searchIndex++;
        if (searchIndex < boxes.length) {
          findPlaces(searchIndex);
        }
      } else { // delay 1 second and try again
        setTimeout('findPlaces(' + searchIndex + ')', 1000);
      }
    });
  }
  createMarker(place) {
    var placeLoc = place.geometry.location;
    if (place.icon) {
      var image = new google.maps.MarkerImage(
        place.icon, new google.maps.Size(71, 71),
        new google.maps.Point(0, 0), new google.maps.Point(17, 34),
        new google.maps.Size(25, 25));
    } else { var image = {
      url: 'https://maps.gstatic.com/intl/en_us/mapfiles/markers2/measle.png',
      size: new google.maps.Size(7, 7),
      anchor: new google.maps.Point(3.5, 3.5)
    };
    }
    const marker = new google.maps.Marker({
      map: map,
      icon: image,
      position: place.geometry.location
    });
    var request = {
      reference: place.reference
    };
    google.maps.event.addListener(marker, 'click', function() {
      service.getDetails(request, function(place, status) {
        if (status == google.maps.places.PlacesServiceStatus.OK) {
          var contentStr = '<h5>' + place.name + '</h5><p>' + place.formatted_address;
          if (!!place.formatted_phone_number) contentStr += '<br>' + place.formatted_phone_number;
          if (!!place.website) contentStr += '<br><a target="_blank" href="' + place.website + '">' + place.website + '</a>';
          contentStr += '<br>' + place.types + '</p>';
          infowindow.setContent(contentStr);
          infowindow.open(map, marker);
        } else {
          var contentStr = "<h5>No Result, status=" + status + "</h5>";
          infowindow.setContent(contentStr);
          infowindow.open(map, marker);
        }
      });
  
    });
    gmarkers.push(marker);
    if (!place.name) place.name = "result " + gmarkers.length;
    var side_bar_html = "<a href='javascript:google.maps.event.trigger(gmarkers[" + parseInt(gmarkers.length - 1) + "],\"click\");'>" + place.name + "</a><br>";
    document.getElementById('side_bar').innerHTML += side_bar_html;
  }
  get source(): AbstractControl { // name property
    return this.directionForm.get('source');
 }
 get destination(): AbstractControl { // name property
  return this.directionForm.get('destination');
}
}
