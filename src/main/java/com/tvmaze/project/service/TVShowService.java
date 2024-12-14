package com.tvmaze.project.service;

import com.tvmaze.project.model.TVShow;
import com.tvmaze.project.repository.TVShowRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class TVShowService {

    private final TVShowRepository tvShowRepository;

    public TVShowService(TVShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    // Get all TV shows
    public List<TVShow> getAllTVShows() {
        return tvShowRepository.findAll();
    }

    // Get a single TV show by ID
    public TVShow getTVShowById(Long id) {
        return tvShowRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("TV Show not found with ID: " + id));
    }

    // Add or update a TV show
    public TVShow addOrUpdateTVShow(TVShow tvShow) {
     TVShow existingTVShow = tvShowRepository.findByName(tvShow.getName());

    if (existingTVShow != null) {
        // Update the existing entity
        existingTVShow.setSummary(tvShow.getSummary());
        existingTVShow.setStatus(tvShow.getStatus());
        existingTVShow.setNetwork(tvShow.getNetwork());
        return tvShowRepository.save(existingTVShow);
    } else {
        // Save as a new entity
        return tvShowRepository.save(tvShow);
    }
    }
}
