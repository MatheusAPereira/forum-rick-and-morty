package com.rickandmorty.forum.controllers;

import com.rickandmorty.forum.dtos.CommentRequestDTO;
import com.rickandmorty.forum.helpers.Constants;
import com.rickandmorty.forum.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = Constants.API_VERSION + "/comments")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

    @Autowired
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody @Valid CommentRequestDTO commentRequestDTO){
        System.out.println(commentRequestDTO.userName());
        System.out.println(commentRequestDTO.date());
        System.out.println(commentRequestDTO.content());
        System.out.println(commentRequestDTO.episodeId());
        commentService.create(commentRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
