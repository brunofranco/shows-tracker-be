package pt.showtracker.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.showtracker.core.Episode;

public class SeasonApi {
    private Long id;
    private Boolean seen;

    public SeasonApi() {
    }

    public SeasonApi(Episode episodeDB) {
        this.id = episodeDB.getId();
        this.seen = episodeDB.isSeen();
    }

    public SeasonApi(Long id, Boolean seen) {
        this.id = id;
        this.seen = seen;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }
}