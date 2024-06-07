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
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';

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
    MatSnackBarModule,
    
  ],
  templateUrl: './episode.component.html',
  styleUrl: './episode.component.css'
})

export class EpisodeComponent implements OnInit{

  episodeId: string | null = null;

  comments: Comment[] = []

  episode: Episode = {}

  commentFormGroup!: FormGroup;

  newComment: Comment = {
      userName: '',
      content: '',
      date: ''
  };

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private commentService: CommentService,
    private episodeService: EpisodeService,
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar
  ){
    this.validateCommentFormGroup();
  }

  ngOnInit(){

    this.episodeId = this.activatedRoute.snapshot.paramMap.get('id');

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
      this.snackBar.open("Comentário salvo!", "" ,{duration: 3000})
      this.reloadComponent(true);
      this.commentFormGroup.reset();
    });
  }

  reloadComponent(self:boolean,urlToNavigateTo ?:string){
    const url=self ? this.router.url :urlToNavigateTo;
    this.router.navigateByUrl('/',{skipLocationChange:true}).then(()=>{
      this.router.navigate([`/${url}`]).then(()=>{
      })
    })
  }

}
