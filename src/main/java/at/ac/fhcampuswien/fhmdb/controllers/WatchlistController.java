package at.ac.fhcampuswien.fhmdb.controllers;

import at.ac.fhcampuswien.fhmdb.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.DataLayer.MovieEntity;
import at.ac.fhcampuswien.fhmdb.DataLayer.MovieRepository;
import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieApiException;
import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.ui.WatchlistCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static at.ac.fhcampuswien.fhmdb.DataLayer.MovieEntity.toMovies;

public class WatchlistController implements Initializable {

    @FXML
    public JFXListView watchListView;

    @FXML
    public BorderPane mainPane;

    @FXML
    public VBox HomeVbox;


    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

    private final ClickEventHandler removeClicked = (clickedItem) ->
    {
        if (clickedItem instanceof Movie movie){
            WatchlistRepository repo = new WatchlistRepository();
            repo.removeFromWatchlist(movie.getId());

            System.out.println(repo.getWatchlist().toString());
            observableMovies.remove(movie);
            watchListView.setItems(observableMovies);   // set data of observable list to list view
            Callback cellStatus = watchListView.getCellFactory();   //refresh entries after pressing remove
            watchListView.setCellFactory(null);
            watchListView.setCellFactory(cellStatus);


        }
    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("init");
        WatchlistRepository watchlistRepo = new WatchlistRepository();
        List<WatchlistMovieEntity> savedMovies = null;
        try {
            savedMovies = watchlistRepo.getWatchlist();
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

        MovieRepository movieRepo = new MovieRepository();
        List<MovieEntity> watchlistMovies = new ArrayList<>();

        for (WatchlistMovieEntity watchlistMovieEntity : savedMovies){

            try {
                watchlistMovies.add(movieRepo.getMovie(watchlistMovieEntity.getApiId()));
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }
        }

        observableMovies.addAll(toMovies(watchlistMovies));
        System.out.println(toMovies(watchlistMovies));


        // initialize UI stuff
        watchListView.setItems(observableMovies);   // set data of observable list to list view
        watchListView.setCellFactory(watchListView -> new WatchlistCell(removeClicked));

    }
}
