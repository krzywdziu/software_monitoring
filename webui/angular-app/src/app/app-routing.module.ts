import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {SingleAlertComponent} from "./single-alert/single-alert.component";
import {ServicemenDashboardComponent} from "./servicemen-dashboard/servicemen-dashboard.component";
import {LoginComponent} from "./login/login.component";
// import {AppNavbarComponent} from "./app-navbar/app-navbar.component";
// import {AuthGuard} from "./auth-guard.service";

// todo: jak będzie podstrona dla pracownika to tu dodać
// todo: zmienic endpoint dla dashboard
const routes: Routes = [
  // todo: zmienic pierwszy redirect na login, potem home
  { path: 'alert/all', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'alert/:id', component: SingleAlertComponent, canActivate: [AuthGuard] },
  { path: 'user/all', component: ServicemenDashboardComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: '**', component: HomeComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
