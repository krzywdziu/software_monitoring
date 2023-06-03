import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-servicemen-dashboard',
  templateUrl: './servicemen-dashboard.component.html',
  styleUrls: ['./servicemen-dashboard.component.css']
})
export class ServicemenDashboardComponent implements OnInit {
  public servicemen: any[] = [];
  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    const id_token = localStorage.getItem('id_token');

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + id_token
    });

    this.http.get<any[]>('http://localhost:8080/users', { headers: headers })
        .subscribe(
            (res: any[]) => {
              this.servicemen = res;
              this.loadUserAlerts(headers);
            },
            (err: any) => console.log('Error: ', err)
        );
  }

  loadUserAlerts(headers: HttpHeaders): void {
    this.servicemen.forEach((serviceman: any) => {
      this.http.get<any[]>(`http://localhost:8080/alerts/user?id=${serviceman.id}`, { headers: headers })
          .subscribe(
              (res: any[]) => {
                serviceman.alertsCount = res.length;
              },
              (err: any) => console.log('Error: ', err)
          );
    });
  }


  getSpecializations(serviceman: any): string {
    return serviceman.specializations.map((specialization: any) => specialization.name).join(', ');
  }

}
