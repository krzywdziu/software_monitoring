import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-single-alert',
  templateUrl: './single-alert.component.html',
  styleUrls: ['./single-alert.component.css']
})
export class SingleAlertComponent implements OnInit{
  public alert: any = {};

  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  ngOnInit(): void {
      const id_token = localStorage.getItem('id_token')
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + id_token
      })
      const params = this.route.snapshot.params;
      const id = params['id'];

      this.http.get<any>(`http://localhost:8080/alert/${id}`, {headers: headers, params})
          .subscribe(
              res => this.alert = res,
              err => console.log('Error: ', err)
          );
  }
}
