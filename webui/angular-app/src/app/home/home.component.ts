import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {formatDate} from "@angular/common";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
    public alerts: any[] = [];
    public myAlerts: any[] = [];

    public alertsResolved: any[] = [];


    constructor(private http: HttpClient) {}

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

    filterResolvedAlertsByDate(): void {
        const currentDate = new Date();
        currentDate.setDate(currentDate.getDate() - 7);

        this.alertsResolved = this.alertsResolved.filter((alert) => {
            const alertDate = new Date(alert.timestamp);
            return alertDate >= currentDate;
        });
    }

}