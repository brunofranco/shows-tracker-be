package pt.showtracker.tvDb.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Entity resulting form the search for a show.
 */
@XmlRootElement(name = "Series")
public class SeriesEntity {
    private int seriesid;
    private String language;
    private String seriesName;
    private String banner;
    private String overview;
    private Date firstAired;
    private String network;
    private String imdbId;
    private String zap2itId;

    public int getSeriesid() {
        return seriesid;
    }

    @XmlElement(name = "seriesid")
    public void setSeriesid(int seriesid) {
        this.seriesid = seriesid;
    }

    public String getLanguage() {
        return language;
    }

    @XmlElement(name = "language")
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSeriesName() {
        return seriesName;
    }

    @XmlElement(name = "SeriesName")
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getOverview() {
        return overview;
    }

    @XmlElement(name = "Overview")
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBanner() {
        return banner;
    }

    @XmlElement(name = "banner")
    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Date getFirstAired() {
        return firstAired;
    }

    @XmlElement(name = "FirstAired")
    public void setFirstAired(Date firstAired) {
        this.firstAired = firstAired;
    }

    public String getNetwork() {
        return network;
    }

    @XmlElement(name = "Network")
    public void setNetwork(String network) {
        this.network = network;
    }

    public String getImdbId() {
        return imdbId;
    }

    @XmlElement(name = "IMDB_ID")
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getZap2itId() {
        return zap2itId;
    }

    @XmlElement(name = "zap2it_id")
    public void setZap2itId(String zap2itId) {
        this.zap2itId = zap2itId;
    }
}
