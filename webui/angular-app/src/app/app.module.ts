import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { CommonModule } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AppNavbarComponent } from './app-navbar/app-navbar.component';
import { SingleAlertComponent } from './single-alert/single-alert.component';
import { ServicemenDashboardComponent } from './servicemen-dashboard/servicemen-dashboard.component';
import {ReactiveFormsModule} from "@angular/forms";
import {AuthGuard} from "./auth-guard.guard";
import { LogoutService } from './logout.service';
import { ErrorPageComponent } from './error-page/error-page.component';
import { AboutMeComponent } from './about-me/about-me.component';
import { UserAlertsComponent } from './about-me/user-alerts/user-alerts.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    AppNavbarComponent,
    SingleAlertComponent,
    ServicemenDashboardComponent,
    ErrorPageComponent,
    AboutMeComponent,
    UserAlertsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule,
  ],
  providers: [AuthGuard, LogoutService],
  bootstrap: [AppComponent]
})
export class AppModule { }
