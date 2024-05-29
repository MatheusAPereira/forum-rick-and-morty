import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Comment } from '../models/comment.model';
import { Response } from '../models/response.model';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private url = environment.api;

  constructor(private http: HttpClient) {

  }

  getComments(episodeId: string): Observable<Response<Comment[]>>{
      const fullUrl: string = `${this.url}/episodes/${episodeId}/comments`

      return this.http.get<Response<Comment[]>>(fullUrl);
  }

  sendComment(newComment: Comment): Observable<Comment>{
      const fullUrl: string = `${this.url}/comments`;

      console.log(fullUrl)

      return this.http.post<Comment>(fullUrl, newComment) as Observable<Comment>;
  }
}
