package at.ac.fhcampuswien.fhmdb.DataLayer;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "WatchlistMovieEntity")
public class WatchlistMovieEntity {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String apiId;
}
