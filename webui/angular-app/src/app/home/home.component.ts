import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import { AuthService } from '../auth.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public alerts: any[] = [];

  constructor(private http: HttpClient, private route: ActivatedRoute, private authService: AuthService) {}

  ngOnInit(): void {
    const token = this.authService.token;
    if (token) {
      const headers = new HttpHeaders().set('Authorization', token);
      const params = this.route.snapshot.params;
      this.http.get<any[]>('http://localhost:8080/alert/all', { headers, params, responseType: 'json' })
          .subscribe(
              (res: any[]) => {this.alerts = res, console.log('Response:', res)},
              (err: any) => console.log('Error: ', err)
          );
    }
  }
}