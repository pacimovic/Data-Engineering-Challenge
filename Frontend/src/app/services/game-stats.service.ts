import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Country } from '../model';

@Injectable({
  providedIn: 'root'
})
export class GameStatsService {

  private readonly apiUrl = environment.api

  constructor(private httpClient: HttpClient) { }

  //Active users
  getNumOfActiveUsers(): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/log_in_outs`)
  }
  getNumOfActiveUsersDate(date1: number, date2: number): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/log_in_outs/${date1}/${date2}`)
  }
  getNumOfActiveUsersCountry(): Observable<Country[]> {
    return this.httpClient.get<Country[]>(`${this.apiUrl}/log_in_outs/country`)
  }
  getNumOfActiveUsersCountryDate(date1: number, date2: number): Observable<Country[]> {
    return this.httpClient.get<Country[]>(`${this.apiUrl}/log_in_outs/country/${date1}/${date2}`)
  }

  //logins
  getNumOfLogins(): Observable<number>{
    return this.httpClient.get<number>(`${this.apiUrl}/log_in_outs/logins`)
  }
  getNumOfLoginsDate(date1: number, date2: number): Observable<number>{
    return this.httpClient.get<number>(`${this.apiUrl}/log_in_outs/logins/${date1}/${date2}`)
  }
  getNumOfLoginsCountry(): Observable<Country[]>{
    return this.httpClient.get<Country[]>(`${this.apiUrl}/log_in_outs/logins/country`)
  }
  getNumOfLoginsCountryDate(date1: number, date2: number): Observable<Country[]>{
    return this.httpClient.get<Country[]>(`${this.apiUrl}/log_in_outs/logins/country/${date1}/${date2}`)
  }

  //total revenue
  getTotalRevenue(): Observable<number>{
    return this.httpClient.get<number>(`${this.apiUrl}/transactions`)
  }
  getTotalRevenueDate(date1: number, date2: number): Observable<number>{
    return this.httpClient.get<number>(`${this.apiUrl}/transactions/${date1}/${date2}`)
  }
  getTotalRevenueCountry(): Observable<Country[]>{
    return this.httpClient.get<Country[]>(`${this.apiUrl}/transactions/country`)
  }
  getTotalRevenueCountryDate(date1: number, date2: number): Observable<Country[]>{
    return this.httpClient.get<Country[]>(`${this.apiUrl}/transactions/country/${date1}/${date2}`)
  }

  //paid users
  getPaidUsers(): Observable<number>{
    return this.httpClient.get<number>(`${this.apiUrl}/registrations`)
  }
  getPaidUsersDate(date1: number, date2: number): Observable<number>{
    return this.httpClient.get<number>(`${this.apiUrl}/registrations/${date1}/${date2}`)
  }
  getPaidUsersCountry(): Observable<Country[]>{
    return this.httpClient.get<Country[]>(`${this.apiUrl}/registrations/country`)
  }
  getPaidUsersCountryDate(date1: number, date2: number): Observable<Country[]>{
    return this.httpClient.get<Country[]>(`${this.apiUrl}/registrations/country/${date1}/${date2}`)
  }

  //number of sessions
  getSessionsAvg(): Observable<number>{
    return this.httpClient.get<number>(`${this.apiUrl}/sessions`)
  }
  getSessionsAvgDate(date1: number, date2: number): Observable<number>{
    return this.httpClient.get<number>(`${this.apiUrl}/sessions/${date1}/${date2}`)
  }
  getSessionsAvgCountry(): Observable<Country[]>{
    return this.httpClient.get<Country[]>(`${this.apiUrl}/sessions/country`)
  }
  getSessionsAvgCountryDate(date1: number, date2: number): Observable<Country[]>{
    return this.httpClient.get<Country[]>(`${this.apiUrl}/sessions/country/${date1}/${date2}`)
  }

  //total time spent
  getTimeAvg(): Observable<number>{
    return this.httpClient.get<number>(`${this.apiUrl}/sessions/time`)
  }
  getTimeAvgDate(date1: number, date2: number): Observable<number>{
    return this.httpClient.get<number>(`${this.apiUrl}/sessions/time/${date1}/${date2}`)
  }
  getTimeAvgCountry(): Observable<Country[]>{
    return this.httpClient.get<Country[]>(`${this.apiUrl}/sessions/time/country`)
  }
  getTimeAvgCountryDate(date1: number, date2: number): Observable<Country[]>{
    return this.httpClient.get<Country[]>(`${this.apiUrl}/sessions/time/country/${date1}/${date2}`)
  }
}
