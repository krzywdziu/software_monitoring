import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
    public alerts: any[] = [];

    constructor(private http: HttpClient) {}

    ngOnInit(): void {
        const id_token = localStorage.getItem('id_token')

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${id_token}`
        })

        console.log('Authorization header: ', headers.get('Authorization'))
        console.log('Header: ', headers)


        this.http.get<any[]>('http://localhost:8080/alert/all', { headers: headers })
            .subscribe(
                (res: any) => {this.alerts = res; },
                (err: any) => console.log('Error: ', err)
            );
    }
}