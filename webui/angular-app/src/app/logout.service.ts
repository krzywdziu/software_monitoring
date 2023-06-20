import { Injectable } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class LogoutService {
  constructor(private router: Router, private authService: AuthService) {
    this.router.events.subscribe(event => {
      if (router.url.includes('logout')) {
        this.authService.logout()
        const token = localStorage.getItem('id_token');
        this.authService._isLoggedIn$.next(!!token);
      }
    });
  }
}
