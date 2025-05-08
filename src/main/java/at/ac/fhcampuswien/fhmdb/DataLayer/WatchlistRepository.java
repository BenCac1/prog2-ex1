package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.FhmdbApplication;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {
    private static Dao<WatchlistMovieEntity, Long> dao;

    public WatchlistRepository(Dao<WatchlistMovieEntity, Long> dao) {
        WatchlistRepository.dao = dao;
    }

    public WatchlistRepository() {
        dao = FhmdbApplication.dbManager.getWatchlistDao();
    }

    public List<WatchlistMovieEntity> getWatchlist() throws DatabaseException {
        try {
            return dao.queryForAll();
        } catch (SQLException e){
            throw new DatabaseException("Fehler beim Abrufen der Filme", e);
        }
    }

    public int addToWatchlist(WatchlistMovieEntity movie) throws DatabaseException {
        try {
            long counter = dao.queryBuilder().where().eq("apiId", movie.getApiId()).countOf();
            if (counter == 0){
                return dao.create(movie);
            } else {
                return 0;
            }
        } catch (SQLException e){
            throw new DatabaseException("Fehler beim Hinzufügen zur Watchlist", e);
        }
    }

    public int removeFromWatchlist(String apiId) throws DatabaseException {
        try {
            return dao.delete(dao.queryBuilder().where().eq("apiId", apiId).query());
        } catch (SQLException e){
            throw new DatabaseException("Fehler beim Entfernen des Films aus der Watchlist", e);
        }
    }
}
