import { Component} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { timeout } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ConstantsService } from '../constants.service';

@Component({
  selector: 'app-chat-history',
  templateUrl: './chat-history.component.html',
  styleUrls: ['./chat-history.component.css']
})
export class ChatHistoryComponent {
  list: any[] = []
  username = '';

  title = "Chat History";
  pageNumber: number = 0;
  pageSize: number = 5;

  constructor(private http: HttpClient, private router: Router, private constants: ConstantsService) {
    this.refreshList();
    this.updateTitle();
  }

  updateTitle() {
    let chatId:string = sessionStorage.getItem('viewChatOf') || '';

    this.http.get<any>(this.constants.siteUrl + 'admin/telegram_user/' + chatId)
    .pipe(timeout(5000))
    .subscribe({
        next: (response) => {
          this.title = response.fullName + " " + this.title;
          this.username = response.fullName.split('-')[0];
        },
        error: (error) => {
            console.error('Error fetching data:', error);
        }
    });
  }

  refreshList() {
    let chatId:string = sessionStorage.getItem('viewChatOf') || '';
    console.log(chatId);

    this.http.get<any[]>(this.constants.siteUrl + 'admin/message-history/' + chatId + '?page='
    + this.pageNumber + "&" + "size=" + this.pageSize)
    .pipe(timeout(5000))
    .subscribe({
      next: (response) => {
        this.list = response.map(item => {
          return {
            role: item.role,
            content: item.content
          };
        });
        console.log( this.list);
    }
  });
  }

  onPageNumberChange(event: Event) {
    this.pageNumber = +(event.target as HTMLInputElement).value;
    if (this.pageNumber < 0) {
      this.pageNumber = 0;
      (event.target as HTMLInputElement).value = "0";
    }
    this.refreshList();
  }

  onPageSizeChange(event: Event) {
    this.pageSize = +(event.target as HTMLInputElement).value;
    if (this.pageSize < 1) {
      this.pageSize = 1;
      (event.target as HTMLInputElement).value = "1";
    }
    this.refreshList();
  }
}
