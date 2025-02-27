package at.ac.fhcampuswien.fhmdb;

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
    public  JFXButton resetFilterBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(FXCollections.observableArrayList(Genres.values()));

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        searchBtn.setOnAction(event -> {
            System.out.println(allMovies.size());
            List<Movie> filteredMovies = filterMovies(allMovies, (Genres) genreComboBox.getValue(), searchField.getText());

            observableMovies.setAll(filteredMovies);

            //Workaround
            movieListView.setItems(observableMovies);
            movieListView.setCellFactory(null);
            movieListView.setCellFactory(movieListView -> new MovieCell());
        });

        // Sort button example:
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

        resetFilterBtn.setOnAction(event -> {

//            List<Movie> filteredMovies = filterMovies(allMovies, (Genres) genreComboBox.getValue(), searchField.getText());
//
//            observableMovies.setAll(filteredMovies);
//
//            //Workaround
//            movieListView.setItems(observableMovies);
//            movieListView.setCellFactory(null);
//            movieListView.setCellFactory(movieListView -> new MovieCell());
        });

    }

    public void sortMovies (ObservableList<Movie> observableMovies, boolean asc) {
        if (asc){
            observableMovies.sort(Comparator.comparing(Movie::getTitle));
        } else {
            observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
        }
    }

    public List<Movie> filterMovies (List<Movie> allMovies, Genres genre, String text) {
        List<Movie> filteredMovies = new ArrayList<>();
        filteredMovies.addAll(allMovies);
        filteredMovies.removeIf(movie -> !(movie.getGenres().contains(genre)));
        return filteredMovies;
    }


}