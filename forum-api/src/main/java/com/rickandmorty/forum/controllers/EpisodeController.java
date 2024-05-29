package com.rickandmorty.forum.controllers;


import com.rickandmorty.forum.helpers.Constants;
import com.rickandmorty.forum.models.Comment;
import com.rickandmorty.forum.models.Episode;
import com.rickandmorty.forum.dtos.PaginatedDTO;
import com.rickandmorty.forum.services.CommentService;
import com.rickandmorty.forum.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = Constants.API_VERSION + "/episodes")
@CrossOrigin(origins = {"http://localhost:4200"})
public class EpisodeController {

    @Autowired
    EpisodeService episodeService;

    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<PaginatedDTO<Episode>> getEpisodes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(episodeService.listEpisodes(page, size));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Episode>> getAllEpisodes(){
        return ResponseEntity.ok(episodeService.listAllEpisodes());
    }

    @GetMapping(path = "/{episodeId}")
    public ResponseEntity<Episode> getEpisode(@PathVariable Long episodeId){
        return ResponseEntity.ok(episodeService.getById(episodeId));
    }

    @GetMapping(path = "/{episodeId}/comments")
    public ResponseEntity<PaginatedDTO<Comment>> getCommentsByEpisode(
            @PathVariable Long episodeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(commentService.listByEpisodeId(episodeId, page, size));
    }

    @GetMapping(path = "/{episodeId}/comments/all")
    public ResponseEntity<List<Comment>> getAllCommentsByEpisode(@PathVariable Long episodeId){
        return ResponseEntity.ok(commentService.listAllByEpisodeId(episodeId));
    }
}
