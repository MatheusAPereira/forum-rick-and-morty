import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf} from '@angular/common';
import { EpisodeService } from '../services/episode.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentService } from '../services/comment.service';
import { Comment } from '../models/comment.model';
import { Episode } from '../models/episode.model';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

@Component({
  selector: 'app-episode',
  standalone: true,
  imports: [
    NgFor,
    NgIf,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    
  ],
  templateUrl: './episode.component.html',
  styleUrl: './episode.component.css'
})

export class EpisodeComponent implements OnInit{

  episodeId: string | null = null;

  comments: Comment[] = []

  episode: Episode = {}

  //newComment!: Comment;

  commentFormGroup!: FormGroup;

  newComment: Comment = {
      userName: '',
      content: '',
      date: ''
  };

  constructor(
    private router: ActivatedRoute,
    private commentService: CommentService,
    private episodeService: EpisodeService,
    private formBuilder: FormBuilder,
  ){
    this.validateCommentFormGroup();
  }

  ngOnInit(){

    this.episodeId = this.router.snapshot.paramMap.get('id');

    if(this.episodeId){
        this.commentService.getComments(this.episodeId).subscribe(comments => {
            this.comments = comments.items;
            console.log(this.comments)
        })

        this.episodeService.getEpisode(this.episodeId).subscribe(episode => {
            this.episode = episode;
        })
    }

  }

  validateCommentFormGroup() {
    this.commentFormGroup = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      message: ['', [Validators.required, Validators.minLength(7)]]
    });
  }

  sendFormComment(){
    const controls = this.commentFormGroup.controls;
    this.newComment.userName = controls['name'].value;
    this.newComment.content = controls['message'].value;
    const date = new Date();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    const seconds = date.getSeconds().toString().padStart(2, '0');
    const formattedDate = `${day}-${month}-${date.getFullYear()} ${hours}:${minutes}:${seconds}`;
    this.newComment.date = formattedDate;
    this.newComment.episodeId = this.episode.id;
    this.commentService.sendComment(this.newComment).subscribe((response) => {
      console.log('Associado criado com sucesso');
      this.commentFormGroup.reset();
    });
  }

}
