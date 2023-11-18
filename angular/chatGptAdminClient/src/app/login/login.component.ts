import { Component} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { timeout } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ConstantsService } from '../constants.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username = '';
  password = '';
  message = '';

  constructor (private http: HttpClient, private router:Router, private constants:ConstantsService) {
			
  }

  login(us: string, pass:string) {
    this.username = us;
		this.password = pass;

		const data = {
  		login: this.username,
  		password: this.password,
      repeatPassword: this.password
		};

    this.http.post<any>(this.constants.siteUrl + 'auth/login', data)
    .pipe(timeout(5000))
    .subscribe({
      next: (response) => {
      sessionStorage.setItem('token', response.token);
      sessionStorage.setItem('login', data.login);
    	this.router.navigate(['/admin'])
    },
    error: () => {
      this.message = "Incorrect login or password";
    }
  });
  }

  goToRegister() {
    this.router.navigate(['/register'])
  }
}
