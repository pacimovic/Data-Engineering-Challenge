import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../model';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class UserStatsService {

  private readonly apiUrl = environment.userStatsApi

  constructor(private httpClient: HttpClient) { }

  getUserStats(): Observable<Transaction> {
    return this.httpClient.get<Transaction>(`${this.apiUrl}/transactions`);
  }
}
