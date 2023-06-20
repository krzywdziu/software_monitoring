import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-alerts',
  templateUrl: './user-alerts.component.html',
  styleUrls: ['./user-alerts.component.css']
})
export class UserAlertsComponent implements OnInit {

  public userAlerts: any[] = [];
  public alertsResolved: any[] = [];
  public user: any = {};

  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const id_token = localStorage.getItem('id_token');
    const id_user = this.route.snapshot.queryParamMap.get('id');

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + id_token
    });

    this.http.get<any[]>(`http://localhost:8080/alerts/user?id=${id_user}`, { headers: headers })
        .subscribe(
            (res: any) => {
              this.userAlerts = res;
              this.filterResolvedAlerts();
            },
            (err: any) => console.log('Error: ', err)
        );

    this.http.get<any>(`http://localhost:8080/users/${id_user}`, { headers: headers })
        .subscribe(
            (res) => this.user = res,
            err => console.log('Error: ', err)
        );
  }

  filterResolvedAlerts(): void {
    if (this.userAlerts) {
      this.alertsResolved = this.userAlerts.filter((alert) => alert.status === 'RESOLVED');
      this.userAlerts = this.userAlerts.filter((alert) => alert.status !== 'RESOLVED');
    }
  }

  getRowColor(severity: string): string {
    switch (severity) {
      case 'EMERGENCY':
        return 'emerg-severity';
      case 'ALERT':
        return 'alert-severity';
      case 'CRITICAL':
        return 'crit-severity';
      case 'ERROR':
        return 'err-severity';
      case 'WARNING':
        return 'warning-severity';
      case 'NOTICE':
        return 'notice-severity';
      case 'INFORMATIONAL':
        return 'info-severity';
      case 'DEBUG':
        return 'debug-severity';
      default:
        return '';
    }
  }

  formatDate(timestamp: string) {
    const date = new Date(timestamp);
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");
    const seconds = String(date.getSeconds()).padStart(2, "0")
    const day = String(date.getDate()).padStart(2, "0");
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const year = String(date.getFullYear());
    const formattedDate = `${day}-${month}-${year} ${hours}:${minutes}:${seconds}`;
    return formattedDate;
  }
}
