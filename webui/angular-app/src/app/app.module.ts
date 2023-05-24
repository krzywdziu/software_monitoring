import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AppNavbarComponent } from './app-navbar/app-navbar.component';
import { SingleAlertComponent } from './single-alert/single-alert.component';
import { ServicemenDashboardComponent } from './servicemen-dashboard/servicemen-dashboard.component';
import { AlertHistoryComponent } from './alert-history/alert-history.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    AppNavbarComponent,
    SingleAlertComponent,
    ServicemenDashboardComponent,
    AlertHistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
