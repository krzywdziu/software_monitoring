import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-alert-history',
  templateUrl: './alert-history.component.html',
  styleUrls: ['./alert-history.component.css']
})
export class AlertHistoryComponent {
  public alerts: any[] = [];

  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.http.get<any[]>('http://localhost:8080/alert/all').subscribe(
          res => this.alerts = res,
          err => console.log('Error: ', err)
      )
    })
  }
}
