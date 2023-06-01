import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'app-servicemen-dashboard',
  templateUrl: './servicemen-dashboard.component.html',
  styleUrls: ['./servicemen-dashboard.component.css']
})
export class ServicemenDashboardComponent implements OnInit {
  public servicemen: any[] = [];

  constructor(private http: HttpClient) {}

  // todo: zmienic url gdy bedzie endpoint
  ngOnInit(): void {
      const id_token = localStorage.getItem('id_token')

      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + id_token
      })

      this.http.get<any[]>('http://localhost:8080/users', { headers: headers })
          .subscribe(
          res => this.servicemen = res,
          err => console.log('Error: ', err)
      )
  }
}
