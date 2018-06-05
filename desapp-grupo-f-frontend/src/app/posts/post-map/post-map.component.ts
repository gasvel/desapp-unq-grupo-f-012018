import { Component, OnInit, ViewChild } from '@angular/core';
import { PostService } from '../../services/post.service';
import { Router, ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operator/map';

@Component({
  selector: 'app-post-map',
  templateUrl: './post-map.component.html',
  styleUrls: ['./post-map.component.css'],
  providers: [PostService]
})
export class PostMapComponent implements OnInit {

  render = true;// parche
  posts= [];
  locations = [];
  @ViewChild('map') mapa:any;
	  map: google.maps.Map;
  province = "Buenos aires";
  geocoder;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private postServ: PostService) {

  }

  ngOnInit() {
    this.geocoder = new google.maps.Geocoder();
    this.getPosts();
  }

  setInitialPosition() {
    let self = this;
    this.geocoder.geocode( {'address' : this.province}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
          var location = results[0].geometry.location;
          self.initMap(location);
        }
    });
  }

  initMap(location) {
    this.map = new google.maps.Map(this.mapa.nativeElement, {
      center: location,
  	  zoom: 10,
  	  mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var infowindow = new google.maps.InfoWindow();

    var marker, i;

    for (i = 0; i < this.locations.length; i++) {
      marker = new google.maps.Marker({
        position: new google.maps.LatLng(this.locations[i].lat, this.locations[i].long),
        map: this.map
      });

      var self = this;


      google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
          let html = '<div >' + "<h4>" + self.locations[i].title + '</h4>';
          html += '<img  src="' + self.locations[i].image + '" height="100" width="150"/><br/>';
          html += '<p>' + self.locations[i].availability + '</p>';
          html += '<button class="btn btn-primary"  id="clickableItem">Ver post</button>';
          html += '</div>';
          infowindow.setContent(html);
          infowindow.open(this.map, marker);

          
          infowindow.addListener("domready",function(e){
            document.getElementById("clickableItem").addEventListener("click", function(e) {
               infowindow.close();
               
                self.viewPost(self.locations[i].id);
            });
          });

          //document.getElementById("clickableItem").addEventListener("click", function(e) {
           // infowindow.close();
            //viewPost(self.locations[i].id);
          //});
        }
      })(marker, i));
    }
  }

  getPosts(){
    this.postServ.getAll().subscribe(
      res => {
        this.posts = res;
        this.setLocations();
        this.setInitialPosition();
      },
      error => console.log(error)
    )
  }

  setLocations() {
    this.posts.forEach( post => {
        this.locations = this.locations.concat( {
          id : post.id,
          title : post.title,
          lat : Number(post.location.split(",")[0]),
          long : Number(post.location.split(",")[1]),
          image : post.photo,
          availability : post.availability
        })
    });
  }

  viewPost(id) {
    console.log("VER POST");
    this.render = false;
    
    this.router.navigate(['post', id ]).then(() => location.reload());

  }

}
