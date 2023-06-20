import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
    selector: 'app-single-alert',
    templateUrl: './single-alert.component.html',
    styleUrls: ['./single-alert.component.css']
})
export class SingleAlertComponent implements OnInit {
    public alert: any = {};
    public selectedStatus: string = '';
    statusEnum: string[] = ['ASSIGNED', 'RESOLVED', 'WONT_FIX'];
    public id_user = localStorage.getItem('id_user');

    constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {
    }

    ngOnInit(): void {
        const id_token = localStorage.getItem('id_token');
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + id_token
        });
        const params = this.route.snapshot.params;
        const id = params['id'];

        this.http
            .get<any>(`http://localhost:8080/alerts/${id}`, {headers: headers, params})
            .subscribe(
                (res) => {this.alert = res; this.selectedStatus = this.alert.status;},
                (err) => console.log('Error: ', err)
            );

    }

    changeAlertStatus() {
        const id_token = localStorage.getItem('id_token');
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + id_token
        });
        const params = this.route.snapshot.params;
        const id = params['id'];

        if (this.selectedStatus) {
            this.http
                .request<any>('PUT', `http://localhost:8080/alerts/${id}/update?status=${this.selectedStatus}`,
                    { headers, responseType: 'text' as 'json'})
                .subscribe(
                    (res: HttpResponse<any>) => {
                        this.alert = res.body;
                        console.log('Success: ', res);
                        this.router.navigate(['/']);
                    },
                    err => console.log('Error: ', err)
                );

            //location.reload();
        }
    }

    assignToMe() {
        const id_token = localStorage.getItem('id_token');
        const id_user = localStorage.getItem('id_user');
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + id_token
        });
        const params = this.route.snapshot.params;
        const id = params['id'];

        this.http
            .request<any>('PUT', `http://localhost:8080/alerts/${id}/assign?userId=${id_user}`,
                { headers, responseType: 'text' as 'json'} )
            .subscribe(
                (res: HttpResponse<any>) => {
                    this.alert = res.body;
                    console.log('Success: ', res);
                    this.router.navigate(['/']);
                },
                err => console.log('Error: ', err)
            );

        //location.reload();
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
