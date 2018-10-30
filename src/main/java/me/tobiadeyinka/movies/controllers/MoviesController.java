package me.tobiadeyinka.movies.controllers;

import me.tobiadeyinka.movies.entities.Movie;
import me.tobiadeyinka.movies.modules.MoviesManager;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/movies", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MoviesController {

    @Autowired private MoviesManager moviesManager;

    @RequestMapping(value = "/top", method = RequestMethod.GET)
    @ResponseBody
    public List<Movie> getTopMovies() throws IOException {
        return moviesManager.getTopMovies();
    }

    @RequestMapping(value = "/popular", method = RequestMethod.GET)
    @ResponseBody
    public List<Movie> getPopularMovies() throws IOException {
        return moviesManager.getPopularMovies();
    }

}
