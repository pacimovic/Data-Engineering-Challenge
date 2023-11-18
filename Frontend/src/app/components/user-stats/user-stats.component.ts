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

  constructor(private userStatsService: UserStatsService) {

  }

  getUserStats(): void {

    this.userStatsService.getUserStats(this.user_id).subscribe(registration => {
      this.country = registration.country
      this.name = registration.name
    })

    this.userStatsService.getUserLogins(this.user_id).subscribe(userLogins => {
      this.numberLogins = userLogins.length

      var date1 = null
      if(this.date === '') date1 = new Date("05/23/2010");
      else date1 = new Date(this.date)

      var date2 = new Date(userLogins[0].event_timestamp * 1000)
      console.log(date1)
      console.log(date2)


      var Difference_In_Time = date1.getTime() - date2.getTime(); 
      var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24); 

      this.daysSinceLastLogin = Math.floor(Difference_In_Days) + 1

    })

  }

  isDateFormatValid(): boolean {
    // Regular expression for the "YYYY-MM-DD" format
    var dateFormat = /^\d{4}-\d{2}-\d{2}$/;
    // Test if the date string matches the format
    return dateFormat.test(this.date);
  }
}
