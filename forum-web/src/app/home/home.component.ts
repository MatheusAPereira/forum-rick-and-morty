import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { EpisodeService } from '../services/episode.service';
import { Episode } from '../models/episode.model';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { RouterOutlet, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';
import { MatListModule } from '@angular/material/list';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  changeDetection: ChangeDetectionStrategy.OnPush,
  imports: [
    RouterOutlet,
    RouterModule,
    MatToolbarModule,
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule,
    MatSelectModule,
    FormsModule,
    MatListModule,
    HttpClientModule,
    CommonModule,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  displayedColumns: string[] = ['position', 'code', 'name'];

  episodes: Episode[] = [];

  dataSource = new MatTableDataSource<Episode>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private episodeService: EpisodeService,
    private cd: ChangeDetectorRef
  ){}

  ngOnInit(): void{

    this.listAllEpisodes();
  }

  listEpisodes(){
    this.episodeService.getEpisodes()
    .subscribe((response) => {
      console.log(response)
      this.episodes = response.items;
      this.cd.detectChanges();
    })
  }

  listAllEpisodes(){
    this.episodeService.getAllEpisodes()
    .subscribe((response) => {
      console.log(response)
      let episodeList: Episode[] = response;
      this.dataSource = new MatTableDataSource(episodeList)
      this.dataSource.paginator = this.paginator
    })
  }
}
