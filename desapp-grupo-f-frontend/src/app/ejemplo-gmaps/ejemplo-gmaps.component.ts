import { Component, OnInit ,ViewChild} from '@angular/core';
import { } from '@types/googlemaps';

@Component({
  selector: 'app-ejemplo-gmaps',
  templateUrl: './ejemplo-gmaps.component.html',
  styleUrls: ['./ejemplo-gmaps.component.css']
})
export class EjemploGmapsComponent implements OnInit {
	@ViewChild('map') mapa:any;
	  map: google.maps.Map;

	  distancia:String;
	  duracion:String;

  constructor() {
  }

  ngOnInit() {
  	this.initMap();
  }

  initMap() {
        var directionsDisplay = new google.maps.DirectionsRenderer;
        var directionsService = new google.maps.DirectionsService;
        this.map = new google.maps.Map(this.mapa.nativeElement, {
          center: new google.maps.LatLng(-34.7583014, -58.2088815),
      	  zoom: 15,
      	  mapTypeId: google.maps.MapTypeId.ROADMAP
        });
        directionsDisplay.setMap(this.map);

        this.calculateAndDisplayRoute(directionsService, directionsDisplay);

      }

   calculateAndDisplayRoute(directionsService, directionsDisplay) {
        directionsService.route({
          origin: {lat: -34.760661, lng: -58.210101},
          destination: {lat: -34.751555, lng: -58.225844},
          travelMode: google.maps.TravelMode["DRIVING"]
        }, function(response, status) {
          if (status == 'OK') {
          	console.log(response);
         	document.getElementById('distancia').innerHTML += '<p class="text-danger text-center">'+response.routes[0].legs[0].distance.text + '</p>';

         	document.getElementById('duracion').innerHTML += '<p class="text-danger text-center">'+response.routes[0].legs[0].duration.text + '</p>';
            directionsDisplay.setDirections(response);


          } else {
            window.alert('Directions request failed due to ' + status);
          }
        });
      }

}
