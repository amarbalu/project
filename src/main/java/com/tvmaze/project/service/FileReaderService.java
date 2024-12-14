package com.tvmaze.project.service;

import com.tvmaze.project.model.TVShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@Transactional
public class FileReaderService {

    @Autowired
    private TVShowService tvShowService;

    private final RestTemplate restTemplate = new RestTemplate();

    public void loadTVShowsFromFile() {
        try {
            InputStreamReader isr = new InputStreamReader(
                    getClass().getClassLoader().getResourceAsStream("static/tvtitles.txt"),
                    StandardCharsets.UTF_8
            );

            if (isr == null) {
                throw new RuntimeException("File not found: static/tvtitles.txt");
            }

            BufferedReader br = new BufferedReader(isr);
            String title;

            try (FileWriter failedTitlesWriter = new FileWriter("failed_titles.log", true)) {
                while ((title = br.readLine()) != null) {
                    String cleanTitle =  title.trim() // Remove leading and trailing spaces
    .replaceAll("[^a-zA-Z0-9\\s]", "") // Remove special characters
    .replaceAll("\\s+", " ");
                    String encodedTitle = URLEncoder.encode(cleanTitle, StandardCharsets.UTF_8);
                    String apiUrl = "http://api.tvmaze.com/singlesearch/shows?q=" + encodedTitle;

                    try {
                        // System.out.println("Fetching details for URL: " + apiUrl);
                        TVShow fetchedTVShow = restTemplate.getForObject(apiUrl, TVShow.class);

                        if (fetchedTVShow != null) {
                            // System.out.println("Saving TV Show: " + fetchedTVShow.getName());
                            tvShowService.addOrUpdateTVShow(fetchedTVShow);
                        } else {
                            System.out.println("No details found for: " + cleanTitle);
                            failedTitlesWriter.write(cleanTitle + "\n");
                        }
                    } catch (HttpClientErrorException.NotFound e) {
                        System.out.println("No details found for: " + encodedTitle + " (404 Not Found)");
                        failedTitlesWriter.write(cleanTitle + "\n");
                    } catch (Exception e) {
                        System.out.println("Failed to fetch details for: " + apiUrl + " (" + e.getMessage() + ")");
                        failedTitlesWriter.write(cleanTitle + " - Error: " + e.getMessage() + "\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file or fetching data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
