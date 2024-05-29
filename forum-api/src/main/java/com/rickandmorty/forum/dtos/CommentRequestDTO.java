package com.rickandmorty.forum.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rickandmorty.forum.models.Episode;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CommentRequestDTO(
        Integer id,
        @NotBlank(message = "Nome de usuario não pode estar em branco") String userName,
        @NotBlank(message = "Comentário não pode estar em branco") String content,
        @NotBlank(message = "Data não pode estar em branco") String date,
        @NotBlank(message = "ID do episódio não pode estar em branco") String episodeId
) implements Serializable {

}
