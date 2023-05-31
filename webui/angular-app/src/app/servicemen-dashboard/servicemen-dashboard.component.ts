import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import {AuthService} from "../auth.service";


@Component({
  selector: 'app-servicemen-dashboard',
  templateUrl: './servicemen-dashboard.component.html',
  styleUrls: ['./servicemen-dashboard.component.css']
})
export class ServicemenDashboardComponent implements OnInit {
  public servicemen: any[] = [];

  constructor(private http: HttpClient, private route: ActivatedRoute, private authService: AuthService) {}

  // todo: zmienic url gdy bedzie endpoint
  ngOnInit(): void {
    const token = this.authService.token;
    if (token) {
      const headers = new HttpHeaders().set('Authorization', token);
      const params = this.route.snapshot.params;

      this.http.get<any[]>('http://localhost:8080/user/all', { headers, params, responseType: 'json' })
          .subscribe(
          res => this.servicemen = res,
          err => console.log('Error: ', err)
      )
    }
  }
}
