package pt.showtracker.core;

import pt.showtracker.tvDb.entity.EpisodeEntity;

import javax.persistence.*;

/**
 * Show domain class.
 */
@Entity
@Table(name = "episodes")
@NamedQueries({
        @NamedQuery(name = "findBySeriesId", query = "select e from Episode e where e.seriesId = :seriesId"),
        @NamedQuery(name = "find", query = "select e from Episode e where e.id = :id"),
        @NamedQuery(name = "findBySeason", query = "select e from Episode e where e.seasonId = :seasonId"),
        @NamedQuery(name = "findByShow", query = "select e from Episode e where e.seriesId = :showId")
})
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "season_number", nullable = false)
    private int seasonNumber;

    @Column(name = "episode_number", nullable = false)
    private int episodeNumber;

    @Column(name = "episode_name", nullable = true)
    private String episodeName;

    @Column(name = "overview", nullable = true)
    private String overview;

    @Column(name = "filename", nullable = true)
    private String filename;

    @Column(name = "season_id", nullable = false)
    private long seasonId;

    @Column(name = "series_id", nullable = false)
    private long seriesId;

    @Column(name = "seen", nullable = false, columnDefinition = "boolean default false")
    private boolean seen;

    public Episode() {
    }

    public Episode(EpisodeEntity episode) {
        this.seasonNumber = episode.getSeasonNumber();
        this.episodeNumber = episode.getEpisodeNumber();
        this.episodeName = episode.getEpisodeName();
        int maxLength = episode.getOverview().length() > 500 ? 500 : episode.getOverview().length();
        this.overview = episode.getOverview().substring(0, maxLength);
        this.filename = episode.getFilename();
        this.seasonId = episode.getSeasonId();
        this.seriesId = episode.getSeriesId();
        this.seen = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(long seasonId) {
        this.seasonId = seasonId;
    }

    public long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(long seriesId) {
        this.seriesId = seriesId;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

}
