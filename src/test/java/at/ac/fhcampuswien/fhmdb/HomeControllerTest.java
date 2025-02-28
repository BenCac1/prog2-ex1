package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

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

    @Test
    void filterMovies_should_return_list_of_movies_based_on_the_genre_and_query() {
        // Given
        HomeController hc = new HomeController();

        List<Movie> movieList = Movie.initializeMovies();
        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);

        // When
        List<Movie> filteredMovies = hc.filterMovies(movies, Genres.DRAMA, "sole");

        // Then
        assertEquals("The Usual Suspects", filteredMovies.get(0).getTitle());
        assertEquals(1, filteredMovies.size());
    }

    @Test
    void filterMovies_should_return_list_of_movies_based_on_the_genre() {
        // Given
        HomeController hc = new HomeController();

        List<Movie> movieList = Movie.initializeMovies();
        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);

        // When
        List<Movie> filteredMovies = hc.filterMovies(movies, Genres.DRAMA, null);

        // Then

        assertEquals("Life Is Beautiful", filteredMovies.get(0).getTitle());
        assertEquals("The Usual Suspects", filteredMovies.get(1).getTitle());
        assertEquals("Avatar", filteredMovies.get(2).getTitle());
        assertEquals("The Wolf of Wall Street", filteredMovies.get(3).getTitle());
        assertEquals(4, filteredMovies.size());
    }

    @Test
    void filterMovies_should_return_list_of_movies_based_on_the_query() {
        // Given
        HomeController hc = new HomeController();

        List<Movie> movieList = Movie.initializeMovies();
        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);

        // When
        List<Movie> filteredMovies = hc.filterMovies(movies, null, "when");

        // Then
        assertEquals("Life Is Beautiful", filteredMovies.get(0).getTitle());
        assertEquals("The Usual Suspects", filteredMovies.get(1).getTitle());
        assertEquals(2, filteredMovies.size());
    }

    @Test
    void filterMovies_should_return_list_of_movies_without_filters() {
        // Given
        HomeController hc = new HomeController();

        List<Movie> movieList = Movie.initializeMovies();
        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);

        // When
        List<Movie> filteredMovies = hc.filterMovies(movies, null, null);

        // Then
        assertEquals("Life Is Beautiful", filteredMovies.get(0).getTitle());
        assertEquals("The Usual Suspects", filteredMovies.get(1).getTitle());
        assertEquals("Puss in Boots", filteredMovies.get(2).getTitle());
        assertEquals("Avatar", filteredMovies.get(3).getTitle());
        assertEquals("The Wolf of Wall Street", filteredMovies.get(4).getTitle());
        assertEquals(5, filteredMovies.size());
    }

    @Test
    void filterMovies_should_return_list_of_movies_without_regard_for_case_sensitivity() {
        // Given
        HomeController hc = new HomeController();

        List<Movie> movieList = Movie.initializeMovies();
        ObservableList<Movie> movies = FXCollections.observableArrayList(movieList);

        // When
        List<Movie> filteredMovies = hc.filterMovies(movies, null, "THE USUAL");

        // Then
        assertEquals("The Usual Suspects", filteredMovies.get(0).getTitle());
        assertEquals(1, filteredMovies.size());
    }

    @Test
    void filterMovies_should_return_empty_list_of_movies_when_given_an_empty_list() {
        // Given
        HomeController hc = new HomeController();

        ObservableList<Movie> movies = FXCollections.observableArrayList();

        // When
        List<Movie> filteredMovies = hc.filterMovies(movies, null, "THE USUAL");

        // Then
        assertEquals(0, filteredMovies.size());
    }
}