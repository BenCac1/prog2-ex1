package at.ac.fhcampuswien.fhmdb.DataLayer;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:h2:file:./data/testdb";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
    private ConnectionSource connectionSource;

    private Dao<MovieEntity, Long> movieDao;
    private Dao<WatchlistMovieEntity, Long> watchlistDao;

    public DatabaseManager() {
        try{
            createConnectionsSource();
            watchlistDao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
            movieDao = DaoManager.createDao(connectionSource, MovieEntity.class);
            createTables();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
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

    public Dao<WatchlistMovieEntity, Long> getWatchlistDao(){
        return watchlistDao;
    }

    public Dao<MovieEntity, Long> getMovieDao(){
        return movieDao;
    }
}