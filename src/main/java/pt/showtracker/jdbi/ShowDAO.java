package pt.showtracker.jdbi;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import pt.showtracker.core.Show;

import java.util.List;

/**
 *  Show data access object.
 */
public class ShowDAO extends AbstractDAO<Show> {

    public ShowDAO(SessionFactory factory) {
        super(factory);
    }

    public Show findById(Long id) {
        return get(id);
    }

    public Show findByImdbId(String imdbId) {
        return uniqueResult(namedQuery("findByImdb").setParameter("imdbId",imdbId).setMaxResults(1));
    }

    public long create(Show show) {
        return persist(show).getId();
    }

    public List<Show> findAll() {
        return list(namedQuery("findAll"));
    }

    public void delete(Show show) {
        currentSession().delete(show);
    }
}