package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.Test;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void initializeMovies_not_empty() {

        //Given

        List<Movie> movie;

        //when

        movie = Movie.initializeMovies();

        //then

        assertFalse(movie.isEmpty());
    }

    @Test
    void initializeMovies_not_null() {

        //Given

        List<Movie> movie;

        //when

        movie = Movie.initializeMovies();

        //then

        assertNotNull(movie);
    }

    @Test
    void initializeMovie_not_DoubleValues(){

        //Given

        List<Movie> movie;

        //when

        movie = Movie.initializeMovies();


        //then
        Set<Movie> movieSet = new HashSet<>();


        movieSet.addAll(movie);

        assertEquals(movieSet.size(),movie.size());
    }
}