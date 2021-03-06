package pt.showtracker.jdbi;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import pt.showtracker.core.Episode;
import pt.showtracker.core.Show;

import java.util.List;

/**
 *  Show data access object.
 */
public class EpisodeDAO extends AbstractDAO<Episode> {
    public EpisodeDAO(SessionFactory factory) {
        super(factory);
    }

    public Episode create(Episode episode) {
        return persist(episode);
    }

    public List<Episode> bySeriesId(long externalId) {
        return list(namedQuery("findBySeriesId").setParameter("seriesId",externalId));
    }

    public Episode find(long id) {
        return uniqueResult(namedQuery("find").setParameter("id", id));
    }

    public Episode findByExternalId(long id) {
        return uniqueResult(namedQuery("findByExternalId").setParameter("externalId", id));
    }

    public boolean existsByFilename(String filename) {
        return !list(namedQuery("findByFilename").setParameter("filename", filename)).isEmpty();
    }

    public Episode findByFilename(String filename) {
        return uniqueResult(namedQuery("findByFilename").setParameter("filename", filename));
    }

    public boolean exists(long id) {
        return !list(namedQuery("find").setParameter("id", id)).isEmpty();
    }

    public Episode update(Episode episode) {
        return persist(episode);
    }

    public List<Episode> findBySeason(Integer seasonId) {
        return list(namedQuery("findBySeason").setParameter("seasonId",seasonId));
    }

    public Boolean seasonSeen(Long seasonId, Boolean seen) {
        Query query = this.currentSession().createQuery("update Episode e set e.seen = "+seen+" where e.seasonId = "+seasonId);
        return query.executeUpdate() > 0;
    }

    public List<Episode> getEpisodes(Show show) {
        return findByShow(show.getExternalId());
    }

    private List<Episode> findByShow(long externalId) {
        return list(namedQuery("findByShow").setParameter("showId", externalId));
    }

    public void delete(Episode episode) {
        currentSession().delete(episode);
    }
}