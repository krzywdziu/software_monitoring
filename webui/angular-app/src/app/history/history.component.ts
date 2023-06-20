import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UtilsService} from "../utils.service";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  public alerts: any[] = [];
  public alertsResolved: any[] = [];

  constructor(private http: HttpClient, public utils: UtilsService) {}

  ngOnInit(): void {
    const id_token = localStorage.getItem('id_token')

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + id_token
    })

    this.http.get<any[]>('http://localhost:8080/alerts', { headers: headers })
        .subscribe(
            (res: any) => {
              this.alerts = res;
              this.filterResolvedAlerts();
            },
            (err: any) => console.log('Error: ', err)
        );
  }

  filterResolvedAlerts(): void {
    if (this.alerts) {
      this.alertsResolved = this.alerts.filter((alert) => alert.status === 'RESOLVED');
    }
  }
}
