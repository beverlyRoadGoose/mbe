package me.tobiadeyinka.movies.controllers;

import me.tobiadeyinka.movies.entities.Movie;
import me.tobiadeyinka.movies.modules.MoviesManager;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.io.IOException;

@RestController
@RequestMapping(value = "/movies", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MoviesController {

    @Autowired private MoviesManager moviesManager;

    @RequestMapping(value = "/top", method = RequestMethod.GET)
    @ResponseBody
    public List<Movie> getTopMovies() throws IOException {
        return moviesManager.getTopMovies();
    }

    @RequestMapping(value = "/top/{page}", method = RequestMethod.GET)
    @ResponseBody
    public List<Movie> getTopMovies(@PathVariable int page) throws IOException {
        return moviesManager.getTopMovies(page);
    }

    @RequestMapping(value = "/popular", method = RequestMethod.GET)
    @ResponseBody
    public List<Movie> getPopularMovies() throws IOException {
        return moviesManager.getPopularMovies();
    }

    @RequestMapping(value = "/popular/{page}", method = RequestMethod.GET)
    @ResponseBody
    public List<Movie> getPopularMovies(@PathVariable int page) throws IOException {
        return moviesManager.getPopularMovies(page);
    }

}
