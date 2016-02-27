package pt.showtracker;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import pt.showtracker.core.Episode;
import pt.showtracker.core.Show;
import pt.showtracker.jdbi.EpisodeDAO;
import pt.showtracker.jdbi.ShowDAO;
import pt.showtracker.resources.EpisodeResource;
import pt.showtracker.resources.ShowResource;
import pt.showtracker.task.DownloadEpisodesTask;
import pt.showtracker.task.UpdateEpisodesTask;
import pt.showtracker.tvDb.TvDbApi;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.client.Client;
import java.util.EnumSet;

public class ShowTrackerApplication extends Application<ShowTrackerConfiguration> {
    public static void main(String[] args) throws Exception {
        new ShowTrackerApplication().run(args);
    }

    @Override
    public String getName() {
        return "Show Tracker";
    }

    // configure DAOs
    private final HibernateBundle<ShowTrackerConfiguration> hibernate = new HibernateBundle<ShowTrackerConfiguration>(Show.class, Episode.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ShowTrackerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<ShowTrackerConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);

        bootstrap.addBundle(new MigrationsBundle<ShowTrackerConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ShowTrackerConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(ShowTrackerConfiguration configuration, Environment environment) {

        setCORS(environment);

        //Create Jersey client
        final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration()).build(getName());
        TvDbApi.create(client, configuration.getApiKey());
        environment.jersey().register(TvDbApi.getInstance());

        // resources
        final EpisodeDAO episodeDAO = new EpisodeDAO(hibernate.getSessionFactory());
        final ShowDAO showDAO = new ShowDAO(hibernate.getSessionFactory());
        environment.jersey().register(new ShowResource(showDAO, episodeDAO));
        environment.jersey().register(new EpisodeResource(episodeDAO));

        // tasks
        environment.admin().addTask(DownloadEpisodesTask.create(episodeDAO, hibernate.getSessionFactory()));
        environment.admin().addTask(UpdateEpisodesTask.create(episodeDAO, hibernate.getSessionFactory()));
    }

    private void setCORS(Environment environment) {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

}
