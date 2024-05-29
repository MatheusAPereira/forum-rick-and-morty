import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Episode } from '../models/episode.model';
import { Response } from '../models/response.model';
import { Observable } from 'rxjs';
import { HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})


export class EpisodeService{

    private url = `${environment.api}/episodes`

    constructor(private http: HttpClient) {
    }
    
    getEpisodes(): Observable<Response<Episode[]>>{
      return this.http.get<Response<Episode[]>>(this.url)
    }

    getAllEpisodes(): Observable<Episode[]>{
      return this.http.get<Episode[]>(`${this.url}/all`)
    }

    getEpisode(episodeId: string): Observable<Episode>{
      return this.http.get<Episode>(`${this.url}/${episodeId}`)
    }
}