import { Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../auth.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent {
    public form = new FormGroup({
        email: new FormControl('admin@example.com', Validators.required),
        password: new FormControl('admin', Validators.required)
    });

    constructor(private authService: AuthService, private router: Router) {}

    submitForm() {
        if (this.form.invalid) {
            window.alert('Wrong credentials');
            return;
        }

        const loginData = {
            email: this.form.controls.email.value,
            password: this.form.controls.password.value
        };

        this.authService.login(loginData).subscribe(() => {
            this.router.navigate(['/']);
        }, (error) => {
            console.log(error);
            window.alert('Login failed');
        });
    }
}