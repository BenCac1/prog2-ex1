package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genres> genres;
    // TODO add more properties here

    /* public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    } */

    public Movie(String title, String description, List<Genres> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genres> getGenres() {
        return genres;
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
}
