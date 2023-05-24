import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {SingleAlertComponent} from "./single-alert/single-alert.component";
import {ServicemenDashboardComponent} from "./servicemen-dashboard/servicemen-dashboard.component";
import {LoginComponent} from "./login/login.component";
import {AppNavbarComponent} from "./app-navbar/app-navbar.component";

// todo: jak będzie podstrona dla pracownika to tu dodać
// todo: zmienic endpoint dla dashboard
const routes: Routes = [
  // todo: zmienic pierwszy redirect na login, potem home
  {path: '', redirectTo: '/alert/all', pathMatch: 'full'},
  {path: 'alert/all', component: HomeComponent},
  {path: 'alert/:id', component: SingleAlertComponent},
  {path: 'user/all', component: ServicemenDashboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
