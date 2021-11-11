package tc97939.quotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tc97939.quotes.model.Movie;
import tc97939.quotes.model.Quote;
import tc97939.quotes.service.MovieQuoteService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieQuoteController {
    @Autowired
    private MovieQuoteService service;

    // Return a random quote from a random movie
    @GetMapping("/quote")
    public Quote getRandomQuote() {
        return service.getRandomQuoteFromRandomMovie();
    }

    // Return a list of all the movies
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return service.getAllMovies();
    }

    // Return a random quote from a specified movie
    @GetMapping("/quotes")
    public Quote getRandomQuoteFromMovie(@RequestParam(name = "movie") long id) {
        return service.getRandomQuoteFromMovie(id);
    }
}
