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

        ObservableList<Movie> movies = FXCollections.observableArrayList (
              new Movie("Life Is Beautiful", "When an open-mided Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp", List.of(Genres.DRAMA, Genres.ROMANCE)),
              new Movie("The Usual Suspects", "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which begin when five criminals meet at a seemingly random police lineup0", List.of(Genres.CRIME, Genres.DRAMA, Genres.MYSTERY)),
              new Movie("Puss in Boots", "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town", List.of(Genres.COMEDY, Genres.FAMILY, Genres.ANIMATION)),
              new Movie("Avatar", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", List.of(Genres.ANIMATION, Genres.DRAMA, Genres.ACTION)),
              new Movie ("The Wolf of Wall Street", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", List.of(Genres.DRAMA, Genres.ROMANCE, Genres.BIOGRAPHY)));


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

        ObservableList<Movie> movies = FXCollections.observableArrayList(
                new Movie("Life Is Beautiful", "When an open-mided Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp", List.of(Genres.DRAMA, Genres.ROMANCE)),
                new Movie("The Usual Suspects", "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which begin when five criminals meet at a seemingly random police lineup0", List.of(Genres.CRIME, Genres.DRAMA, Genres.MYSTERY)),
                new Movie("Puss in Boots", "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town", List.of(Genres.COMEDY, Genres.FAMILY, Genres.ANIMATION)),
                new Movie("Avatar", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", List.of(Genres.ANIMATION, Genres.DRAMA, Genres.ACTION)),
                new Movie ("The Wolf of Wall Street", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", List.of(Genres.DRAMA, Genres.ROMANCE, Genres.BIOGRAPHY))
        );

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

        ObservableList<Movie> movies = FXCollections.observableArrayList(
                new Movie("Avatar", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", List.of(Genres.ANIMATION, Genres.DRAMA, Genres.ACTION)),
                new Movie("Life Is Beautiful", "When an open-mided Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp", List.of(Genres.DRAMA, Genres.ROMANCE)),
                new Movie("Puss in Boots", "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town", List.of(Genres.COMEDY, Genres.FAMILY, Genres.ANIMATION)),
                new Movie("The Usual Suspects", "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which begin when five criminals meet at a seemingly random police lineup0", List.of(Genres.CRIME, Genres.DRAMA, Genres.MYSTERY)),
                new Movie("The Wolf of Wall Street", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", List.of(Genres.DRAMA, Genres.ROMANCE, Genres.BIOGRAPHY))
        );

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

        ObservableList<Movie> movies = FXCollections.observableArrayList(
                new Movie("The Wolf of Wall Street", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", List.of(Genres.DRAMA, Genres.ROMANCE, Genres.BIOGRAPHY)),
                new Movie("The Usual Suspects", "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which begin when five criminals meet at a seemingly random police lineup0", List.of(Genres.CRIME, Genres.DRAMA, Genres.MYSTERY)),
                new Movie("Puss in Boots", "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town", List.of(Genres.COMEDY, Genres.FAMILY, Genres.ANIMATION)),
                new Movie("Life Is Beautiful", "When an open-mided Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp", List.of(Genres.DRAMA, Genres.ROMANCE)),
                new Movie("Avatar", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", List.of(Genres.ANIMATION, Genres.DRAMA, Genres.ACTION))
        );

        // When
        hc.sortMovies(movies, false);

        // Then
        assertEquals("The Wolf of Wall Street", movies.get(0).getTitle());
        assertEquals("The Usual Suspects", movies.get(1).getTitle());
        assertEquals("Puss in Boots", movies.get(2).getTitle());
        assertEquals("Life Is Beautiful", movies.get(3).getTitle());
        assertEquals("Avatar", movies.get(4).getTitle());
    }
}