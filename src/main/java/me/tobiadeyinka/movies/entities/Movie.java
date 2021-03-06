package me.tobiadeyinka.movies.entities;

public class Movie {

    private final int id;
    private final String title;
    private final String releaseDate;
    private final String moviePoster;
    private final String plotSynopsis;

    public Movie(int id, String title, String releaseDate, String moviePoster, String plotSynopsis) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.moviePoster = moviePoster;
        this.plotSynopsis = plotSynopsis;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

}
