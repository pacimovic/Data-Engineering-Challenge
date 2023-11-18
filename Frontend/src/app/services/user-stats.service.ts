import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Log, Registration, Transaction } from '../model';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class UserStatsService {

  private readonly apiUrl = environment.userStatsApi

  constructor(private httpClient: HttpClient) { }

  getUserStats(user_id: string): Observable<Registration> {
    return this.httpClient.get<Registration>(`${this.apiUrl}/registrations/${user_id}`)
  }

  getUserLogins(user_id: string): Observable<Log[]> {
    return this.httpClient.get<Log[]>(`${this.apiUrl}/log_in_outs/${user_id}`)
  }
}
