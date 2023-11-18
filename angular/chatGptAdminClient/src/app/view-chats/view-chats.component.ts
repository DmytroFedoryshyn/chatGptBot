import { Component} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { timeout } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ConstantsService } from '../constants.service';

@Component({
  selector: 'app-view-chats',
  templateUrl: './view-chats.component.html',
  styleUrls: ['./view-chats.component.css']
})
export class ViewChatsComponent {
  list: any[] = []

  pageNumber: number = 0;
  pageSize: number = 5;

  constructor (private http: HttpClient, private router: Router, private constants: ConstantsService) {
    this.refreshList();
  }

  refreshList() {
    this.http.get<any[]>(this.constants.siteUrl + 'admin/telegram_users?page='
    +this.pageNumber + "&" + "size=" + this.pageSize)
    .pipe(timeout(5000))
    .subscribe({
      next: (response) => {
        this.list = response.map(item => {
          return {
            chatId: item.chatId,
            fullName: item.fullName
          };
        });
    }
  });
  }

  sendMessage(obj:any) { 
    sessionStorage.setItem('messageTo', obj.chatId);
    this.router.navigate(['/send-message'])
  }

  viewChatHistory(obj:any) {
    sessionStorage.setItem('viewChatOf', obj.chatId);
    this.router.navigate(['/chat-history'])
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
