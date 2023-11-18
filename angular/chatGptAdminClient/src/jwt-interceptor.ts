import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable()
export class JwtInterceptor implements HttpInterceptor {

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
            if (!request.url.includes('api/auth')) {
                const token = sessionStorage.getItem("token");

                request = request.clone({
                    setHeaders: {
                        Authorization: 'Bearer ' + token
                    }
                });
            }
           
        return next.handle(request);
    }
}