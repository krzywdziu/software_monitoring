import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {formatDate} from "@angular/common";
import {UtilsService} from "../utils.service";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
    public alerts: any[] = [];
    public myAlerts: any[] = [];

    public alertsResolved: any[] = [];


    constructor(private http: HttpClient, public utils: UtilsService) {}

    ngOnInit(): void {
        const id_token = localStorage.getItem('id_token')
        const id_user = localStorage.getItem('id_user');

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + id_token
        })

        this.http.get<any[]>('http://localhost:8080/alerts', { headers: headers })
            .subscribe(
                (res: any) => {
                    this.alerts = res;
                    this.filterResolvedAlerts();
                    this.filterResolvedAlertsByDate()
                },
                (err: any) => console.log('Error: ', err)
            );

        this.http.get<any[]>(`http://localhost:8080/alerts/user?id=${id_user}`, { headers: headers })
            .subscribe(
                (res: any) => {
                    this.myAlerts = res;
                    this.filterResolvedAlerts();
                },
                (err: any) => console.log('Error: ', err)
            );
    }

    filterResolvedAlerts(): void {
        if (this.alerts) {
            this.alertsResolved = this.alerts.filter((alert) => alert.status === 'RESOLVED');
            this.alerts = this.alerts.filter((alert) => alert.status !== 'RESOLVED');
        }
        if (this.myAlerts) {
            this.myAlerts = this.myAlerts.filter((alert) => alert.status !== 'RESOLVED');
        }
    }

    filterResolvedAlertsByDate(): void {
        const currentDate = new Date();
        currentDate.setDate(currentDate.getDate() - 7);

        this.alertsResolved = this.alertsResolved.filter((alert) => {
            const alertDate = new Date(alert.timestamp);
            return alertDate >= currentDate;
        });
    }

}