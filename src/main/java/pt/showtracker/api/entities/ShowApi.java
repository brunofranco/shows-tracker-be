package pt.showtracker.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.showtracker.core.Episode;
import pt.showtracker.core.Show;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ShowApi {
    private Optional<Long> seriesId;
    private Optional<String> language;
    private Optional<String> seriesName;
    private Optional<String> banner;
    private Optional<String> overview;
    private Optional<String> firstAired;
    private Optional<String> network;
    private Optional<String> imdbId;
    private Optional<String> zap2itId;
    private List<Episode> episodes;

    static DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public ShowApi(long seriesId, String language, String seriesName, String banner, String overview, Date firstAired, String network, String imdbId, String zap2itId, List<Episode> episodes) {
        this.seriesId = Optional.ofNullable(seriesId);
        this.language = Optional.ofNullable(language);
        this.seriesName = Optional.ofNullable(seriesName);
        this.banner = Optional.ofNullable(banner);
        this.overview = Optional.ofNullable(overview);
        this.firstAired = Optional.ofNullable(firstAired == null ? null: df.format(firstAired));
        this.network = Optional.ofNullable(network);
        this.imdbId = Optional.ofNullable(imdbId);
        this.zap2itId = Optional.ofNullable(zap2itId);
        this.episodes = episodes;
    }

    public ShowApi(Show showDB, List<Episode> episodesDB) {
        this.seriesId = Optional.ofNullable(showDB.getExternalId());
        this.language = Optional.ofNullable(showDB.getLanguage());
        this.seriesName = Optional.ofNullable(showDB.getSeriesName());
        this.banner = Optional.ofNullable(showDB.getBanner());
        this.overview = Optional.ofNullable(showDB.getOverview());
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        this.firstAired = Optional.ofNullable(df.format(showDB.getFirstAired()));
        this.network = Optional.ofNullable(showDB.getNetwork());
        this.imdbId = Optional.ofNullable(showDB.getImdbId());
        this.zap2itId = Optional.ofNullable(showDB.getZap2itId());
        this.episodes = episodesDB;
    }

    @JsonProperty
    public long getSeriesId() {
        return seriesId.orElse(0L);
    }

    @JsonProperty
    public String getLanguage() {
        return language.orElse("");
    }

    @JsonProperty
    public String getSeriesName() {
        return seriesName.orElse("");
    }

    @JsonProperty
    public String getBanner() {
        return banner.orElse("");
    }

    @JsonProperty
    public String getOverview() {
        return overview.orElse("");
    }

    @JsonProperty
    public String getFirstAired() {
        return firstAired.orElse("");
    }

    @JsonProperty
    public String getNetwork() {
        return network.orElse("");
    }

    @JsonProperty
    public String getImdbId() {
        return imdbId.orElse("");
    }

    @JsonProperty
    public String getZap2itId() {
        return zap2itId.orElse("");
    }

    @JsonProperty
    public List<Episode> getEpisodes() {
        return episodes;
    }
}