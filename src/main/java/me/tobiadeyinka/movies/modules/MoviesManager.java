package me.tobiadeyinka.movies.modules;

import me.tobiadeyinka.movies.entities.Movie;
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
        return extractMoviesFromJsonString(MovieQueries.getTopRatedMovies());
    }

    public List<Movie> getPopularMovies() throws IOException {
        return extractMoviesFromJsonString(MovieQueries.getPopularMovies());
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
