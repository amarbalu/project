package com.tvmaze.project.service;

import com.tvmaze.project.model.TVShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class FileReaderService {

    @Autowired
    private TVShowService tvShowService;

    public void loadTVShowsFromFile() {
        try {
            // Load the file from resources/static
            ClassPathResource resource = new ClassPathResource("static/tvtitles.txt");

            // Read the file using BufferedReader
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
                String title;
                while ((title = reader.readLine()) != null) {
                    // Create a dummy TVShow object (can replace with API calls)
                    TVShow tvShow = new TVShow();
                    tvShow.setId(String.valueOf(title.hashCode())); // Generate a unique ID
                    tvShow.setName(title);
                    tvShow.setSummary("Summary for " + title); // Dummy summary
                    tvShow.setNetwork("Dummy Network");
                    tvShow.setStatus("Running");

                    // Add TV show to the in-memory list
                    tvShowService.addTVShow(tvShow);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
