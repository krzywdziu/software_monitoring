import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-servicemen-dashboard',
  templateUrl: './servicemen-dashboard.component.html',
  styleUrls: ['./servicemen-dashboard.component.css']
})
export class ServicemenDashboardComponent implements OnInit {
  public servicemen: any[] = [];

  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  // todo: zmienic url gdy bedzie endpoint
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.http.get<any[]>('http://localhost:8080/user/all').subscribe(
        res => this.servicemen = res,
        err => console.log('Error: ', err)
      )
    })
  }
}
