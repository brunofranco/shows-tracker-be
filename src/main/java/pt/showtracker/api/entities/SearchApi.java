package pt.showtracker.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class SearchApi {
    private Optional<String> imdbId;
    private Optional<String> name;

    public SearchApi(String imdbId, String name) {
        this.imdbId = Optional.ofNullable(imdbId);
        this.name = Optional.ofNullable(name);
    }

    @JsonProperty
    public String getImdbId() {
        return imdbId.orElse("");
    }

    @JsonProperty
    public String getName() {
        return name.orElse("");
    }
}