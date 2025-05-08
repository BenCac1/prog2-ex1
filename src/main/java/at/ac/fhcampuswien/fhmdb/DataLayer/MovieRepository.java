package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.FhmdbApplication;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private static Dao<MovieEntity, Long> dao;
    private static Connection connection;

    public MovieRepository(Dao<MovieEntity, Long> dao, Connection connection) {
        MovieRepository.dao = dao;
        MovieRepository.connection = connection;
    }

    public MovieRepository() {
        this.dao = FhmdbApplication.dbManager.getMovieDao();
    }

    public List<MovieEntity> getAllMovies() throws DatabaseException {
        try {
            return dao.queryForAll();
        } catch (SQLException e){
            throw new DatabaseException("Fehler beim Abrufen der Filme", e);
        }
    }

    public int removeAllMovies() throws DatabaseException {
        try {
            return dao.deleteBuilder().delete();
        } catch (SQLException e) {
            throw new DatabaseException("Fehler beim Löschen aller Filme", e);
        }
    }

    public MovieEntity getMovie(String apiId) throws DatabaseException {
        try {
            return dao.queryBuilder().where().eq("apiId", apiId).queryForFirst();
        } catch (SQLException e) {
            throw new DatabaseException("Fehler beim Laden des Filmes", e);
        }
    }

    public int addAllMovies(List<Movie> movies) throws DatabaseException {
        try {
            List<MovieEntity> movieEntities = MovieEntity.fromMovies(movies);
            return dao.create(movieEntities);
        } catch (Exception e){
            throw new DatabaseException("Fehler beim Laden der Filme", e);
        }
    }
}
