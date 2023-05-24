import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-single-alert',
  templateUrl: './single-alert.component.html',
  styleUrls: ['./single-alert.component.css']
})
export class SingleAlertComponent implements OnInit{
  public alert: any = {};

  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['id'];

      this.http.get<any>(`http://localhost:8080/alert/${id}`).subscribe(
        res => this.alert = res,
        err => console.log('Error: ', err)
      )
    })
  }
}
