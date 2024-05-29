package com.rickandmorty.forum.services.Impl;

import com.rickandmorty.forum.models.Episode;
import com.rickandmorty.forum.dtos.PaginatedInfoDTO;
import com.rickandmorty.forum.dtos.PaginatedDTO;
import com.rickandmorty.forum.respositories.EpisodeRepository;
import com.rickandmorty.forum.services.EpisodeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EpisodeServiceImpl implements EpisodeService {

    private final EpisodeRepository episodeRepository;

    public EpisodeServiceImpl(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }


    @Override
    public Episode getById(Long episodeId) {
        Optional<Episode> episodeOptional = episodeRepository.findById(episodeId);
        return episodeOptional.isPresent() ? episodeOptional.get() : null;
    }

    @Override
    public PaginatedDTO<Episode> listEpisodes(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Episode> episodes = episodeRepository.findAll(pageable);
        return new PaginatedDTO<>(
                new PaginatedInfoDTO(
                        episodes.getTotalPages(),
                        episodes.getTotalElements(),
                        episodes.getNumber(),
                        episodes.getSize(),
                        episodes.hasPrevious(),
                        episodes.hasNext()
                ),
                episodes.getContent()
        );
    }

    @Override
    public List<Episode> listAllEpisodes() {
        return episodeRepository.findAll();
    }

    @Override
    public void save(Episode episode) {
        episodeRepository.save(episode);
    }
}
