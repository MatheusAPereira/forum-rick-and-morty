package com.rickandmorty.forum.controllers;


import com.rickandmorty.forum.exceptions.NotFoundException;
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
public class EpisodeController {

    @Autowired
    EpisodeService episodeService;

    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<PaginatedDTO<Episode>> getEpisodes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        PaginatedDTO<Episode> pag = episodeService.listEpisodes(page, size);
        if (pag.getItems().isEmpty()){
            throw new NotFoundException("Esta página de episódios não existe");
        }
        return ResponseEntity.ok(pag);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Episode>> getAllEpisodes(){
        return ResponseEntity.ok(episodeService.listAllEpisodes());
    }

    @GetMapping(path = "/{episodeId}")
    public ResponseEntity<Episode> getEpisode(@PathVariable Long episodeId){
        Episode episode = episodeService.getById(episodeId);
        if (episode == null){
            throw new NotFoundException("Este episódio não existe");
        }
        return ResponseEntity.ok(episode);
    }

    @GetMapping(path = "/{episodeId}/comments")
    public ResponseEntity<PaginatedDTO<Comment>> getCommentsByEpisode(
            @PathVariable Long episodeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        PaginatedDTO<Comment> pag = commentService.listByEpisodeId(episodeId, page, size);
        if (pag.getItems().isEmpty()){
            throw new NotFoundException("Esta página de comentários não existe");
        }
        return ResponseEntity.ok(pag);
    }

    @GetMapping(path = "/{episodeId}/comments/all")
    public ResponseEntity<List<Comment>> getAllCommentsByEpisode(@PathVariable Long episodeId){
        return ResponseEntity.ok(commentService.listAllByEpisodeId(episodeId));
    }
}
