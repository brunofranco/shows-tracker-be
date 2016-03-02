package pt.showtracker;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URISyntaxException;


public class ShowTrackerConfiguration extends Configuration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowTrackerConfiguration.class);

    /**
     * Jersey client default configuration.
     */
    @Valid
    @NotNull
    @JsonProperty("jerseyClient")
    private JerseyClientConfiguration jerseyClientConfiguration = new JerseyClientConfiguration();

    /**
     *
     * @return Jersey Client
     */
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClientConfiguration;
    }

    /**
     * Database default configuration.
     */
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    /**
     * This gets called with the values from the Dropwizard example.xmp, but we want to override it with the values
     * from the Heroku DATABASE_URL environment variable.
     */
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        DatabaseConfiguration databaseConfiguration = null;
        try {
            // try heroku config
            databaseConfiguration = HerokuDatabaseConfiguration.create(System.getenv("DATABASE_URL"));
            if(databaseConfiguration != null) {
                LOGGER.info("Use Heroku database configuration");
                database = (DataSourceFactory) databaseConfiguration.getDataSourceFactory(null);
            } else {
                // fallback to yml
                LOGGER.info("Fallback to yml databse configuration " + database);
            }
        } catch (URISyntaxException e) {
            LOGGER.info("Error when processing the database url");
            database = null;
        }

        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        database = dataSourceFactory;
    }

    /**
     * The key to access TVDB API.
     */
    @NotEmpty
    private String apiKey;


    /**
     * A getter for the API key of TVDB.
     *
     * @return the API key of TVDB.
     */
    @JsonProperty
    public String getApiKey() {
        return apiKey;
    }

}