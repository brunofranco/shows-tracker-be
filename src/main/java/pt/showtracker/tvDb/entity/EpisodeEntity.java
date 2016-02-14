package pt.showtracker.tvDb.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity for an episode.
 */
@XmlRootElement(name = "EpisodeName")
public class EpisodeEntity {
    private long id;
    private int seasonNumber;
    private int episodeNumber;
    private String episodeName;
    private String overview;
    private String filename;
    private long seasonId;
    private long seriesId;

    @XmlElement(name = "id")
    public void setId(long id) {
        this.id = id;
    }

    @XmlElement(name = "SeasonNumber")
    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    @XmlElement(name = "EpisodeNumber")
    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    @XmlElement(name = "EpisodeName")
    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    @XmlElement(name = "Overview")
    public void setOverview(String overview) {
        this.overview = overview;
    }

    @XmlElement(name = "filename")
    public void setFilename(String filename) {
        this.filename = filename;
    }

    @XmlElement(name = "seasonid")
    public void setSeasonId(long seasonId) {
        this.seasonId = seasonId;
    }

    @XmlElement(name = "seriesid")
    public void setSeriesId(long seriesId) {
        this.seriesId = seriesId;
    }

    public long getId() {
        return id;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getEpisodeNumber() {
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

    public long getSeasonId() {
        return seasonId;
    }

    public long getSeriesId() {
        return seriesId;
    }
}
