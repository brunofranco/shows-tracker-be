package pt.showtracker.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import pt.showtracker.api.entities.EpisodeApi;
import pt.showtracker.core.Episode;
import pt.showtracker.error.UpdateException;
import pt.showtracker.jdbi.EpisodeDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/api/episodes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EpisodeResource {
    private final AtomicLong counter;
    private final EpisodeDAO episodeDAO;

    public EpisodeResource(EpisodeDAO epiDao) {
        this.counter = new AtomicLong();
        this.episodeDAO = epiDao;
    }

    @POST
    @Path("/{imdbId}/updateEpisode")
    @Timed
    @UnitOfWork
    public EpisodeApi updateEpisode(@PathParam("imdbId") String imdbId, EpisodeApi episode) {

        Episode episodeDB = this.episodeDAO.find(episode.getId());
        if(episodeDB != null) {
            episodeDB.setSeen(true);
            this.episodeDAO.update(episodeDB);
            return new EpisodeApi(episodeDB);
        }

        throw new UpdateException();
    }

}