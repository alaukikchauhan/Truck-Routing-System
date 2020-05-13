import { Component, OnInit, ElementRef, ViewChild, NgZone } from '@angular/core';
import { MapsAPILoader } from '@agm/core';


@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  submitted = false;
  public src: any;
  public dest: any;
  lat: any = 20.5937;
  lng: any = 78.9629;
  zoom: any = 2;
  public origin: any;
  public destination: any;
  @ViewChild('search')
  public searchElementRef: ElementRef;

  getDirection() {
    this.origin = {};
    this.destination = { };
  }
  constructor(private mapsAPILoader: MapsAPILoader, private ngZone: NgZone) {}

  ngOnInit(): void {}
  findAdress(){
    this.mapsAPILoader.load().then(() => {
         const autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement);
         autocomplete.addListener('place_changed', () => {
           this.ngZone.run(() => {
             // some details
             const place: google.maps.places.PlaceResult = autocomplete.getPlace();
             if (place.geometry === undefined || place.geometry === null)
             {
               return ;
             }
           });
         });
       });
   }
  onRoute()
  {
    this.getDirection();
  }

}
