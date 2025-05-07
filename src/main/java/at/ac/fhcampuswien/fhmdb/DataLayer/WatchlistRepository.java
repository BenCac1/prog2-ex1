package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.FhmdbApplication;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository {
    private static Dao<WatchlistMovieEntity, Long> dao;

    public WatchlistRepository(Dao<WatchlistMovieEntity, Long> dao) {
        WatchlistRepository.dao = dao;
    }
    public WatchlistRepository() {
        dao = FhmdbApplication.dbManager.getWatchlistDao();
    }

    public List<WatchlistMovieEntity> getWatchlist() {
        try {
            return dao.queryForAll();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Fehler beim Abrufen der Filme" + e.getMessage());
        }
        return null;
    }

    public int addToWatchlist(WatchlistMovieEntity movie){

        try {
            long counter = dao.queryBuilder().where().eq("apiId", movie.getApiId()).countOf();
            if (counter == 0){
                return dao.create(movie);
            } else {
                return 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Fehler beim hinzufügen des Filmes " + e.getMessage());
            return 1;
        }
    }

    public int removeFromWatchlist(String apiId){
        try {
            return dao.delete(dao.queryBuilder().where().eq("apiId", apiId).query());
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Fehler beim entfernen des Filmes: " + e);
        }
        return -1;
    }
}
