package com.tvmaze.project.controller;

import com.tvmaze.project.model.TVShow;
import com.tvmaze.project.service.TVShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST API
@RequestMapping("/tvshows") // Base URL for this API
public class TVShowController {

    @Autowired // Injects the service into this controller
    private TVShowService tvShowService;

    // Endpoint: GET /tvshows
    @GetMapping
    public List<TVShow> getAllTVShows() {
        return tvShowService.getAllTVShows();
    }

    // Endpoint: GET /tvshows/{id}
    @GetMapping("/{id}")
    public TVShow getTVShowById(@PathVariable String id) {
        return tvShowService.getTVShowById(id);
    }

    // Endpoint: POST /tvshows
    @PostMapping
    public void addTVShow(@RequestBody TVShow tvShow) {
        tvShowService.addTVShow(tvShow);
    }
}

