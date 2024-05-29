package com.rickandmorty.forum.dtos;

import java.util.List;

public class EpisodeApiResponse {
    private Info info;
    private List<ApiEpisode> results;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<ApiEpisode> getResults() {
        return results;
    }

    public void setResults(List<ApiEpisode> results) {
        this.results = results;
    }
}