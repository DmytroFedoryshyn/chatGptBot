import { Component} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { timeout } from 'rxjs/operators';
import { Router } from '@angular/router';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  login = ''

  constructor (private http: HttpClient, private router:Router) {
		this.login = sessionStorage.getItem('login') || '';
  }

  sendMessage() {
    this.router.navigate(['/send-message'])
  }

  viewChats() {
    this.router.navigate(['/view-chats'])
  }
}
