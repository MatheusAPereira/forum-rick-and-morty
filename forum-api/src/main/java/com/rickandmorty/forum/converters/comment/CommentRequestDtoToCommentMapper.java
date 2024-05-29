package com.rickandmorty.forum.converters.comment;

import com.rickandmorty.forum.converters.Mapper;
import com.rickandmorty.forum.dtos.CommentRequestDTO;
import com.rickandmorty.forum.helpers.Util;
import com.rickandmorty.forum.models.Comment;
import com.rickandmorty.forum.models.Episode;
import com.rickandmorty.forum.services.EpisodeService;
import org.springframework.stereotype.Component;

@Component
public class CommentRequestDtoToCommentMapper implements Mapper<CommentRequestDTO, Comment> {

    @Override
    public Comment map(CommentRequestDTO commentRequestDTO, Comment comment) {

        Episode episode = new Episode();
        episode.setId(Long.parseLong(commentRequestDTO.episodeId()));

        return new Comment(
                null,
                commentRequestDTO.userName(),
                commentRequestDTO.content(),
                Util.stringToLocalDateTime(commentRequestDTO.date()),
                episode
        );
    }

}
