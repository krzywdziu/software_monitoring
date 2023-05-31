import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { LoginService } from "./login.service";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    isAuthenticated_: boolean = false;
    private _isLoggedIn$ = new BehaviorSubject<boolean>(false);
    isLoggedIn$ = this._isLoggedIn$.asObservable();

    constructor(private loginService: LoginService) {
        const token = localStorage.getItem('auth_token');
        console.log(1, token)
        this._isLoggedIn$.next(!!token);
        this.isAuthenticated_ = this.checkAuthentication();
    }

    login(loginData: any) {
        return this.loginService.login(loginData).pipe(
            tap((response: any) => {
                this._isLoggedIn$.next(true);
                localStorage.setItem('auth_token', response);
                console.log(2, response)
                this.isAuthenticated_ = true;
            })
        );
    }

    checkAuthentication(): boolean {
        const token = localStorage.getItem('auth_token');
        console.log(3, token)
        return !!token;
    }

    isAuthenticated(): boolean {
        return this.isAuthenticated_;
    }

}
