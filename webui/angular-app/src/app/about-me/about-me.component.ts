import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Component({
  selector: 'app-about-me',
  templateUrl: './about-me.component.html',
  styleUrls: ['./about-me.component.css']
})
export class AboutMeComponent implements OnInit {
  constructor(private http: HttpClient) {}

  public me: any;

  ngOnInit(): void {
    const id_token = localStorage.getItem('id_token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + id_token
    });

    this.http.get<any>('http://localhost:8080/me', { headers: headers })
        .subscribe(
            res => {
              this.me = res;
            },
            err => console.log('Error: ', err)
        );
  }
}
