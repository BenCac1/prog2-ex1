package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void sortMovies_should_sort_in_ascending_order_by_Title() {
        // Given
        HomeController hc = new HomeController();

        List<Movie> movieList = Movie.initializeMovies();
        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);

        // When
        hc.sortMovies(movies, true);

        // Then
        assertEquals("Avatar", movies.get(0).getTitle());
        assertEquals("Life Is Beautiful", movies.get(1).getTitle());
        assertEquals("Puss in Boots", movies.get(2).getTitle());
        assertEquals("The Usual Suspects", movies.get(3).getTitle());
        assertEquals("The Wolf of Wall Street", movies.get(4).getTitle());
    }

    @Test
    void sortMovies_should_sort_in_descending_order_by_Title() {
        // Given
        HomeController hc = new HomeController();

        List<Movie> movieList = Movie.initializeMovies();
        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);

        // When
        hc.sortMovies(movies, false);

        // Then
        assertEquals("The Wolf of Wall Street", movies.get(0).getTitle());
        assertEquals("The Usual Suspects", movies.get(1).getTitle());
        assertEquals("Puss in Boots", movies.get(2).getTitle());
        assertEquals("Life Is Beautiful", movies.get(3).getTitle());
        assertEquals("Avatar", movies.get(4).getTitle());
    }

    @Test
    void sortMovies_should_return_List_which_is_already_ascending_ordered_as_it_is_by_Title() {
        // Given
        HomeController hc = new HomeController();

        List<Movie> movieList = Movie.initializeMovies();
        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);

        // When
        hc.sortMovies(movies, true);

        // Then
        assertEquals("Avatar", movies.get(0).getTitle());
        assertEquals("Life Is Beautiful", movies.get(1).getTitle());
        assertEquals("Puss in Boots", movies.get(2).getTitle());
        assertEquals("The Usual Suspects", movies.get(3).getTitle());
        assertEquals("The Wolf of Wall Street", movies.get(4).getTitle());
    }


    @Test
    void sortMovies_should_return_List_which_is_already_descending_ordered_as_it_is_by_Title() {
        // Given
        HomeController hc = new HomeController();

        List<Movie> movieList = Movie.initializeMovies();
        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);

        // When
        hc.sortMovies(movies, false);

        // Then
        assertEquals("The Wolf of Wall Street", movies.get(0).getTitle());
        assertEquals("The Usual Suspects", movies.get(1).getTitle());
        assertEquals("Puss in Boots", movies.get(2).getTitle());
        assertEquals("Life Is Beautiful", movies.get(3).getTitle());
        assertEquals("Avatar", movies.get(4).getTitle());
    }

//    @Test
//    void filterMovies_should_return_list_of_movies_based_on_the_genre_and_query() {
//        // Given
//        HomeController hc = new HomeController();
//
//        List<Movie> movieList = Movie.initializeMovies();
//        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);
//
//        // When
//        List<Movie> filteredMovies = hc.filterMovies(movies, Genres.DRAMA, "sole");
//
//        // Then
//        assertEquals("The Usual Suspects", filteredMovies.get(0).getTitle());
//        assertEquals(1, filteredMovies.size());
//    }
//
//    @Test
//    void filterMovies_should_return_list_of_movies_based_on_the_genre() {
//        // Given
//        HomeController hc = new HomeController();
//
//        List<Movie> movieList = Movie.initializeMovies();
//        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);
//
//        // When
//        List<Movie> filteredMovies = hc.filterMovies(movies, Genres.DRAMA, null);
//
//        // Then
//
//        assertEquals("Life Is Beautiful", filteredMovies.get(0).getTitle());
//        assertEquals("The Usual Suspects", filteredMovies.get(1).getTitle());
//        assertEquals("Avatar", filteredMovies.get(2).getTitle());
//        assertEquals("The Wolf of Wall Street", filteredMovies.get(3).getTitle());
//        assertEquals(4, filteredMovies.size());
//    }
//
//    @Test
//    void filterMovies_should_return_list_of_movies_based_on_the_query() {
//        // Given
//        HomeController hc = new HomeController();
//
//        List<Movie> movieList = Movie.initializeMovies();
//        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);
//
//        // When
//        List<Movie> filteredMovies = hc.filterMovies(movies, null, "when");
//
//        // Then
//        assertEquals("Life Is Beautiful", filteredMovies.get(0).getTitle());
//        assertEquals("The Usual Suspects", filteredMovies.get(1).getTitle());
//        assertEquals(2, filteredMovies.size());
//    }
//
//    @Test
//    void filterMovies_should_return_list_of_movies_without_filters() {
//        // Given
//        HomeController hc = new HomeController();
//
//        List<Movie> movieList = Movie.initializeMovies();
//        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);
//
//        // When
//        List<Movie> filteredMovies = hc.filterMovies(movies, null, null);
//
//        // Then
//        assertEquals("Life Is Beautiful", filteredMovies.get(0).getTitle());
//        assertEquals("The Usual Suspects", filteredMovies.get(1).getTitle());
//        assertEquals("Puss in Boots", filteredMovies.get(2).getTitle());
//        assertEquals("Avatar", filteredMovies.get(3).getTitle());
//        assertEquals("The Wolf of Wall Street", filteredMovies.get(4).getTitle());
//        assertEquals(5, filteredMovies.size());
//    }
//
//    @Test
//    void filterMovies_should_return_list_of_movies_without_regard_for_case_sensitivity() {
//        // Given
//        HomeController hc = new HomeController();
//
//        List<Movie> movieList = Movie.initializeMovies();
//        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);
//
//        // When
//        List<Movie> filteredMovies = hc.filterMovies(movies, null, "THE USUAL");
//
//        // Then
//        assertEquals("The Usual Suspects", filteredMovies.get(0).getTitle());
//        assertEquals(1, filteredMovies.size());
//    }
//
//    @Test
//    void filterMovies_should_return_empty_list_of_movies_when_given_an_empty_list() {
//        // Given
//        HomeController hc = new HomeController();
//
//        ObservableList<Movie> movies = FXCollections.observableArrayList();
//
//        // When
//        List<Movie> filteredMovies = hc.filterMovies(movies, null, "THE USUAL");
//
//        // Then
//        assertEquals(0, filteredMovies.size());
//    }


    @Test
    void returns_the_most_popular_acotr() {
        // Given
        Movie movie1 = new Movie("Inception",List.of("Christopher Nolan", "Ali", "RaZa"),  List.of("Leonardo DiCaprio", "Tom Hardy"), 2010);
        Movie movie2 = new Movie("The Dark Knight", List.of("Christopher Nolan", "Hischam"), List.of("Christian Bale", "Heath Ledger"), 2008);
        Movie movie3 = new Movie("Interstellar", List.of("Christopher Nolan", "Ben10") , List.of("Matthew McConaughey", "Anne Hathaway"), 2014);
        Movie movie4 = new Movie("Titanic", List.of("James Cameron", "Yacine"),  List.of("Leonardo DiCaprio", "Kate Winslet"), 1997);

        List<Movie> movies = new ArrayList<>(List.of(movie1, movie2, movie3, movie4));
        HomeController hc = new HomeController();



        //when + //then
        assertEquals("Leonardo DiCaprio", hc.getMostPopularActor(movies));
    }

    @Test
    void returns_the_longest_movie_title() {
        // Given
        Movie movie1 = new Movie("Inception",List.of("Christopher Nolan", "Ali", "RaZa"),  List.of("Leonardo DiCaprio", "Tom Hardy"), 2010);
        Movie movie2 = new Movie("The Dark Knight", List.of("Christopher Nolan", "Hischam"), List.of("Christian Bale", "Heath Ledger"), 2008);
        Movie movie3 = new Movie("Interstellar", List.of("Christopher Nolan", "Ben10") , List.of("Matthew McConaughey", "Anne Hathaway"), 2014);
        Movie movie4 = new Movie("Titanic", List.of("James Cameron", "Yacine"),  List.of("Leonardo DiCaprio", "Kate Winslet"), 1997);

        List<Movie> movies = new ArrayList<>(List.of(movie1, movie2, movie3, movie4));
        HomeController hc = new HomeController();

        //when + //then
        assertEquals(15, hc.getLongestMovieTitle(movies));
    }

    @Test
    void should_count_movies_from_director() {
        // Given
        Movie movie1 = new Movie("Inception",List.of("Christopher Nolan", "Ali", "RaZa"),  List.of("Leonardo DiCaprio", "Tom Hardy"), 2010);
        Movie movie2 = new Movie("The Dark Knight", List.of("Christopher Nolan", "Hischam"), List.of("Christian Bale", "Heath Ledger"), 2008);
        Movie movie3 = new Movie("Interstellar", List.of("Christopher Nolan", "Ben10") , List.of("Matthew McConaughey", "Anne Hathaway"), 2014);
        Movie movie4 = new Movie("Titanic", List.of("James Cameron", "Yacine"),  List.of("Leonardo DiCaprio", "Kate Winslet"), 1997);

        List<Movie> movies = new ArrayList<>(List.of(movie1, movie2, movie3, movie4));
        HomeController hc = new HomeController();

        //when + //then
        assertEquals(3, hc.countMoviesFrom(movies, "Christopher Nolan"));
        assertEquals(1, hc.countMoviesFrom(movies, "James Cameron"));
    }

    @Test
    void returns_movies_between_2000_and_2020() {
        // Given
        Movie movie1 = new Movie("Inception",List.of("Christopher Nolan", "Ali", "RaZa"),  List.of("Leonardo DiCaprio", "Tom Hardy"), 2010);
        Movie movie2 = new Movie("The Dark Knight", List.of("Christopher Nolan", "Hischam"), List.of("Christian Bale", "Heath Ledger"), 2008);
        Movie movie3 = new Movie("Interstellar", List.of("Christopher Nolan", "Ben10") , List.of("Matthew McConaughey", "Anne Hathaway"), 2014);
        Movie movie4 = new Movie("Titanic", List.of("James Cameron", "Yacine"),  List.of("Leonardo DiCaprio", "Kate Winslet"), 1997);

        List<Movie> movies = new ArrayList<>(List.of(movie1, movie2, movie3, movie4));
        HomeController hc = new HomeController();

        //when
        List<Movie> numOfMovies = hc.getMoviesBetweenYears(movies, 2000, 2020);

        // then

        assertEquals(3, numOfMovies.size());

    }
}