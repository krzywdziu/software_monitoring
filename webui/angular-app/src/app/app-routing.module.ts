import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {SingleAlertComponent} from "./single-alert/single-alert.component";
import {ServicemenDashboardComponent} from "./servicemen-dashboard/servicemen-dashboard.component";
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./auth-guard.guard";

// todo: jak będzie podstrona dla pracownika to tu dodać
// todo: zmienic endpoint dla dashboard
const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LoginComponent },
  { path: 'alerts/all', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'alerts/:id', component: SingleAlertComponent, canActivate: [AuthGuard] },
  { path: 'users/all', component: ServicemenDashboardComponent, canActivate: [AuthGuard] },
  { path: '**', component: HomeComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }