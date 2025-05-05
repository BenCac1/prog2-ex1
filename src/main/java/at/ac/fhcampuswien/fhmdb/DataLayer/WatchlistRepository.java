package at.ac.fhcampuswien.fhmdb.DataLayer;

import com.j256.ormlite.dao.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository {
    private static Dao<WatchlistMovieEntity, Long> dao;

    public WatchlistRepository(Dao<WatchlistMovieEntity, Long> dao) {
        WatchlistRepository.dao = dao;
    }

    public static List<WatchlistMovieEntity> getAllWatchlists() throws SQLException {
        try {
            return dao.queryForAll();
        } catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Fehler beim Abrufen der Filme", e);
        }
    }

    public int addToWatchlist(WatchlistMovieEntity movie) throws SQLException{

        try {
            long counter = dao.queryBuilder().where().eq("apiId", movie.getApiId()).countOf();
            if (counter == 0){
                return dao.create(movie);
            } else {
                return 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Fehler beim hinzufügen des Filmes", e);
        }
    }

    public int removeFromWatchlist(String apiId) throws SQLException{
        try {
            return dao.delete(dao.queryBuilder().where().eq("apiId", apiId).query());
        } catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Fehler beim entfernen des Filmes", e);
        }
    }
}
