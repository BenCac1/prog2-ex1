package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

@DatabaseTable(tableName = "MovieEntity")
public class MovieEntity {
    @DatabaseField(id = true)
    private long id;
    @DatabaseField
    private String apiId;
    @DatabaseField
    private String title;
    @DatabaseField
    private String description;
    @DatabaseField
    private String genres;
    @DatabaseField
    private int releaseYear;
    @DatabaseField
    private String imgUrl;
    @DatabaseField
    private int lengthInMinutes;
    @DatabaseField
    private double rating;

    public String genresToString(List<Genres> genres) {

        StringBuilder genresString = new StringBuilder();
        for (Genres genre : genres) {
            genresString.append(genre.toString()).append(",");
        }
        if (genresString.length() > 0) {
            genresString.deleteCharAt(genresString.length() - 1);
        }
        return genresString.toString();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getApiId() {
        return apiId;
    }
    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenres() {
        return genres;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }
    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

}
