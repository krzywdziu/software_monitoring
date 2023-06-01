import { Injectable } from '@angular/core';
import {BehaviorSubject, catchError, Observable, throwError} from 'rxjs';
import { tap } from 'rxjs/operators';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    _isLoggedIn$ = new BehaviorSubject<boolean>(false);
    isLoggedIn$ = this._isLoggedIn$.asObservable();
    token: string | null = null;

    constructor(private http: HttpClient) {
        const token = localStorage.getItem('id_token');
        this._isLoggedIn$.next(!!token);
    }

    login(loginData: any): Observable<any> {
        const headers = new HttpHeaders().set('Content-Type', 'application/json');
        return this.http.post('http://localhost:8080/token', loginData,
            { headers: headers, observe: 'response', responseType: 'text'})
            .pipe(
            tap((response: any) => {
                const token = response.body
                this.token = token;
                localStorage.setItem('id_token', token);
                this._isLoggedIn$.next(true);
            }),
            catchError(err => {
                console.log('Error: ', err);
                return throwError(err);
            })
        );
    }

    logout() {
        localStorage.setItem('id_token', '')
        this.token = localStorage.getItem('id_token')
    }
}
