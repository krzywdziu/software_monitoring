import { Injectable } from '@angular/core';
import {BehaviorSubject, catchError, Observable, throwError} from 'rxjs';
import { tap } from 'rxjs/operators';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private _isLoggedIn$ = new BehaviorSubject<boolean>(false);
    isLoggedIn$ = this._isLoggedIn$.asObservable();
    public headers: HttpHeaders = new HttpHeaders();
    public token: string | null = null;

    constructor(private http: HttpClient) {
        const token = localStorage.getItem('auth_token');
        this._isLoggedIn$.next(!!token);
    }

    login(loginData: any): Observable<any> {
        const headers = new HttpHeaders().set('Content-Type', 'application/json');
        return this.http.post('http://localhost:8080/token', loginData, { headers, observe: 'response', responseType: 'text'}).pipe(
            tap((response: any) => {
                if (response.headers && response.headers.has('Authorization')) {
                    const token = response.headers.get('Authorization');
                    if (token) {
                        this.token = token;
                        localStorage.setItem('auth_token', token);
                        this._isLoggedIn$.next(true);
                    }
                }
                console.log('Response token:', response);
            }),
            catchError(err => {
                console.log('Error: ', err);
                return throwError(err);
            })
        );
    }
}
