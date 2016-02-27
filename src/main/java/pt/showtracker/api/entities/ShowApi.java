package pt.showtracker.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.showtracker.core.Episode;
import pt.showtracker.core.Show;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ShowApi {
    private Long seriesId;
    private String language;
    private String seriesName;
    private String banner;
    private String overview;
    private String firstAired;
    private String network;
    private String imdbId;
    private String zap2itId;
    private List<EpisodeApi> episodes;

    static DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public ShowApi(long seriesId, String language, String seriesName, String banner, String overview, Date firstAired, String network, String imdbId, String zap2itId, List<Episode> episodes) {
        this.seriesId = seriesId;
        this.language = language;
        this.seriesName = seriesName;
        this.banner = banner;
        this.overview = overview;
        this.firstAired = firstAired == null ? null: df.format(firstAired);
        this.network = network;
        this.imdbId = imdbId;
        this.zap2itId = zap2itId;
        this.episodes = episodes == null ? Collections.EMPTY_LIST : episodes.stream().map((e) -> new EpisodeApi(e)).collect(Collectors.toList());
    }

    public ShowApi(Show showDB, List<Episode> episodes) {
        this.seriesId = showDB.getExternalId();
        this.language = showDB.getLanguage();
        this.seriesName = showDB.getSeriesName();
        this.banner = showDB.getBanner();
        this.overview = showDB.getOverview();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        this.firstAired = df.format(showDB.getFirstAired());
        this.network = showDB.getNetwork();
        this.imdbId = showDB.getImdbId();
        this.zap2itId = showDB.getZap2itId();
        this.episodes = episodes == null ? Collections.EMPTY_LIST : episodes.stream().map((e) -> new EpisodeApi(e)).collect(Collectors.toList());
    }

    public ShowApi() {
    }

    @JsonProperty
    public long getSeriesId() {
        return seriesId;
    }

    @JsonProperty
    public String getLanguage() {
        return language;
    }

    @JsonProperty
    public String getSeriesName() {
        return seriesName;
    }

    @JsonProperty
    public String getBanner() {
        return banner;
    }

    @JsonProperty
    public String getOverview() {
        return overview;
    }

    @JsonProperty
    public String getFirstAired() {
        return firstAired;
    }

    @JsonProperty
    public String getNetwork() {
        return network;
    }

    @JsonProperty
    public String getImdbId() {
        return imdbId;
    }

    @JsonProperty
    public String getZap2itId() {
        return zap2itId;
    }

    @JsonProperty
    public List<EpisodeApi> getEpisodes() {
        return episodes;
    }
}