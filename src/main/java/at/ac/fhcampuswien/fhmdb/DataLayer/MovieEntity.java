package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@DatabaseTable(tableName = "MovieEntity")
public class MovieEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(canBeNull = false)
    private String apiId;
    @DatabaseField(canBeNull = false)
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

    public MovieEntity() {}

    public MovieEntity(String apiId, String title, String description, String genres, int releaseYear, String imgUrl, int lengthInMinutes, double rating) {
        this.apiId = apiId;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }
    public String genresToString(List<Genres> genres) {
        StringBuilder genresString = new StringBuilder();
        for (Genres genre : genres) {
            genresString.append(genre.name()).append(",");
        }
        if (genresString.length() > 0) {
            genresString.deleteCharAt(genresString.length() - 1);
        }
        return genresString.toString();
    }

    public List<String> convertGenresToList(String genresString) {
        return Arrays.asList(genresString.split(","));
    }

    public static List<MovieEntity> fromMovies(List<Movie> movies) {
        List<MovieEntity> movieEntities = new ArrayList<>();
        for (Movie movie : movies) {
            movieEntities.add(
                    new MovieEntity(
                            movie.getId(),
                            movie.getTitle(),
                            movie.getDescription(),
                            movie.getGenres().toString(),
                            movie.getReleaseYear(),
                            movie.getImgUrl(),
                            movie.getLengthInMinutes(),
                            movie.getRating()));
        }
        return movieEntities;
    }

    public static List<Movie> toMovies(List<MovieEntity> movieEntities) {

        List<Movie> movies = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntities) {
            List<Genres> genreList = Arrays.stream(movieEntity.getGenres().replaceAll("\\[|\\]| ", "").split(",")).map(Genres::valueOf).toList();

            movies.add(
                    new Movie(
                            movieEntity.getApiId(),
                            movieEntity.getTitle(),
                            genreList,
                            movieEntity.getReleaseYear(),
                            movieEntity.getDescription(),
                            movieEntity.getImgUrl(),
                            movieEntity.getLengthInMinutes(),
                            null,
                            null,
                            null,
                            movieEntity.getRating()));

        }

        return movies;
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
