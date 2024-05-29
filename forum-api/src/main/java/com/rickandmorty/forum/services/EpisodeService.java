package com.rickandmorty.forum.services;

import com.rickandmorty.forum.models.Episode;
import com.rickandmorty.forum.dtos.PaginatedDTO;

import java.util.List;

public interface EpisodeService {

    Episode getById(Long episodeId);
    PaginatedDTO<Episode> listEpisodes(int pageNumber, int pageSize);
    List<Episode> listAllEpisodes();
    void save(Episode episode);
}
