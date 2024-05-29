package com.rickandmorty.forum.converters.episode;

import com.rickandmorty.forum.converters.Mapper;
import com.rickandmorty.forum.dtos.ApiEpisode;
import com.rickandmorty.forum.models.Episode;

public class ApiEpisodeToEpisode implements Mapper<ApiEpisode, Episode> {
    @Override
    public Episode map(ApiEpisode apiEpisode, Episode episode) {
        episode.setId(Long.parseLong(String.valueOf(apiEpisode.getId())));
        episode.setName(apiEpisode.getName());
        episode.setCode(apiEpisode.getEpisode());

        return episode;
    }
}
