package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.FhmdbApplication;
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
    public MovieRepository(){
        this.dao = FhmdbApplication.dbManager.getMovieDao();
    }

    public List<MovieEntity> getAllMovies() {
        List<MovieEntity> movies = new ArrayList<>();

        try {
            return dao.queryForAll();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Fehler beim Abrufen der Filme: " + e);
        }
        return null;
    }

    public int removeAllMovies() {
        try {
            return dao.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fehler beim löschen aller Filme: " + e);
        }
        return -1;
    }

    public MovieEntity getMovie(String apiId) {
        try {
            return dao.queryBuilder().where().eq("apiId", apiId).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fehler beim laden des Filmes: " + e);
        }
        return null;
    }

    public int addAllMovies(List<Movie> movies) {
        try {
            List<MovieEntity> movieEntities = MovieEntity.fromMovies(movies);
            return dao.create(movieEntities);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Fehler beim Laden der Filmes: " + e);
        }
        return -1;
    }

}
