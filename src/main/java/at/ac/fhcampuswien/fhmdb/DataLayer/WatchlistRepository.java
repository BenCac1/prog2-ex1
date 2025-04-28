package at.ac.fhcampuswien.fhmdb.DataLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository {
    private static Connection connection;

    public WatchlistRepository(Connection connection) {
        this.connection = connection;
    }

    public static List<WatchlistMovieEntity> getAllWatchlists() throws SQLException {
        List<WatchlistMovieEntity> watchlists = new ArrayList<>();
        String query = "SELECT * FROM WatchlistMovieEntity";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                WatchlistMovieEntity watchlist = new WatchlistMovieEntity();

                watchlist.setId(resultSet.getInt("id"));
                watchlist.setApiId(resultSet.getString("apiId"));

                watchlists.add(watchlist);
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Fehler beim Abrufen der Filme", e);
        }
        return watchlists;
    }

    public void deleteWatchlists(List<Integer> ids) throws SQLException{
        if (ids == null || ids.isEmpty()) {
            throw new SQLException("Keine Filme zum Löschen übergeben");
        }

        StringBuilder queryBuilder = new StringBuilder("DELETE FROM WatchlistMovieEntity WHERE id IN (");
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
            System.out.println(affectedRows + "Watchlisten wurden erfolgreich gelöscht.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Fehler beim Löschen der Watchlisten", e);
        }
    }

    public void deleteWatchlist(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM WatchlistMovieEntity WHERE id = ?")) {
            statement.setInt(1, id);
            int affectedRow = statement.executeUpdate();

            if (affectedRow > 0) {
                System.out.println("Die Watchliste wurde erfolgreich gelöscht.");
            } else {
                System.out.println("Keine Watchliste mit der ID" + id + "gefunden.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Fehler beim Löschen der Watchliste mit ID " + id, e);
        }
    }

    public void addToWatchlist(Long movieID) throws SQLException{


        String checkQuery = "SELECET COUNT(*) FROM WatchlistMovieEntity WHERE id = ?";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setLong(1, movieID);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("Film ist bereits in der Watchliste.");
                    return;
                }
            }
        }

        String insertQuery = "INSERT INTO WatchlistMovieEntity VALUES (?)";

        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)){
            insertStmt.setLong(1, movieID);
            insertStmt.executeUpdate();
            System.out.println("Film wurde zur Watchlist hinzugefügt.");
        }
    }

}
