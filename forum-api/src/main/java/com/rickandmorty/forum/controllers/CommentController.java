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
public class CommentController {

    @Autowired
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody @Valid CommentRequestDTO commentRequestDTO){
        commentService.create(commentRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
