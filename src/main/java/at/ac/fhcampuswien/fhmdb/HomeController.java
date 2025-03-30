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
    public  JFXButton sortBtn;

    @FXML
    public  JFXButton resetFilter;

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


        // Action for when filter button is pressed
        searchBtn.setOnAction(event -> {
            List<Movie> filteredMovies = filterMovies((Genres) genreComboBox.getValue(), searchField.getText());
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

        //Implementaion of reset Filter Button
        resetFilter.setOnAction(event -> {

            observableMovies.setAll(allMovies);
            searchField.clear();
            genreComboBox.setValue(null);
            genreComboBox.setPromptText("Filter by Genre");
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
    public List<Movie> filterMovies (Genres genre, String text) {
        return MovieAPI.getMovies(text, genre, null, null);
    }


}