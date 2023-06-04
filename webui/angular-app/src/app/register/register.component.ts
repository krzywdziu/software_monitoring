import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {tap} from "rxjs/operators";
import {catchError, throwError} from "rxjs";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  public form = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  constructor(private http: HttpClient) {
    const token = localStorage.getItem('id_token');
  }

  submitForm() {
    if (this.form.invalid) {
      window.alert('Wrong credentials');
      return;
    }

    const registerData = {
      firstName: this.form.controls.firstName.value,
      lastName: this.form.controls.lastName.value,
      email: this.form.controls.email.value,
      password: this.form.controls.password.value
    };

    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    this.http.post('http://localhost:8080/auth/register', registerData,
        { headers: headers, observe: 'response', responseType: 'text'})
        .pipe(
            tap((response: any) => {
              if (response.status === 200) {
                console.log('User registered successfully!');
                window.alert('Zarejestrowano pomyślnie');
              } else {
                console.log('Registration failed.');
              }
            }),
            catchError(err => {
              console.log('Error: ', err);
              window.alert('Registration failed');
              return throwError(err);
            })
        )
        .subscribe();
  }

}
