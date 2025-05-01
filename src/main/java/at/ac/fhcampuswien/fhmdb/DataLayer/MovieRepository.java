package at.ac.fhcampuswien.fhmdb.DataLayer;

import com.j256.ormlite.dao.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private static Connection connection;

    public MovieRepository(Connection connection) {
        this.connection = connection;
    }

    public static List<MovieEntity> getAllMovies() throws SQLException {
        List<MovieEntity> movies = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM MovieEntity")) {
            while (resultSet.next()) {
                MovieEntity movie = new MovieEntity();

                movie.setId(resultSet.getInt("id"));
                movie.setApiId(resultSet.getString("apiId"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDescription(resultSet.getString("description"));
                movie.setGenres(resultSet.getString("genres"));
                movie.setReleaseYear(resultSet.getInt("releaseYear"));
                movie.setImgUrl(resultSet.getString("imgUrl"));
                movie.setLengthInMinutes(resultSet.getInt("lengthInMinutes"));
                movie.setRating(resultSet.getDouble("rating"));

                movies.add(movie);
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Fehler beim Abrufen der Filme", e);
        }
        return movies;
    }

    public void removeAllMovies() throws SQLException {
        String deleteQuery = "DELETE FROM MovieEntity";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
             int affectedRows = statement.executeUpdate();
            System.out.println(affectedRows + "Filme wurden gelöscht");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Fehler beim löschen aller Filme", e);
        }
    }

    //Methods to remove Movies using a list or single MovieID
    /*
    public void deleteMovies(List<Long> ids) throws SQLException{
        if (ids == null || ids.isEmpty()) {
            throw new SQLException("Keine Filme zum Löschen übergeben");
        }

        StringBuilder queryBuilder = new StringBuilder("DELETE FROM MovieEntity WHERE id IN (");
        for (int i = 0; i < ids.size(); i++) {
            queryBuilder.append("?");
            if (i < ids.size() - 1) {
                queryBuilder.append(",");
            }
        }
        queryBuilder.append(");");

        String query = queryBuilder.toString();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < ids.size(); i++) {
                statement.setLong(i + 1, ids.get(i));
            }

            int affectedRows = statement.executeUpdate();
            System.out.println(affectedRows + "Filme wurden erfolgreich gelöscht.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Fehler beim Löschen der Filme", e);
        }
    }

    public void deleteMovie(Long id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM MovieEntity WHERE id = ?")) {
            statement.setLong(1, id);
            int affectedRow = statement.executeUpdate();

            if (affectedRow > 0) {
                System.out.println("Der Film wurde erfolgreich gelöscht.");
            } else {
                System.out.println("Kein Film mit der ID" + id + "gefunden.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Fehler beim Löschen des Films mit ID " + id, e);
        }
    }
     */

    Dao<MovieEntity, Long> dao;

}
