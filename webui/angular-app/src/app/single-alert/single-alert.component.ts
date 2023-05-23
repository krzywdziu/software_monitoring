import { Component } from '@angular/core';

@Component({
  selector: 'app-single-alert',
  templateUrl: './single-alert.component.html',
  styleUrls: ['./single-alert.component.css']
})
export class SingleAlertComponent {
  public alert = {"id":1,"description":"Docker container down","status":"IN_PROGRESS","boxIp":"10.0.0.17","severity":"ERROR","url":null,"timestamp":"2023-05-21T20:39:48"}
}
