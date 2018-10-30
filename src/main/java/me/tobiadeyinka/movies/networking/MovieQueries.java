package me.tobiadeyinka.movies.networking;

import java.net.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;

public class MovieQueries {

    private static final String MOVIE_DB_API_KEY = System.getenv("MOVIE_DB_API_KEY");
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    private static final String POPULAR_MOVIES_ENDPOINT = String.format(
        "%spopular?api_key=%s&language=en-US&page=", BASE_URL, MOVIE_DB_API_KEY
    );

    private static final String TOP_RATED_MOVIES_ENDPOINT = String.format(
        "%stop_rated?api_key=%s&language=en-US&page=", BASE_URL, MOVIE_DB_API_KEY
    );

    public static String getPopularMovies(int page) throws IOException {
        try {
            URL url = new URL(POPULAR_MOVIES_ENDPOINT + page);
            return query(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getTopRatedMovies(int page) throws IOException {
        try {
            URL url = new URL(TOP_RATED_MOVIES_ENDPOINT + page);
            return query(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getMovieDetails(int movieId) throws IOException {
        URL url;
        try {
            url = new URL(BASE_URL + movieId + "?api_key=" + MOVIE_DB_API_KEY + "&language=en-US");
            return query(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getMovieVideos(int movieId) throws IOException{
        URL url;
        try{
            url = new URL(BASE_URL + movieId + "/videos" + "?api_key=" + MOVIE_DB_API_KEY + "&language=en-US");
            return query(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getMovieReviews(int movieId) throws IOException{
        URL url;
        try{
            url = new URL(BASE_URL + movieId + "/reviews" + "?api_key=" + MOVIE_DB_API_KEY + "&language=en-US&page=1");
            return query(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String query(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
