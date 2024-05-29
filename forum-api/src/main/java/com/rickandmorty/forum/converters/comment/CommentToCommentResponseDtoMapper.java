package com.rickandmorty.forum.converters.comment;

import com.rickandmorty.forum.converters.Mapper;
import com.rickandmorty.forum.dtos.CommentResponseDTO;
import com.rickandmorty.forum.helpers.Util;
import com.rickandmorty.forum.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentResponseDtoMapper implements Mapper<Comment, CommentResponseDTO> {

    @Override
    public CommentResponseDTO map(Comment comment, CommentResponseDTO commentResponseDTOResponseDTO) {

        return new CommentResponseDTO(
                String.valueOf(comment.getId()),
                comment.getUserName(),
                comment.getContent(),
                Util.localDateTimeToString(comment.getDate())
        );
    }
}
