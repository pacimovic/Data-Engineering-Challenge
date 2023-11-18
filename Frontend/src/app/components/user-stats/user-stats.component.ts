import { Component } from '@angular/core';
import { UserStatsService } from 'src/app/services/user-stats.service';

@Component({
  selector: 'app-user-stats',
  templateUrl: './user-stats.component.html',
  styleUrls: ['./user-stats.component.css']
})
export class UserStatsComponent {

  user_id: string = ''
  date: string = ''

  constructor(private userStatsService: UserStatsService){

  }

  getUserStats(): void {
    
    this.userStatsService.getUserStats().subscribe(transactions => {
      console.log(transactions)
    })

  }

  isDateFormatValid(): boolean {
    // Regular expression for the "YYYY-MM-DD" format
    var dateFormat = /^\d{4}-\d{2}-\d{2}$/;
    // Test if the date string matches the format
    return dateFormat.test(this.date);
  }
}
