import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {SingleAlertComponent} from "./single-alert/single-alert.component";
import {ServicemenDashboardComponent} from "./servicemen-dashboard/servicemen-dashboard.component";
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./auth-guard.guard";
import {ErrorPageComponent} from "./error-page/error-page.component";
import {AboutMeComponent} from "./about-me/about-me.component";
import {UserAlertsComponent} from "./about-me/user-alerts/user-alerts.component";

// todo: jak będzie podstrona dla pracownika to tu dodać
const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LoginComponent },
  { path: 'alerts/user', component: UserAlertsComponent, canActivate: [AuthGuard]},
  { path: 'alerts', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'alerts/:id', component: SingleAlertComponent, canActivate: [AuthGuard] },
  { path: 'users', component: ServicemenDashboardComponent, canActivate: [AuthGuard] },
  { path: 'me', component: AboutMeComponent, canActivate: [AuthGuard] },
  { path: '', pathMatch: 'full', canActivate: [AuthGuard], children: [
      { path: '', redirectTo: 'alerts', pathMatch: 'full' },
      { path: 'alerts', component: HomeComponent }
    ]},
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'error', component: ErrorPageComponent },
  { path: '**', redirectTo: '/error' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }