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
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {path: 'logout', component: LoginComponent },
  { path: 'alert/all', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'alert/:id', component: SingleAlertComponent },
  { path: 'user/all', component: ServicemenDashboardComponent},
  { path: '**', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }