import { Component } from '@angular/core';

@Component({
  selector: 'app-user-stats',
  templateUrl: './user-stats.component.html',
  styleUrls: ['./user-stats.component.css']
})
export class UserStatsComponent {

  user_id: string = ''
  date: string = ''


  getUserStats(): void {
    console.log("User id: " + this.user_id + ", Date: " + this.date)
  }


  isDateFormatValid(): boolean {
    // Regular expression for the "YYYY-MM-DD" format
    var dateFormat = /^\d{4}-\d{2}-\d{2}$/;
    // Test if the date string matches the format
    return dateFormat.test(this.date);
}
}
