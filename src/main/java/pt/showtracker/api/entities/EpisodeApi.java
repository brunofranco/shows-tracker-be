package pt.showtracker.api.entities;

import pt.showtracker.core.Episode;

public class EpisodeApi {
    private Long id;
    private Long externalId;
    private Integer seasonNumber;
    private Integer episodeNumber;
    private String episodeName;
    private String overview;
    private String filename;
    private Long seasonId;
    private Long seriesId;
    private Boolean seen;

    public EpisodeApi() {
    }

    public EpisodeApi(Episode episodeDB) {
        this.id = episodeDB.getId();
        this.externalId = episodeDB.getExternalId();
        this.seasonNumber = episodeDB.getSeasonNumber();
        this.episodeNumber = episodeDB.getEpisodeNumber();
        this.episodeName = episodeDB.getEpisodeName();
        this.overview = episodeDB.getOverview();
        this.filename = episodeDB.getFilename();
        this.seasonId = episodeDB.getSeasonId();
        this.seriesId = episodeDB.getSeriesId();
        this.seen = episodeDB.isSeen();
    }

    public EpisodeApi(Long id, Long externalId, Integer seasonNumber, Integer episodeNumber, String episodeName, String overview, String filename, Long seasonId, Long seriesId, Boolean seen) {
        this.id = id;
        this.externalId = externalId;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.episodeName = episodeName;
        this.overview = overview;
        this.filename = filename;
        this.seasonId = seasonId;
        this.seriesId = seriesId;
        this.seen = seen;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public Long getId() {
        return id;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public String getOverview() {
        return overview;
    }

    public String getFilename() {
        return filename;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public Long getSeriesId() {
        return seriesId;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }
}