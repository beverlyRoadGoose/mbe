package me.tobiadeyinka.movies.modules;

import me.tobiadeyinka.movies.entities.Movie;
import me.tobiadeyinka.movies.entities.Trailer;
import me.tobiadeyinka.movies.networking.MovieQueries;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

@Component
public class MoviesManager {

    public List<Movie> getTopMovies() throws IOException {
        return extractMoviesFromJsonString(MovieQueries.getTopRatedMovies(1));
    }

    public List<Movie> getTopMovies(int page) throws IOException {
        return extractMoviesFromJsonString(MovieQueries.getTopRatedMovies(page));
    }

    public List<Movie> getPopularMovies() throws IOException {
        return extractMoviesFromJsonString(MovieQueries.getPopularMovies(1));
    }

    public List<Movie> getPopularMovies(int page) throws IOException {
        return extractMoviesFromJsonString(MovieQueries.getPopularMovies(page));
    }

    public Movie getMovie(int movieId) throws IOException {
        JSONObject movie = new JSONObject(MovieQueries.getMovieDetails(movieId));
        return new Movie(
            movieId,
            movie.getString("title"),
            movie.getString("release_date"),
            movie.getString("poster_path"),
            movie.getString("overview")
        );
    }

    public Trailer getMovieTrailer(int movieId) throws IOException {
        JSONObject object = new JSONObject(MovieQueries.getMovieVideos(movieId));
        JSONArray results = object.getJSONArray("results");
        int n = results.length();
        JSONObject videoJsonObject;

        for (int i = 0; i < n; i++) {
            videoJsonObject = results.getJSONObject(i);
            if (videoJsonObject.getString("type").equals("Trailer") && videoJsonObject.getString("site").equals("YouTube")) {
                return new Trailer(movieId, videoJsonObject.getString("key"));
            }
        }

        return null;
    }

    private List<Movie> extractMoviesFromJsonString(String jsonString) {
        List<Movie> movies = new ArrayList<>();

        JSONObject object = new JSONObject(jsonString);
        JSONArray results = object.getJSONArray("results");
        int n = results.length();
        JSONObject movieJsonObject;

        for(int i = 0; i < n; i++){
            movieJsonObject = results.getJSONObject(i);
            int id = movieJsonObject.getInt("id");
            String title = movieJsonObject.getString("title");
            String releaseDate = movieJsonObject.getString("release_date");
            String moviePoster = movieJsonObject.getString("poster_path");
            String plotSynopsis = movieJsonObject.getString("overview");
            movies.add(new Movie(id, title, releaseDate, moviePoster, plotSynopsis));
        }

        return movies;
    }

}
