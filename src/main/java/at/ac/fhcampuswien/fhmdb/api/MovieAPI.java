package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import okhttp3.*;

import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class MovieAPI {
    private static final String URL_STRING = "http://prog2.fh-campuswien.ac.at/movies";


    public static URL buildURL(String query, Genres genre, String releaseYear, String ratingFrom) {
        URL url = null;
        StringBuilder urlBuild = new StringBuilder(URL_STRING);

        if (query != null) {
            urlBuild.append("?query=").append(query);
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
        //Creates a new instance (configurable HTTP-Request)
        // Using Builder to build the URL as needed
        // Setting the expected User-Agent/HTTP-Client for a successful request
        Request req = new Request.Builder()
                .url(buildURL(query, genre, releaseYear, ratingFrom))
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "http.agent")
                .build();

        OkHttpClient client = new OkHttpClient();   //Creates a new instance (Client manages network requests such as Get, Post, Put and Delete)
        Call call = client.newCall(req);            //creates a Call-Object which represents an HTTP-request ; "req" being the specific request(URL)
        ObjectMapper mapper = new ObjectMapper();   //Converts JSON file to into a Java object (could also be used to do the opposite)

        //sensing a synchron request (meanwhile blocking the thread)
        try (Response response = call.execute()) {
            ResponseBody responseBody = response.body();    //saves the response (JSON file)
            return List.of(mapper.readValue(responseBody.byteStream(), Movie[].class));     //converts the String in responseBody into a Movie-Object with the help of ObjectMapper
                                                                                            // Using .byteStram instead of .string to not overload responseBody in the case of big JSON files

        } catch (Exception e) {
            e.printStackTrace();
        }

        //better than returning null. Helps to avoid NullPointerException in other parts of the code
        return Collections.emptyList();
    }

    public static List<Movie> getAllMovies() {
        return getMovies(null, null, null, null);
    }
}
