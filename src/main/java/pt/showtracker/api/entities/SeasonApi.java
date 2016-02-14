package pt.showtracker.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.showtracker.core.Episode;

import java.util.Optional;

public class SeasonApi {
    private Optional<Long> id;
    private Optional<Boolean> seen;

    public SeasonApi() {
    }

    public SeasonApi(Episode episodeDB) {
        this.id = Optional.ofNullable(episodeDB.getId());
        this.seen = Optional.ofNullable(episodeDB.isSeen());
    }

    public SeasonApi(Long id, Boolean seen) {
        this.id = Optional.ofNullable(id);
        this.seen = Optional.ofNullable(seen);
    }

    @JsonProperty
    public Long getId() {
        return id.orElse(0L);
    }

    public Boolean getSeen() {
        return seen.orElse(Boolean.FALSE);
    }

    public void setId(Long id) {
        this.id = Optional.ofNullable(id);
    }

    public void setSeen(Boolean seen) {
        this.seen = Optional.ofNullable(seen);
    }
}