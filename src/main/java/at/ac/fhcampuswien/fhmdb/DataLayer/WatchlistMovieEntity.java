package at.ac.fhcampuswien.fhmdb.DataLayer;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "WatchlistMovieEntity")
public class WatchlistMovieEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(canBeNull = false)
    private String apiId;

    public WatchlistMovieEntity() {}

    public WatchlistMovieEntity(String apiId) {
        this.apiId = apiId;
    }

    public long getId() {
        return id;
    }

    public String getApiId() {
        return apiId;
    }
    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    @Override
    public String toString() {
        return "WatchlistMovieEntity{" +
                "id=" + id +
                ", apiId='" + apiId + '\'' +
                '}';
    }
}
