package pt.showtracker;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class ShowTrackerConfiguration extends Configuration {

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
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    /**
     *
     * @return Database
     */
    public DataSourceFactory getDataSourceFactory() {
        return database;
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

    /**
     * The host.
     */
    @NotEmpty
    private String host;


    /**
     * A getter for the host.
     *
     * @return the hostname.
     */
    @JsonProperty
    public String getHost() {
        return host;
    }

    /**
     * The port.
     */
    @NotNull
    private String port;


    /**
     * A getter for the port.
     *
     * @return the port.
     */
    @JsonProperty
    public String getPort() {
        return port;
    }
}