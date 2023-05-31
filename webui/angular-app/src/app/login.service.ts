import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {}

  login(loginData: any) {
    return this.http.post('http://localhost:8080/token', loginData, {responseType: 'text'});
  }
}
