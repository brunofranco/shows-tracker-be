package pt.showtracker.tvDb;

import pt.showtracker.error.ShowNotFoundException;
import pt.showtracker.tvDb.entity.SearchEntity;
import pt.showtracker.tvDb.entity.ShowEntity;

import javax.ws.rs.client.Client;
import java.util.Optional;

public class TvDbApi {

    private final String GET_SERIES_URL = "http://thetvdb.com/api/GetSeries.php";
    private final String GET_SERIES_BY_ID_URL = "http://thetvdb.com/api/GetSeriesByRemoteID.php";
    private String GET_EPISODES = "http://thetvdb.com/api/API_KEY/series/SERIES_ID/all/en.zip";

    public String getGET_EPISODES() {
        return GET_EPISODES;
    }

    static Optional<TvDbApi> instance = Optional.empty();

    //Jersey client
    private Client client;
    // TVDB apiKey
    private String apiKey;

    private TvDbApi() {
    }

    private TvDbApi(Client c, String apiKey) {
        this.client = c;
        this.apiKey = apiKey;
        GET_EPISODES = GET_EPISODES.replace("API_KEY", apiKey);
    }

    public static void create(Client client, String apiKey) {
        if (!instance.isPresent()) {
            instance = Optional.of(new TvDbApi(client, apiKey));
        }
    }

    public static TvDbApi getInstance() {
        return instance.orElse(new TvDbApi());
    }

    public SearchEntity getSeries(String name) {
        try {
            //Obtain data from external API.
            SearchEntity result = client
                    .target(GET_SERIES_URL)
                    .queryParam("seriesname", name)
                    .request()
                    .get(SearchEntity.class);
            return result;
        } catch (Exception e) {
            throw new ShowNotFoundException();
        }
    }

    public ShowEntity getShow(String imdbId) {
        try {
            //Obtain data from external API.
            ShowEntity result = client
                    .target(GET_SERIES_BY_ID_URL)
                    .queryParam("imdbid", imdbId)
                    .request()
                    .get(ShowEntity.class);
            return result;
        } catch (Exception e) {
            throw new ShowNotFoundException();
        }
    }
}