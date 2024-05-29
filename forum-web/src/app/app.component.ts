import { Component, NgModule, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';
import { MatListModule } from '@angular/material/list';
import { HttpClientModule, provideHttpClient } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Episode } from './models/episode.model';
import { Response } from './models/response.model';
import { EpisodeService } from './services/episode.service';
import { NgIf } from '@angular/common';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    MatToolbarModule,
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    MatPaginatorModule,
    MatSelectModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    RouterModule
  ],
  providers: [
    HttpClient,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent{

  title: string = "";

  private allEpisodes: Episode[] = [];
  
 

  links: string[] = [':4200/episodes', ':4200', ':4200']

  displayedColumns: string[] = ['position', 'code', 'name'];

  response: any;

  responseEpisode$!: Observable<Response<Episode[]>>;

  constructor(private episodeService: EpisodeService){
  
    
  }

  ngOnInit(): void{

  }
}
