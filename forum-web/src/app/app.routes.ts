import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { EpisodeComponent } from './episode/episode.component';


export const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'episode/:id', component: EpisodeComponent}
];
