package pt.showtracker.core;

import pt.showtracker.tvDb.entity.SeriesEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Show domain class.
 */
@Entity
@Table(name = "shows")
@NamedQueries({
        @NamedQuery(name = "findByImdb", query = "select e from Show e where e.imdbId = :imdbId"),
        @NamedQuery(name = "findAll", query = "select e from Show e")
})
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "external_id", nullable = false)
    private long externalId;
    
    @Column(name = "language", nullable = true)
    private String language;

    @Column(name = "series_name", nullable = false)
    private String seriesName;

    @Column(name = "banner", nullable = true)
    private String banner;

    @Column(name = "overview", nullable = true)
    private String overview;

    @Column(name = "first_arired", nullable = true)
    private Date firstAired;

    @Column(name = "network", nullable = true)
    private String network;

    @Column(name = "imdb_id", nullable = true)
    private String imdbId;

    @Column(name = "zap2it_id", nullable = true)
    private String zap2itId;

    public Show() {
    }

    public Show(SeriesEntity series) {
        this.externalId = series.getSeriesid();
        this.language = series.getLanguage();
        this.seriesName = series.getSeriesName();
        this.banner = "http://thetvdb.com/banners/"+series.getBanner();
        this.overview = series.getOverview();
        this.firstAired = series.getFirstAired();
        this.network = series.getNetwork();
        this.imdbId = series.getImdbId();
        this.zap2itId = series.getZap2itId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(Date firstAired) {
        this.firstAired = firstAired;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getZap2itId() {
        return zap2itId;
    }

    public void setZap2itId(String zap2itId) {
        this.zap2itId = zap2itId;
    }

    public long getExternalId() {
        return externalId;
    }

    public void setExternalId(long externalId) {
        this.externalId = externalId;
    }
}
