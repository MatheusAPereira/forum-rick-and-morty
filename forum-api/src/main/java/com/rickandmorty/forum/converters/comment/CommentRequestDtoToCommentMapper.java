package com.rickandmorty.forum.converters.comment;

import com.rickandmorty.forum.converters.Mapper;
import com.rickandmorty.forum.dtos.CommentRequestDTO;
import com.rickandmorty.forum.exceptions.NotFoundException;
import com.rickandmorty.forum.helpers.Util;
import com.rickandmorty.forum.models.Comment;
import com.rickandmorty.forum.models.Episode;
import com.rickandmorty.forum.services.EpisodeService;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentRequestDtoToCommentMapper implements Mapper<CommentRequestDTO, Comment> {

    final private EpisodeService episodeService;

    public CommentRequestDtoToCommentMapper(EpisodeService episodeService){
        this.episodeService = episodeService;
    }

    @Override
    public Comment map(CommentRequestDTO commentRequestDTO, Comment comment) {

        Episode episode = new Episode();
        long episodeId;

        try{
            episodeId = Long.parseLong(commentRequestDTO.episodeId());
        } catch (Throwable e){
            throw new ValidationException("Formato do id do episódio é inválido");
        }

        episode = episodeService.getById(episodeId);

        if (episode == null){
            throw new NotFoundException("Episódio não encontrado");
        }
        

        LocalDateTime commentDateTime;

        try{
            commentDateTime = Util.stringToLocalDateTime(commentRequestDTO.date());
        } catch (Throwable e){
            throw new ValidationException("Data em formato inválido");
        }

        return new Comment(
                null,
                commentRequestDTO.userName(),
                commentRequestDTO.content(),
                commentDateTime,
                episode
        );
    }

}
