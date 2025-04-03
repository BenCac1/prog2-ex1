package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @Test
    void sortMovies_should_sort_in_ascending_order_by_Title() {
        HomeController hc = new HomeController();
        List<Movie> movieList = Movie.initializeMovies();
        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);
        hc.sortMovies(movies, true);
        assertEquals("Avatar", movies.get(0).getTitle());
        assertEquals("Life Is Beautiful", movies.get(1).getTitle());
        assertEquals("Puss in Boots", movies.get(2).getTitle());
        assertEquals("The Usual Suspects", movies.get(3).getTitle());
        assertEquals("The Wolf of Wall Street", movies.get(4).getTitle());
    }

    @Test
    void sortMovies_should_sort_in_descending_order_by_Title() {
        HomeController hc = new HomeController();
        List<Movie> movieList = Movie.initializeMovies();
        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);
        hc.sortMovies(movies, false);
        assertEquals("The Wolf of Wall Street", movies.get(0).getTitle());
        assertEquals("The Usual Suspects", movies.get(1).getTitle());
        assertEquals("Puss in Boots", movies.get(2).getTitle());
        assertEquals("Life Is Beautiful", movies.get(3).getTitle());
        assertEquals("Avatar", movies.get(4).getTitle());
    }

    @Test
    void sortMovies_should_return_List_which_is_already_ascending_ordered_as_it_is_by_Title() {
        HomeController hc = new HomeController();
        List<Movie> movieList = Movie.initializeMovies();
        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);
        hc.sortMovies(movies, true);
        assertEquals("Avatar", movies.get(0).getTitle());
        assertEquals("Life Is Beautiful", movies.get(1).getTitle());
        assertEquals("Puss in Boots", movies.get(2).getTitle());
        assertEquals("The Usual Suspects", movies.get(3).getTitle());
        assertEquals("The Wolf of Wall Street", movies.get(4).getTitle());
    }

    @Test
    void sortMovies_should_return_List_which_is_already_descending_ordered_as_it_is_by_Title() {
        HomeController hc = new HomeController();
        List<Movie> movieList = Movie.initializeMovies();
        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);
        hc.sortMovies(movies, false);
        assertEquals("The Wolf of Wall Street", movies.get(0).getTitle());
        assertEquals("The Usual Suspects", movies.get(1).getTitle());
        assertEquals("Puss in Boots", movies.get(2).getTitle());
        assertEquals("Life Is Beautiful", movies.get(3).getTitle());
        assertEquals("Avatar", movies.get(4).getTitle());
    }

    @Test
    void filterMovies_should_return_list_of_movies_based_on_genre_and_query() {
        HomeController hc = new HomeController();
        Genres genre = Genres.DRAMA;
        String text = "sole";
        List<Movie> expectedMovies = List.of(
                new Movie("The Usual Suspects", "A sole survivor tells...", List.of(Genres.CRIME, Genres.DRAMA, Genres.MYSTERY))
        );
        try (var mocked = mockStatic(MovieAPI.class)) {
            mocked.when(() -> MovieAPI.getMovies(text, genre, null, null)).thenReturn(expectedMovies);
            List<Movie> filteredMovies = hc.filterMovies(genre, text, null, null);
            assertEquals(1, filteredMovies.size());
            assertEquals("The Usual Suspects", filteredMovies.get(0).getTitle());
        }
    }

    @Test
    void filterMovies_should_return_list_of_movies_based_on_genre() {
        HomeController hc = new HomeController();
        Genres genre = Genres.DRAMA;
        List<Movie> expectedMovies = List.of(
                new Movie("Life Is Beautiful", "When an open-minded...", List.of(Genres.DRAMA, Genres.ROMANCE)),
                new Movie("The Usual Suspects", "A sole survivor tells...", List.of(Genres.CRIME, Genres.DRAMA, Genres.MYSTERY)),
                new Movie("Avatar", "A paraplegic Marine...", List.of(Genres.ANIMATION, Genres.DRAMA, Genres.ACTION)),
                new Movie("The Wolf of Wall Street", "Based on the true story...", List.of(Genres.DRAMA, Genres.ROMANCE, Genres.BIOGRAPHY))
        );
        try (var mocked = mockStatic(MovieAPI.class)) {
            mocked.when(() -> MovieAPI.getMovies(null, genre, null, null)).thenReturn(expectedMovies);
            List<Movie> filteredMovies = hc.filterMovies(genre, null, null, null);
            assertEquals(4, filteredMovies.size());
            assertEquals("Life Is Beautiful", filteredMovies.get(0).getTitle());
            assertEquals("The Usual Suspects", filteredMovies.get(1).getTitle());
            assertEquals("Avatar", filteredMovies.get(2).getTitle());
            assertEquals("The Wolf of Wall Street", filteredMovies.get(3).getTitle());
        }
    }

    @Test
    void filterMovies_should_return_list_of_movies_based_on_query() {
        HomeController hc = new HomeController();
        String text = "when";
        List<Movie> expectedMovies = List.of(
                new Movie("Life Is Beautiful", "When an open-minded...", List.of(Genres.DRAMA, Genres.ROMANCE)),
                new Movie("The Usual Suspects", "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which begin when five criminals meet...", List.of(Genres.CRIME, Genres.DRAMA, Genres.MYSTERY))
        );
        try (var mocked = mockStatic(MovieAPI.class)) {
            mocked.when(() -> MovieAPI.getMovies(text, null, null, null)).thenReturn(expectedMovies);
            List<Movie> filteredMovies = hc.filterMovies(null, text, null, null);
            assertEquals(2, filteredMovies.size());
            assertEquals("Life Is Beautiful", filteredMovies.get(0).getTitle());
            assertEquals("The Usual Suspects", filteredMovies.get(1).getTitle());
        }
    }

    @Test
    void filterMovies_should_return_list_of_movies_without_filters() {
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = List.of(
                new Movie("Life Is Beautiful", "When an open-minded...", List.of(Genres.DRAMA, Genres.ROMANCE)),
                new Movie("The Usual Suspects", "A sole survivor tells...", List.of(Genres.CRIME, Genres.DRAMA, Genres.MYSTERY)),
                new Movie("Puss in Boots", "An outlaw cat...", List.of(Genres.COMEDY, Genres.FAMILY, Genres.ANIMATION)),
                new Movie("Avatar", "A paraplegic Marine...", List.of(Genres.ANIMATION, Genres.DRAMA, Genres.ACTION)),
                new Movie("The Wolf of Wall Street", "Based on the true story...", List.of(Genres.DRAMA, Genres.ROMANCE, Genres.BIOGRAPHY))
        );
        try (var mocked = mockStatic(MovieAPI.class)) {
            mocked.when(() -> MovieAPI.getMovies(null, null, null, null)).thenReturn(expectedMovies);
            List<Movie> filteredMovies = hc.filterMovies(null, null, null, null);
            assertEquals(5, filteredMovies.size());
            assertEquals("Life Is Beautiful", filteredMovies.get(0).getTitle());
            assertEquals("The Usual Suspects", filteredMovies.get(1).getTitle());
            assertEquals("Puss in Boots", filteredMovies.get(2).getTitle());
            assertEquals("Avatar", filteredMovies.get(3).getTitle());
            assertEquals("The Wolf of Wall Street", filteredMovies.get(4).getTitle());
        }
    }

    @Test
    void filterMovies_should_return_list_of_movies_based_on_releaseYear_and_rating() {
        HomeController hc = new HomeController();
        String releaseYear = "2020";
        String ratingFrom = "7";
        List<Movie> expectedMovies = List.of(
                new Movie("Test Movie 2020", "A 2020 movie with high rating", List.of(Genres.ACTION))
        );
        try (var mocked = mockStatic(MovieAPI.class)) {
            mocked.when(() -> MovieAPI.getMovies(null, null, releaseYear, ratingFrom)).thenReturn(expectedMovies);
            List<Movie> filteredMovies = hc.filterMovies(null, null, releaseYear, ratingFrom);
            assertEquals(1, filteredMovies.size());
            assertEquals("Test Movie 2020", filteredMovies.get(0).getTitle());
        }
    }

    @Test
    void filterMovies_should_return_list_of_movies_based_on_all_filters() {
        HomeController hc = new HomeController();
        Genres genre = Genres.DRAMA;
        String text = "test";
        String releaseYear = "2020";
        String ratingFrom = "8";
        List<Movie> expectedMovies = List.of(
                new Movie("Test Drama 2020", "A test drama from 2020", List.of(Genres.DRAMA))
        );
        try (var mocked = mockStatic(MovieAPI.class)) {
            mocked.when(() -> MovieAPI.getMovies(text, genre, releaseYear, ratingFrom)).thenReturn(expectedMovies);
            List<Movie> filteredMovies = hc.filterMovies(genre, text, releaseYear, ratingFrom);
            assertEquals(1, filteredMovies.size());
            assertEquals("Test Drama 2020", filteredMovies.get(0).getTitle());
        }
    }

    // Weitere Tests für zusätzliche Methoden
    @Test
    void returns_the_most_popular_actor() {
        HomeController hc = new HomeController();
        Movie movie1 = new Movie("Inception", List.of("Christopher Nolan", "Ali", "RaZa"), List.of("Leonardo DiCaprio", "Tom Hardy"), 2010);
        Movie movie2 = new Movie("The Dark Knight", List.of("Christopher Nolan", "Hischam"), List.of("Christian Bale", "Heath Ledger"), 2008);
        Movie movie3 = new Movie("Interstellar", List.of("Christopher Nolan", "Ben10"), List.of("Matthew McConaughey", "Anne Hathaway"), 2014);
        Movie movie4 = new Movie("Titanic", List.of("James Cameron", "Yacine"), List.of("Leonardo DiCaprio", "Kate Winslet"), 1997);
        List<Movie> movies = new ArrayList<>(List.of(movie1, movie2, movie3, movie4));
        assertEquals("Leonardo DiCaprio", hc.getMostPopularActor(movies));
    }

    @Test
    void returns_the_longest_movie_title() {
        HomeController hc = new HomeController();
        Movie movie1 = new Movie("Inception", List.of("Christopher Nolan", "Ali", "RaZa"), List.of("Leonardo DiCaprio", "Tom Hardy"), 2010);
        Movie movie2 = new Movie("The Dark Knight", List.of("Christopher Nolan", "Hischam"), List.of("Christian Bale", "Heath Ledger"), 2008);
        Movie movie3 = new Movie("Interstellar", List.of("Christopher Nolan", "Ben10"), List.of("Matthew McConaughey", "Anne Hathaway"), 2014);
        Movie movie4 = new Movie("Titanic", List.of("James Cameron", "Yacine"), List.of("Leonardo DiCaprio", "Kate Winslet"), 1997);
        List<Movie> movies = new ArrayList<>(List.of(movie1, movie2, movie3, movie4));
        assertEquals(15, hc.getLongestMovieTitle(movies));
    }

    @Test
    void should_count_movies_from_director() {
        HomeController hc = new HomeController();
        Movie movie1 = new Movie("Inception", List.of("Christopher Nolan", "Ali", "RaZa"), List.of("Leonardo DiCaprio", "Tom Hardy"), 2010);
        Movie movie2 = new Movie("The Dark Knight", List.of("Christopher Nolan", "Hischam"), List.of("Christian Bale", "Heath Ledger"), 2008);
        Movie movie3 = new Movie("Interstellar", List.of("Christopher Nolan", "Ben10"), List.of("Matthew McConaughey", "Anne Hathaway"), 2014);
        Movie movie4 = new Movie("Titanic", List.of("James Cameron", "Yacine"), List.of("Leonardo DiCaprio", "Kate Winslet"), 1997);
        List<Movie> movies = new ArrayList<>(List.of(movie1, movie2, movie3, movie4));
        assertEquals(3, hc.countMoviesFrom(movies, "Christopher Nolan"));
        assertEquals(1, hc.countMoviesFrom(movies, "James Cameron"));
    }

    @Test
    void returns_movies_between_2000_and_2020() {
        HomeController hc = new HomeController();
        Movie movie1 = new Movie("Inception", List.of("Christopher Nolan", "Ali", "RaZa"), List.of("Leonardo DiCaprio", "Tom Hardy"), 2010);
        Movie movie2 = new Movie("The Dark Knight", List.of("Christopher Nolan", "Hischam"), List.of("Christian Bale", "Heath Ledger"), 2008);
        Movie movie3 = new Movie("Interstellar", List.of("Christopher Nolan", "Ben10"), List.of("Matthew McConaughey", "Anne Hathaway"), 2014);
        Movie movie4 = new Movie("Titanic", List.of("James Cameron", "Yacine"), List.of("Leonardo DiCaprio", "Kate Winslet"), 1997);
        List<Movie> movies = new ArrayList<>(List.of(movie1, movie2, movie3, movie4));
        List<Movie> numOfMovies = hc.getMoviesBetweenYears(movies, 2000, 2020);
        assertEquals(3, numOfMovies.size());
    }
}