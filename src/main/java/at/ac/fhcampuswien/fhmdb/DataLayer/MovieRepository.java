package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private static Dao<MovieEntity, Long> dao;
    private static Connection connection;

    public MovieRepository(Dao<MovieEntity, Long> dao, Connection connection) {
        MovieRepository.dao = dao;
        MovieRepository.connection = connection;
    }

    public static List<MovieEntity> getAllMovies() throws SQLException {
        List<MovieEntity> movies = new ArrayList<>();

        try {
            return dao.queryForAll();
        } catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Fehler beim Abrufen der Filme", e);
        }
    }

    public int removeAllMovies() throws SQLException {
        try {
            return dao.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Fehler beim löschen aller Filme", e);
        }
    }

    public MovieEntity getMovie(String apiId) throws SQLException {
        try {
            return dao.queryBuilder().where().eq("apiId", apiId).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Fehler beim laden des Filmes", e);
        }
    }

    public int addAllMovies(List<Movie> movies) throws SQLException {
        try {
            List<MovieEntity> movieEntities = MovieEntity.fromMovies(movies);
            return dao.create(movieEntities);
        } catch (Exception e){
            e.printStackTrace();
            throw new SQLException("Fehler beim Laden der Filmes", e);
        }
    }

}
