import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserStatsComponent } from './components/user-stats/user-stats.component';
import { GameStatsComponent } from './components/game-stats/game-stats.component';

const routes: Routes = [
  {
    path: "user-stats",
    component: UserStatsComponent
  },
  {
    path: "game-stats",
    component: GameStatsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
