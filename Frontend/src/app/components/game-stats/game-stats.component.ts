import { Component } from '@angular/core';
import { Country } from 'src/app/model';
import { GameStatsService } from 'src/app/services/game-stats.service';

@Component({
  selector: 'app-game-stats',
  templateUrl: './game-stats.component.html',
  styleUrls: ['./game-stats.component.css']
})
export class GameStatsComponent {

  //form data
  date: string = ''
  country: boolean = false

  //result data
  numOfActiveUsers: number = 0
  numOfLogins: number = 0
  totalRevenue: number = 0
  paidUsers: number = 0
  sessionAvg: number = 0
  timeAvg: number = 0

  foundObject: Country = {
    country: '',
    intValue: 0,
    floatValue: 0
  }

  numOfActiveUsersCountries: Country[] = []
  numOfLoginsCountries: Country[] = []
  totalRevenueCountries: Country[] = []
  paidUsersCountries: Country[] = []
  sessionAvgCountries: Country[] = []
  timeAvgCountries: Country[] = []

  constructor(private gameStatsService: GameStatsService) {

  }

  getGameData(): void{

    var date = new Date(this.date)
    var nextDay = new Date(date.getTime())
    nextDay.setDate(date.getDate() + 1)
    var date1 = date.getTime()/1000
    var date2 = nextDay.getTime()/1000
        
    if(this.date === '' && this.country === false){
      this.gameStatsService.getNumOfActiveUsers().subscribe(numOfActiveUsers => this.numOfActiveUsers = numOfActiveUsers)
      this.gameStatsService.getNumOfLogins().subscribe(numOfLogins => this.numOfLogins = numOfLogins)
      this.gameStatsService.getTotalRevenue().subscribe(totalRevenue => this.totalRevenue = Math.round(totalRevenue))
      this.gameStatsService.getPaidUsers().subscribe(paidUsers => this.paidUsers = paidUsers)
      this.gameStatsService.getSessionsAvg().subscribe(sessionsAvg => this.sessionAvg =  Math.round(sessionsAvg))
      this.gameStatsService.getTimeAvg().subscribe(timeAvg => this.timeAvg =  Math.round(timeAvg))
    }
    else if(this.date != '' && this.country === false){
      this.gameStatsService.getNumOfActiveUsersDate(date1, date2).subscribe(numOfActiveUsers => this.numOfActiveUsers = numOfActiveUsers)
      this.gameStatsService.getNumOfLoginsDate(date1, date2).subscribe(numOfLogins => this.numOfLogins = numOfLogins)
      this.gameStatsService.getTotalRevenueDate(date1, date2).subscribe(totalRevenue => this.totalRevenue = Math.round(totalRevenue))
      this.gameStatsService.getPaidUsersDate(date1, date2).subscribe(paidUsers => this.paidUsers = paidUsers)
      this.gameStatsService.getSessionsAvgDate(date1, date2).subscribe(sessionsAvg => this.sessionAvg =  Math.round(sessionsAvg))
      this.gameStatsService.getTimeAvgDate(date1, date2).subscribe(timeAvg => this.timeAvg =  Math.round(timeAvg) )
    }
    else if(this.date === '' && this.country === true){
      this.gameStatsService.getNumOfActiveUsersCountry().subscribe(countries => this.numOfActiveUsersCountries = countries)
      this.gameStatsService.getNumOfLoginsCountry().subscribe(countries => this.numOfLoginsCountries = countries)
      this.gameStatsService.getTotalRevenueCountry().subscribe(countries => this.totalRevenueCountries = countries)
      this.gameStatsService.getPaidUsersCountry().subscribe(countries => this.paidUsersCountries = countries)
      this.gameStatsService.getSessionsAvgCountry().subscribe(countries => this.sessionAvgCountries = countries)
      this.gameStatsService.getTimeAvgCountry().subscribe(countries => this.timeAvgCountries = countries)
    }
    else if(this.date != '' && this.country === true){
      this.gameStatsService.getNumOfActiveUsersCountryDate(date1, date2).subscribe(countries => this.numOfActiveUsersCountries = countries)
      this.gameStatsService.getNumOfLoginsCountryDate(date1, date2).subscribe(countries => this.numOfLoginsCountries = countries)
      this.gameStatsService.getTotalRevenueCountryDate(date1, date2).subscribe(countries => this.totalRevenueCountries = countries)
      this.gameStatsService.getPaidUsersCountryDate(date1, date2).subscribe(countries => this.paidUsersCountries = countries)
      this.gameStatsService.getSessionsAvgCountryDate(date1, date2).subscribe(countries => this.sessionAvgCountries = countries)
      this.gameStatsService.getTimeAvgCountryDate(date1, date2).subscribe(countries => this.timeAvgCountries = countries)
    }

  }



  isDateFormatValid(): boolean {
    var dateFormat = /^\d{4}-\d{2}-\d{2}$/;
    return dateFormat.test(this.date);
  }

}
