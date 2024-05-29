package com.rickandmorty.forum.services.Impl;

import com.rickandmorty.forum.converters.episode.ApiEpisodeToEpisode;
import com.rickandmorty.forum.dtos.ApiEpisode;
import com.rickandmorty.forum.dtos.EpisodeApiResponse;
import com.rickandmorty.forum.helpers.Constants;
import com.rickandmorty.forum.models.Episode;
import com.rickandmorty.forum.services.EpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalApiServiceImpl {

    private final EpisodeService episodeService;

    public ExternalApiServiceImpl(EpisodeService episodeService){
        this.episodeService = episodeService;
    }

    @Scheduled(initialDelay = 0)
    //@Scheduled(cron = "0 0 12 ? * 5")
    public void saveDataFromExternalApi() {
        RestTemplate restTemplate = new RestTemplate();
        String url = Constants.RICKANDMORTY_API_EPISODES;
        List<ApiEpisode> allApiEpisodes = new ArrayList<>();

        while (true) {
            ResponseEntity<EpisodeApiResponse> response = restTemplate.getForEntity(url, EpisodeApiResponse.class);
            EpisodeApiResponse episodeResponse = response.getBody();
            allApiEpisodes.addAll(episodeResponse.getResults());

            if (episodeResponse.getInfo().getNext() != null) {
                url = episodeResponse.getInfo().getNext();
            } else {
                break;
            }
        }

        ApiEpisodeToEpisode apiEpisodeToEpisode = new ApiEpisodeToEpisode();
        List<Episode> episodes = allApiEpisodes.stream()
                .map(apiEpisode -> apiEpisodeToEpisode.map(apiEpisode, new Episode())).toList();

        for (Episode episode : episodes) {
            episodeService.save(episode);
        }
    }

}
