package com.rickandmorty.forum.dtos;


public class EpisodeResponseDTO {

    String id;
    String name;
    String code;

    public EpisodeResponseDTO(String id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
}
