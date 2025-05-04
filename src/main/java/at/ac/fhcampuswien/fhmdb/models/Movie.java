package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Movie {
    private String id;
    private String title;
    private List<Genres> genres;
    private int releaseYear;
    private String description;
    private String imgUrl;
    private int lengthInMinutes;
    private List<String> directors;
    private List<String> writers;
    private List<String> mainCast ;
    private double rating;

    public Movie() {}

    public Movie(String title, String description, List<Genres> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public Movie(String title, List<String> directors, List<String> mainCast, int releaseYear) {
        this.title = title;
        this.directors = directors;
        this.mainCast = mainCast;
        this.releaseYear = releaseYear;
    }

    public Movie(String id, String title, List<Genres> genres, int releaseYear, String description, String imgUrl, int lengthInMinutes, List<String> directors, List<String> writers, List<String> mainCast, double rating) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.description = description;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
    }





    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();

        Movie lifeIsBeautiful = new Movie("Life Is Beautiful", "When an open-mided Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp", List.of(Genres.DRAMA, Genres.ROMANCE));
        Movie theUsualSuspects = new Movie("The Usual Suspects", "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which begin when five criminals meet at a seemingly random police lineup0", List.of(Genres.CRIME, Genres.DRAMA, Genres.MYSTERY));
        Movie pussInBoots = new Movie("Puss in Boots", "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town", List.of(Genres.COMEDY, Genres.FAMILY, Genres.ANIMATION));
        Movie avatar = new Movie("Avatar", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", List.of(Genres.ANIMATION, Genres.DRAMA, Genres.ACTION));
        Movie theWolfOfWallStreet = new Movie ("The Wolf of Wall Street", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", List.of(Genres.DRAMA, Genres.ROMANCE, Genres.BIOGRAPHY));


        movies.add(lifeIsBeautiful);
        movies.add(theUsualSuspects);
        movies.add(pussInBoots);
        movies.add(avatar);
        movies.add(theWolfOfWallStreet);


        return movies;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    public double getRating() {
        return rating;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public void setMainCast(List<String> mainCast) {
        this.mainCast = mainCast;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
