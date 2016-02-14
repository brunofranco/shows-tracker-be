package pt.showtracker.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.showtracker.core.Episode;

import java.util.Optional;

public class EpisodeApi {
    private Optional<Long> id;
    private Optional<Integer> seasonNumber;
    private Optional<Integer> episodeNumber;
    private Optional<String> episodeName;
    private Optional<String> overview;
    private Optional<String> filename;
    private Optional<Long> seasonId;
    private Optional<Long> seriesId;
    private Optional<Boolean> seen;

    public EpisodeApi() {
    }

    public EpisodeApi(Episode episodeDB) {
        this.id = Optional.ofNullable(episodeDB.getId());
        this.seasonNumber = Optional.ofNullable(episodeDB.getSeasonNumber());
        this.episodeNumber = Optional.ofNullable(episodeDB.getEpisodeNumber());
        this.episodeName = Optional.ofNullable(episodeDB.getEpisodeName());
        this.overview = Optional.ofNullable(episodeDB.getOverview());
        this.filename = Optional.ofNullable(episodeDB.getFilename());
        this.seasonId = Optional.ofNullable(episodeDB.getSeasonId());
        this.seriesId = Optional.ofNullable(episodeDB.getSeriesId());
        this.seen = Optional.ofNullable(episodeDB.isSeen());
    }

    public EpisodeApi(Long id, Integer seasonNumber, Integer episodeNumber, String episodeName, String overview, String filename, Long seasonId, Long seriesId, Boolean seen) {
        this.id = Optional.ofNullable(id);
        this.seasonNumber = Optional.ofNullable(seasonNumber);
        this.episodeNumber = Optional.ofNullable(episodeNumber);
        this.episodeName = Optional.ofNullable(episodeName);
        this.overview = Optional.ofNullable(overview);
        this.filename = Optional.ofNullable(filename);
        this.seasonId = Optional.ofNullable(seasonId);
        this.seriesId = Optional.ofNullable(seriesId);
        this.seen = Optional.ofNullable(seen);
    }

    @JsonProperty
    public Long getId() {
        return id.orElse(0L);
    }

    public Integer getSeasonNumber() {
        return seasonNumber.orElse(0);
    }

    public Integer getEpisodeNumber() {
        return episodeNumber.orElse(0);
    }

    public String getEpisodeName() {
        return episodeName.orElse("");
    }

    public String getOverview() {
        return overview.orElse("");
    }

    public String getFilename() {
        return filename.orElse("");
    }

    public Long getSeasonId() {
        return seasonId.orElse(0L);
    }

    public Long getSeriesId() {
        return seriesId.orElse(0L);
    }

    public Boolean getSeen() {
        return seen.orElse(Boolean.FALSE);
    }

    public void setId(Long id) {
        this.id = Optional.ofNullable(id);
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = Optional.ofNullable(seasonNumber);
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = Optional.ofNullable(episodeNumber);
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = Optional.ofNullable(episodeName);
    }

    public void setOverview(String overview) {
        this.overview = Optional.ofNullable(overview);
    }

    public void setFilename(String filename) {
        this.filename = Optional.ofNullable(filename);
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = Optional.ofNullable(seasonId);
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = Optional.ofNullable(seriesId);
    }

    public void setSeen(Boolean seen) {
        this.seen = Optional.ofNullable(seen);
    }
}