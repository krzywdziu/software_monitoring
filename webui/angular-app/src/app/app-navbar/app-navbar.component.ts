import { Component } from '@angular/core';
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-app-navbar',
  templateUrl: './app-navbar.component.html',
  styleUrls: ['./app-navbar.component.css']
})
export class AppNavbarComponent {

  constructor(private authService: AuthService) {
  }
  logut() {
    this.authService.logout()
    const token = localStorage.getItem('id_token');
    this.authService._isLoggedIn$.next(!!token);
  }

}
