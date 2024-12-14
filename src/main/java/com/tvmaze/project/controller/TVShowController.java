package com.tvmaze.project.controller;

import com.tvmaze.project.model.TVShow;
import com.tvmaze.project.service.TVShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tvshows")
public class TVShowController {

    @Autowired
    private TVShowService tvShowService;

    // Get all TV shows
    @GetMapping
    public List<TVShow> getAllTVShows() {
        return tvShowService.getAllTVShows();
    }

    // Get a single TV show by ID
    @GetMapping("/{id}")
    public TVShow getTVShowById(@PathVariable Long id) {
        return tvShowService.getTVShowById(id);
    }

    // Add or update a TV show
    @PostMapping
    public void addOrUpdateTVShow(@RequestBody TVShow tvShow) {
        tvShowService.addOrUpdateTVShow(tvShow);
    }
}
