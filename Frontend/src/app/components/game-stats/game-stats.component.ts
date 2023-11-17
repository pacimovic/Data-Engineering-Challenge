import { Component } from '@angular/core';

@Component({
  selector: 'app-game-stats',
  templateUrl: './game-stats.component.html',
  styleUrls: ['./game-stats.component.css']
})
export class GameStatsComponent {

  date: string = ''
  country: boolean = false

  getGameStats(): void {
    console.log("Country: " + this.country + ", Date: " + this.date)
  }


  isDateFormatValid(): boolean {
    // Regular expression for the "YYYY-MM-DD" format
    var dateFormat = /^\d{4}-\d{2}-\d{2}$/;
    // Test if the date string matches the format
    return dateFormat.test(this.date);
  }

}
