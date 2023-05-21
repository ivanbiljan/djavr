import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from '../constants/app.constants';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor() { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log("intercepting")
        if (!request || !request.url || (request.url.startsWith('http') && !(SERVER_API_URL && request.url.startsWith(SERVER_API_URL)))) {
            return next.handle(request);
        }

        const token = localStorage.getItem('token');
        if (token) {
            console.log("found " + token);
            request = request.clone({
                headers: request.headers.set("Authorization", "Bearer " + token)
            });
        }

        // add content type
        if (!request.headers.has('Content-Type')) {
            request = request.clone({
                setHeaders: {
                    'Content-Type': 'application/json'
                }
            });
        }

        return next.handle(request);
    }

}
