package at.ac.fhcampuswien.fhmdb.controllers;

import at.ac.fhcampuswien.fhmdb.DataLayer.MovieRepository;
import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.FhmdbApplication;
import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import at.ac.fhcampuswien.fhmdb.ClickEventHandler;

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

    @FXML
    public Button homebtn;

    @FXML
    public Button watchbtn;

    @FXML
    public Button aboutbtn;

    @FXML
    public BorderPane mainPane;

    @FXML
    public VBox HomeVbox;




    private final ClickEventHandler onAddToWatchlistClicked = (clickedItem) ->
    {
        if (clickedItem instanceof Movie movie){
            WatchlistMovieEntity watchlistMovie = new WatchlistMovieEntity();
            watchlistMovie.setApiId(movie.getId());

            WatchlistRepository repo = new WatchlistRepository();
            repo.addToWatchlist(watchlistMovie);
            System.out.println(repo.getWatchlist().toString());
        }
    };

    public void changeCenterContent(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        try {
            mainPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while loading" + e.getMessage());
        }
    }



    public void loadHomeView() {
        if (mainPane.getCenter() != HomeVbox) {
            mainPane.setCenter(HomeVbox);
        }

    }

    public void loadWatchlistView() {
        changeCenterContent("/watchlist-view.fxml");
    }

    public void loadAboutView() {
        changeCenterContent("/About-view.fxml");
    }



    public List<Movie> allMovies = MovieAPI.getAllMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MovieRepository movieRepo = new MovieRepository();
        movieRepo.removeAllMovies();
        movieRepo.addAllMovies(allMovies);      // add movies to the local database
        observableMovies.addAll(allMovies);     // add movies to the view

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell(onAddToWatchlistClicked)); // use custom cell factory to display data

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
            movieListView.setCellFactory(movieListView -> new MovieCell(onAddToWatchlistClicked));
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