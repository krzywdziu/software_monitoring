import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-single-alert',
  templateUrl: './single-alert.component.html',
  styleUrls: ['./single-alert.component.css']
})
export class SingleAlertComponent implements OnInit{
  public alert: any = {};

  constructor(private http: HttpClient, private route: ActivatedRoute, private authService: AuthService) {}

  ngOnInit(): void {
    const token = this.authService.token;
    if (token) {
      const headers = new HttpHeaders().set('Authorization', token);
      const params = this.route.snapshot.params;
      const id = params['id'];

      this.http.get<any>(`http://localhost:8080/alert/${id}`, {headers, params, responseType: 'json'})
          .subscribe(
              res => this.alert = res,
              err => console.log('Error: ', err)
          )
    }
  }
}
