import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SingleAlertComponent} from "./single-alert/single-alert.component";

// todo: jak będzie podstrona dla pracownika to tu dodać
const routes: Routes = [
  {path: 'alert/id', component: SingleAlertComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
