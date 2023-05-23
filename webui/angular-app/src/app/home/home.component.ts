import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  public alerts: any[] = [{"id":1,"description":"Docker container down","status":"IN_PROGRESS","boxIp":"10.0.0.17","severity":"ERROR","url":null,"timestamp":"2023-05-21T20:39:48"},{"id":2,"description":"Server down","status":"WONT_FIX","boxIp":"10.0.0.18","severity":"CRITICAL","url":null,"timestamp":"2023-05-21T20:39:48"},{"id":3,"description":"Log rotation needed","status":"IN_PROGRESS","boxIp":"10.0.0.19","severity":"NOTICE","url":null,"timestamp":"2023-05-21T20:39:48"},{"id":4,"description":"Load average too high","status":"UNASSIGNED","boxIp":"10.0.0.20","severity":"WARNING","url":null,"timestamp":"2023-05-21T20:39:48"},{"id":5,"description":"Network connection error","status":"UNASSIGNED","boxIp":"10.0.0.21","severity":"ERROR","url":null,"timestamp":"2023-05-21T20:39:48"}]

}
