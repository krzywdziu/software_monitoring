import { Component } from '@angular/core';

@Component({
  selector: 'app-servicemen-dashboard',
  templateUrl: './servicemen-dashboard.component.html',
  styleUrls: ['./servicemen-dashboard.component.css']
})
export class ServicemenDashboardComponent {
  public servicemen: any[] = [
    {
      "id": 0,
      "firstName": "Jan",
      "lastName": "Kowalski",
      "phoneNumber": "123123123",
      "email": "kowalski@gamil.com",
    }, {
      "id": 1,
      "firstName": "Piotr",
      "lastName": "Nowak",
      "phoneNumber": "321321321",
      "email": "pnowak@gamil.com",
    }]
}
