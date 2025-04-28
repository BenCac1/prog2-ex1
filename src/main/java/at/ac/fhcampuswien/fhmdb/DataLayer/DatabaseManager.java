package at.ac.fhcampuswien.fhmdb.DataLayer;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:h2:file:./data/testdb";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    private ConnectionSource connectionSource;

    public void createConnectionsSource() throws Exception {
        connectionSource = new JdbcConnectionSource(DATABASE_URL, USERNAME, PASSWORD);
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, MovieEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
    }

//    public WatchlistDao getWatchlistDao(){
//
//    }
}