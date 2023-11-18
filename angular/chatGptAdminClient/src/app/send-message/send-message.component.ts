import { Component} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { timeout } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ConstantsService } from '../constants.service';

@Component({
  selector: 'app-send-message',
  templateUrl: './send-message.component.html',
  styleUrls: ['./send-message.component.css']
})
export class SendMessageComponent {
    constructor (private http: HttpClient, private router:Router, private constants: ConstantsService) {
			
    }

    sendMessage(text:string) {
        let data = {
          content: text
        };

        
        let chatId:string = sessionStorage.getItem('messageTo') || '';
        this.http.post<any>(this.constants.siteUrl+ 'admin/send-message/' + chatId, data)
        .pipe(timeout(5000))
        .subscribe({
          next: (response) => {
          sessionStorage.removeItem('messageTo');
          this.router.navigate(['/view-chats'])
        }
      })
    }
}
