package pt.showtracker;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.db.PooledDataSourceFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Parse the Heroku DATABASE_URL environment variable to create a database configuration.
 *
 */
public class HerokuDatabaseConfiguration  implements DatabaseConfiguration {

    private static DatabaseConfiguration databaseConfiguration;

    public static DatabaseConfiguration create(String databaseUrl) throws URISyntaxException {
        if (databaseUrl == null) {
            return null;
        }
        DatabaseConfiguration databaseConfiguration = null;

        URI dbUri = new URI(databaseUrl);
        final String user = dbUri.getUserInfo().split(":")[0];
        final String password = dbUri.getUserInfo().split(":")[1];
        final String url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()
                + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        databaseConfiguration = new DatabaseConfiguration() {

            DataSourceFactory dataSourceFactory;

            @Override
            public DataSourceFactory getDataSourceFactory(io.dropwizard.Configuration configuration) {
                if (dataSourceFactory!= null) {
                    return dataSourceFactory;
                }
                DataSourceFactory dsf = new DataSourceFactory();
                dsf.setUser(user);
                dsf.setPassword(password);
                dsf.setUrl(url);
                dsf.setDriverClass("org.postgresql.Driver");
                dataSourceFactory = dsf;
                return dsf;
            }
        };

        return databaseConfiguration;
    }

    @Override
    public PooledDataSourceFactory getDataSourceFactory(Configuration configuration) {
        if (databaseConfiguration == null) {
            throw new IllegalStateException("You must first create a DatabaseConfiguration");
        }
        return databaseConfiguration.getDataSourceFactory(null);
    }
}
