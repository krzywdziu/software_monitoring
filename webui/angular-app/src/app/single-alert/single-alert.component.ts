import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-single-alert',
    templateUrl: './single-alert.component.html',
    styleUrls: ['./single-alert.component.css']
})
export class SingleAlertComponent implements OnInit {
    public alert: any = {};
    public selectedStatus: string = '';
    statusEnum: string[] = ['IN_PROGRESS', 'RESOLVED', 'WONT_FIX'];
    public id_user = localStorage.getItem('id_user');

    constructor(private http: HttpClient, private route: ActivatedRoute) {
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
                (res) => (this.alert = res),
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
                    },
                    err => console.log('Error: ', err)
                );

            location.reload();
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
                },
                err => console.log('Error: ', err)
            );

        location.reload();
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
}
