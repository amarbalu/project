package com.tvmaze.project.model;

public class TVShow {
    private String id; // Unique identifier for the show
    private String name; // Name of the show
    private String summary; // Summary of the show
    private String network; // TV network (e.g., NBC)
    private String status; // Show status (e.g., Running, Ended)

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

