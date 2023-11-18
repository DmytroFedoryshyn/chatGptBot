import { Component} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { timeout } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ConstantsService } from '../constants.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor (private http: HttpClient, private router:Router, private constants:ConstantsService) {
			
  }

  goToLogin() {
    this.router.navigate(['/login'])
  }
  
  register(loginParam: string, passwordParam: string) {
    const data = {
  		login: loginParam,
  		password: passwordParam,
      repeatPassword: passwordParam,
      role: "ADMIN"
		};

    this.http.post<any>(this.constants.siteUrl + 'auth/register', data)
    .pipe(timeout(5000))
    .subscribe({
      next: (response) => {
    	this.router.navigate(['/login'])
    },
  });
  }
}
