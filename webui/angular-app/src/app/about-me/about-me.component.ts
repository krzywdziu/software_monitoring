import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Component({
  selector: 'app-about-me',
  templateUrl: './about-me.component.html',
  styleUrls: ['./about-me.component.css']
})
export class AboutMeComponent implements OnInit {
  public id_user = localStorage.getItem('id_user');

  constructor(private http: HttpClient) {}

  public me: any;

  ngOnInit(): void {
    const id_token = localStorage.getItem('id_token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + id_token
    });

    this.http.get<any>(`http://localhost:8080/users/${this.id_user}`, { headers: headers })
        .subscribe(
            res => {
              this.me = res;
              this.loadUserAlerts(headers);
            },
            err => console.log('Error: ', err)
        );
  }

  loadUserAlerts(headers: HttpHeaders): void {
      this.http.get<any[]>(`http://localhost:8080/alerts/user?id=${this.id_user}`, { headers: headers })
          .subscribe(
              (res: any[]) => {
                this.me.alertsCount = res.length;
              },
              (err: any) => console.log('Error: ', err)
          );
  }
    getSpecializations(serviceman: any): string {
        return serviceman.specializations.map((specialization: any) => specialization.name).join(', ');
    }
}
