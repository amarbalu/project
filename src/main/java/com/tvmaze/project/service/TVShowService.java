package com.tvmaze.project.service;

import com.tvmaze.project.model.TVShow;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TVShowService {
    private final List<TVShow> tvShows = new ArrayList<>(); // In-memory storage

    // Get all TV shows
    public List<TVShow> getAllTVShows() {
        return tvShows;
    }

    // Get a single TV show by ID
    public TVShow getTVShowById(String id) {
        return tvShows.stream()
                .filter(show -> show.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Add a new TV show
    public void addTVShow(TVShow tvShow) {
        tvShows.add(tvShow);
    }
}

