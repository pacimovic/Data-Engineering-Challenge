import { Component } from '@angular/core';
import { GameStatsService } from 'src/app/services/game-stats.service';

@Component({
  selector: 'app-game-stats',
  templateUrl: './game-stats.component.html',
  styleUrls: ['./game-stats.component.css']
})
export class GameStatsComponent {

  date: string = ''
  country: boolean = false

  constructor(private gameStatsService: GameStatsService) {

  }

  getGameData(): void{

    var date = new Date(this.date)
    var nextDay = new Date(date.getTime())
    nextDay.setDate(date.getDate() + 1)
    var date1 = date.getTime()/1000
    var date2 = nextDay.getTime()/1000
    

    if(this.date === '' && this.country === false){
      this.gameStatsService.getNumOfActiveUsers().subscribe(numOfActiveUsers => console.log('Number of active users: ' + numOfActiveUsers))
      this.gameStatsService.getNumOfLogins().subscribe(numOfLogins => console.log('Number of logins: ' + numOfLogins))
      this.gameStatsService.getTotalRevenue().subscribe(totalRevenue => console.log('Total revenue: ' + totalRevenue))
      this.gameStatsService.getPaidUsers().subscribe(paidUsers => console.log('Number of paid users: ' + paidUsers))
      this.gameStatsService.getSessionsAvg().subscribe(sessionsAvg => console.log('Average number of sessions: ' + sessionsAvg))
      this.gameStatsService.getTimeAvg().subscribe(timeAvg => console.log('Average total time spent in game: ' + timeAvg))
    }
    else if(this.date != '' && this.country === false){
      this.gameStatsService.getNumOfActiveUsersDate(date1, date2).subscribe(numOfActiveUsers => console.log('Number of active users for day ' + this.date + ': ' + numOfActiveUsers))
      this.gameStatsService.getNumOfLoginsDate(date1, date2).subscribe(numOfLogins => console.log('Number of logins for day ' + this.date + ': ' + numOfLogins))
      this.gameStatsService.getTotalRevenueDate(date1, date2).subscribe(totalRevenue => console.log('Total revenue for day ' + this.date + ': ' + totalRevenue))
      this.gameStatsService.getPaidUsersDate(date1, date2).subscribe(paidUsers => console.log('Number of paid users for day ' + this.date + ': ' + paidUsers))
      this.gameStatsService.getSessionsAvgDate(date1, date2).subscribe(sessionsAvg => console.log('Average number of sessions for day ' + this.date + ': ' + sessionsAvg))
      this.gameStatsService.getTimeAvgDate(date1, date2).subscribe(timeAvg => console.log('Average total time spent in game for day ' + this.date + ': ' + timeAvg))
    }
    else if(this.date === '' && this.country === true){
      this.gameStatsService.getNumOfActiveUsersCountry().subscribe(countries => console.log(countries))
      this.gameStatsService.getNumOfLoginsCountry().subscribe(countries => console.log(countries))
      this.gameStatsService.getTotalRevenueCountry().subscribe(countries => console.log(countries))
      this.gameStatsService.getPaidUsersCountry().subscribe(countries => console.log(countries))
      this.gameStatsService.getSessionsAvgCountry().subscribe(countries => console.log(countries))
      this.gameStatsService.getTimeAvgCountry().subscribe(countries => console.log(countries))
    }
    else if(this.date != '' && this.country === true){
      this.gameStatsService.getNumOfActiveUsersCountryDate(date1, date2).subscribe(countries => console.log(countries))
      this.gameStatsService.getNumOfLoginsCountryDate(date1, date2).subscribe(countries => console.log(countries))
      this.gameStatsService.getTotalRevenueCountryDate(date1, date2).subscribe(countries => console.log(countries))
      this.gameStatsService.getPaidUsersCountryDate(date1, date2).subscribe(countries => console.log(countries))
      this.gameStatsService.getSessionsAvgCountryDate(date1, date2).subscribe(countries => console.log(countries))
      this.gameStatsService.getTimeAvgCountryDate(date1, date2).subscribe(countries => console.log(countries))
    }

  }





  isDateFormatValid(): boolean {
    var dateFormat = /^\d{4}-\d{2}-\d{2}$/;
    return dateFormat.test(this.date);
  }

}
