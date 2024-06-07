package com.rickandmorty.forum.services;

import com.rickandmorty.forum.dtos.CommentRequestDTO;
import com.rickandmorty.forum.models.Comment;
import com.rickandmorty.forum.dtos.PaginatedDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommentService {

    void create(CommentRequestDTO commentRequestDTO);
    PaginatedDTO<Comment> listByEpisodeId(Long episodeId, int pageNumber, int pageSize);
    List<Comment> listAllByEpisodeId(Long episodeId);
    Comment findById(Long commentId);
    CommentRequestDTO update(CommentRequestDTO commentRequestDTO);
    void delete(CommentRequestDTO commentRequestDTO);
}
