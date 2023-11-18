import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { HTTP_INTERCEPTORS } from '@angular/common/http';


import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { SendMessageComponent } from './send-message/send-message.component';
import { ViewChatsComponent } from './view-chats/view-chats.component';
import { JwtInterceptor } from 'src/jwt-interceptor';
import { ChatHistoryComponent } from './chat-history/chat-history.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    SendMessageComponent,
    ViewChatsComponent,
    ChatHistoryComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'login', component: LoginComponent},
      {path: 'admin', component: AdminComponent},
      {path: 'send-message', component: SendMessageComponent},
      {path: 'view-chats', component: ViewChatsComponent},
      {path: 'chat-history', component: ChatHistoryComponent},
      {path: 'register', component: RegisterComponent},
    ])
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
