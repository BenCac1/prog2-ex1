package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import okhttp3.*;

import java.net.URI;
import java.net.URL;
import java.util.List;

public class MovieAPI {
    private static final String URL_STRING = "http://prog2.fh-campuswien.ac.at/movies";


    public static URL buildURL(String query, Genres genre, String releaseYear, String ratingFrom) {
        URL url = null;
        StringBuilder urlBuild = new StringBuilder(URL_STRING);

        if (query != null) {
            urlBuild.append("?").append(query);
        }
        if (genre != null) {
            urlBuild.append("&genre=").append(genre.name());
        }
        if (releaseYear != null) {
            urlBuild.append("&release_year=").append(releaseYear);
        }
        if (ratingFrom != null) {
            urlBuild.append("&rating_from=").append(ratingFrom);
        }


        try {
            url = new URL(urlBuild.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    public static List<Movie> getMovies(String query, Genres genre, String releaseYear, String ratingFrom) {
        Request req = new Request.Builder()
                .url(buildURL(query, genre, releaseYear, ratingFrom))
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "http.agent")
                .build();

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(req);
        ObjectMapper mapper = new ObjectMapper();

        try (Response response = call.execute()) {
            ResponseBody responseBody = response.body();
            return List.of(mapper.readValue(responseBody.string(), Movie[].class));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Movie> getAllMovies() {
        return getMovies(null, null, null, null);
    }
}
