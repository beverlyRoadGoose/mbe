package me.tobiadeyinka.movies.entities;

public class Trailer {

    private final int movieId;
    private final String youtubeKey;

    public Trailer(int movieId, String youtubeKey) {
        this.movieId = movieId;
        this.youtubeKey = youtubeKey;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getYoutubeKey() {
        return youtubeKey;
    }

}
