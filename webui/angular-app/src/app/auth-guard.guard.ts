import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}
  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (localStorage.getItem('id_token') != '' && localStorage.getItem('id_token') != null
        && localStorage.getItem('id_token') != 'undefined') {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}