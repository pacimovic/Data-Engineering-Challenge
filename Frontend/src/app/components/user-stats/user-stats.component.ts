import { Component } from '@angular/core';
import { UserStatsService } from 'src/app/services/user-stats.service';

@Component({
  selector: 'app-user-stats',
  templateUrl: './user-stats.component.html',
  styleUrls: ['./user-stats.component.css']
})
export class UserStatsComponent {

  //form data
  user_id: string = ''
  date: string = ''

  //result data
  country: string = ''
  name: string = ''
  numberLogins: number = 0
  daysSinceLastLogin: number = 0
  numberOfSessions: number = 0

  constructor(private userStatsService: UserStatsService) {

  }

  getUserData(): void {

    this.userStatsService.getUserStats(this.user_id).subscribe(registration => {
      this.country = registration.country
      this.name = registration.name
    })

    this.userStatsService.getUserLogins(this.user_id).subscribe(userLogins => {
      this.numberLogins = userLogins.length

      var date1 = null
      if(this.date === '') date1 = new Date("2010-05-23"); //thats last date in dataset
      else date1 = new Date(this.date)

      var date2 = new Date(userLogins[0].event_timestamp * 1000)
      //console.log(date1)
      //console.log(date2)


      var Difference_In_Time = date1.getTime() - date2.getTime(); 
      var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24); 

      this.daysSinceLastLogin = Math.floor(Difference_In_Days)

    })

    this.userStatsService.getUserSessions(this.user_id).subscribe(userSessions => {
      
      if(this.date != ''){
        this.numberOfSessions = 0
        var date1 = new Date(this.date)
        var date2 
        console.log('Date in form: ' + date1)
        for(let i = 0; i < userSessions.length; i++){
          date2 = new Date(userSessions[i].logInTime*1000)
          console.log(date2)
          if(date1.getFullYear() == date2.getFullYear() && date1.getMonth() == date2.getMonth() && date1.getDate() == date2.getDate()){
            this.numberOfSessions++;
          }
        }
      }
      else{
        this.numberOfSessions = userSessions.length
      }
    })

  }

  isDateFormatValid(): boolean {
    var dateFormat = /^\d{4}-\d{2}-\d{2}$/;
    return dateFormat.test(this.date);
  }
}
