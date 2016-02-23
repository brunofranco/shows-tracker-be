package pt.showtracker.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import pt.showtracker.api.entities.SearchApi;
import pt.showtracker.api.entities.SeasonApi;
import pt.showtracker.api.entities.ShowApi;
import pt.showtracker.core.Episode;
import pt.showtracker.core.Show;
import pt.showtracker.jdbi.EpisodeDAO;
import pt.showtracker.jdbi.ShowDAO;
import pt.showtracker.task.DownloadEpisodesTask;
import pt.showtracker.tvDb.TvDbApi;
import pt.showtracker.tvDb.entity.SearchEntity;
import pt.showtracker.tvDb.entity.ShowEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Path("/api/shows")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShowResource {
    private final AtomicLong counter;
    private final ShowDAO showDAO;
    private final EpisodeDAO episodeDAO;

    public ShowResource(ShowDAO dao, EpisodeDAO epiDao) {
        this.counter = new AtomicLong();
        this.showDAO = dao;
        this.episodeDAO = epiDao;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<ShowApi> shows() {

        return this.showDAO
                .findAll()
                .stream()
                .map((showDB) -> new ShowApi(showDB, null))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{imdbId}")
    @Timed
    @UnitOfWork
    public ShowApi show(@PathParam("imdbId") String imdbId) {


        List entities = null;
        try {
            entities = getOrAddShow(imdbId);
            Show showDB = (Show) entities.get(0);
            List<Episode> episodesDB = (List<Episode>) entities.get(1);

            return new ShowApi(showDB, episodesDB);
        } catch (Exception e) {
            e.printStackTrace();
            return new ShowApi();
        }

    }

    @POST
    @Path("/{imdbId}/updateSeason")
    @Timed
    @UnitOfWork
    public Boolean seasonUpdate(@PathParam("imdbId") String imdbId, SeasonApi season) {
        return this.episodeDAO.seasonSeen(season.getId(), season.getSeen());
    }

    @DELETE
    @Path("/{imdbId}")
    @Timed
    @UnitOfWork
    public boolean delete(@PathParam("imdbId") String imdbId) {
        Show show = this.showDAO.findByImdbId(imdbId);

        for(Episode e : this.episodeDAO.getEpisodes(show)) {
            this.episodeDAO.delete(e);
        }
        this.showDAO.delete(show);

        return true;
    }

    @GET
    @Path("/search")
    @Timed
    public List<SearchApi> search(@QueryParam("showName") com.google.common.base.Optional<String> showName) {
        SearchEntity search = TvDbApi.getInstance().getSeries(showName.or(""));

        if (search.getData().isEmpty())
            return Collections.emptyList();

        return search.getData()
                .stream()
                .map((show) -> new SearchApi(show.getImdbId(), show.getSeriesName()))
                .collect(Collectors.toList());
    }

    private List getOrAddShow(String show) throws IOException, JAXBException {
        Show showDB = this.showDAO.findByImdbId(show);
        if(showDB == null) {
            ShowEntity series = TvDbApi.getInstance().getShow(show);
            if(series.getData() != null) {
                showDB = new Show(series.getData());
                this.showDAO.create(showDB);
            } else {
                showDB = new Show();
            }
        }

        List<Episode> episodesDB = this.episodeDAO.bySeriesId(showDB.getExternalId());
        if(episodesDB.isEmpty()) {
//            TaskDispatcher.getInstance().scheduleTask("downloadEpisodes", showDB.getExternalId());
            episodesDB = DownloadEpisodesTask.getInstance().execute(showDB.getExternalId());
        }

        return Arrays.asList(showDB, episodesDB);
    }
}