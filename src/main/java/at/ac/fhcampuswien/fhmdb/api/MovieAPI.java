package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieApiException;


public class MovieAPI {
    private static final String URL_STRING = "http://prog2.fh-campuswien.ac.at/movies";
    //Test if API Error works
    public static URL buildURL(String query, Genres genre, String releaseYear, String ratingFrom) {
        URL url = null;
        StringBuilder urlBuild = new StringBuilder(URL_STRING);

        if (query != null) {
            urlBuild.append("?query=").append(query);
        }
        if (genre != null) {
            urlBuild.append(query != null ? "&" : "?").append("genre=").append(genre.name());
        }
        if (releaseYear != null) {
            urlBuild.append((query != null || genre != null) ? "&" : "?").append("releaseYear=").append(releaseYear);
        }
        if (ratingFrom != null) {
            urlBuild.append((query != null || genre != null || releaseYear != null) ? "&" : "?").append("ratingFrom=").append(ratingFrom);
        }

        System.out.println("Constructed URL: " + urlBuild.toString()); // Temporary debug

        try {
            url = new URL(urlBuild.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    public static List<Movie> getMovies(String query, Genres genre, String releaseYear, String ratingFrom) throws MovieApiException {
        Request req = new Request.Builder()
                .url(buildURL(query, genre, releaseYear, ratingFrom))
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "http.agent")
                .build();

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(req);
        ObjectMapper mapper = new ObjectMapper();

        try (Response response = call.execute()) {
            if (!response.isSuccessful()) {
                throw new MovieApiException("Unsuccessful API call: " + response.code() + " " + response.message());
            }

            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new MovieApiException("Antwort der API war leer.");
            }

            return List.of(mapper.readValue(responseBody.byteStream(), Movie[].class));
        } catch (Exception e) {
            throw new MovieApiException("Fehler beim Abrufen der Filme von der API", e);
        }
    }

    public static List<Movie> getAllMovies() throws MovieApiException {
        return getMovies(null, null, null, null);
    }
}