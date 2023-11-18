import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConstantsService {
  siteUrl: string = 'http://13.50.4.251:8080/api/';
  constructor() { }
}
