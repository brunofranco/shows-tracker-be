package pt.showtracker.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchApi {
    private String imdbId;
    private String name;

    public SearchApi(String imdbId, String name) {
        this.imdbId = imdbId;
        this.name = name;
    }

    @JsonProperty
    public String getImdbId() {
        return imdbId;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
}