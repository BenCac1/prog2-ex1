package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXComboBox ratingComboBox;

    @FXML
    public JFXComboBox releaseYearComboBox;

    @FXML
    public JFXButton sortBtn;

    @FXML
    public JFXButton resetFilter;

    public List<Movie> allMovies = MovieAPI.getAllMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // Add the genres to the dropdown
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(FXCollections.observableArrayList(Genres.values()));

        // Add rating options to the dropdown (0-10)
        ratingComboBox.setPromptText("Filter by Rating");
        ratingComboBox.getItems().addAll(FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));

        // Add release year options to the dropdown (e.g., 1900 to current year)
        releaseYearComboBox.setPromptText("Filter by Release Year");
        List<String> years = new ArrayList<>();
        for (int year = 1900; year <= 2025; year++) {
            years.add(String.valueOf(year));
        }
        releaseYearComboBox.getItems().addAll(FXCollections.observableArrayList(years));

        // Action for when filter button is pressed
        searchBtn.setOnAction(event -> {
            List<Movie> filteredMovies = filterMovies(
                    (Genres) genreComboBox.getValue(),
                    searchField.getText(),
                    (String) releaseYearComboBox.getValue(),
                    (String) ratingComboBox.getValue()
            );
            observableMovies.setAll(filteredMovies);

            //Reset ListView
            movieListView.setItems(observableMovies);
            movieListView.setCellFactory(null);
            movieListView.setCellFactory(movieListView -> new MovieCell());
        });

        // Action for when sort button is pressed
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                sortMovies(observableMovies, true);
                sortBtn.setText("Sort (desc)");
            } else {
                sortMovies(observableMovies, false);
                sortBtn.setText("Sort (asc)");
            }
        });

        //Implementation of reset Filter Button
        resetFilter.setOnAction(event -> {
            observableMovies.setAll(allMovies);
            searchField.clear();
            genreComboBox.setValue(null);
            genreComboBox.setPromptText("Filter by Genre");
            ratingComboBox.setValue(null);
            ratingComboBox.setPromptText("Filter by Rating");
            releaseYearComboBox.setValue(null);
            releaseYearComboBox.setPromptText("Filter by Release Year");
        });
    }

    // Logic for what happens when sort button is pressed
    public void sortMovies (ObservableList<Movie> observableMovies, boolean asc) {
        if (asc){
            observableMovies.sort(Comparator.comparing(Movie::getTitle));
        } else {
            observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
        }
    }

    // Logic for what happens when filter button is pressed
    public List<Movie> filterMovies (Genres genre, String text, String releaseYear, String ratingFrom) {
        return MovieAPI.getMovies(text, genre, releaseYear, ratingFrom);
    }


    public String getMostPopularActor(List <Movie> movies) {
        String actor = movies.stream().flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

        return actor;
    }

    public int getLongestMovieTitle(List<Movie> movies) {
        return movies.stream()
                .mapToInt(movie -> movie.getTitle().length())
                .max()
                .orElse(0);
    }

    public long countMoviesFrom(List<Movie> movies, String director) {
        return movies.stream()
                .filter(movie -> movie.getDirectors().contains(director))
                .count();
    }

    public List<Movie> getMoviesBetweenYears (List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());
    }


}