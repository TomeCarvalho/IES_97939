package tc97939.quotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tc97939.quotes.model.Movie;
import tc97939.quotes.model.Quote;
import tc97939.quotes.service.MovieQuoteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieQuoteController {
    @Autowired
    private MovieQuoteService service;

    // Return a list of all the quotes
    @GetMapping("/quotes")
    public List<Quote> getAllQuotes() {
        return service.getAllQuotes();
    }

    // Return a list of all the movies
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return service.getAllMovies();
    }

    // Return all the information of a specified movie
    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable(value = "id") Long id) {
        return service.getMovieById(id);
    }

    // Return all the information of a random movie
    @GetMapping("/movies/random")
    public Movie getRandomMovie() {
        return service.getRandomMovie();
    }

    // Return all the quotes of a movie
    @GetMapping("/movies/{id}/quotes")
    public List<Quote> getMovieQuotesById(@PathVariable(value = "id") Long id) {
        return service.getMovieQuotesById(id);
    }

    // Return all the quotes from a random movie
    @GetMapping("/movies/random/quotes")
    public List<Quote> getRandomMovieQuotes() {
        return service.getRandomMovieQuotes();
    }

    // Return a random quote from a specified movie
    @GetMapping("/movies/{id}/quotes/random")
    public Quote getRandomQuoteFromMovie(@PathVariable Long id) {
        return service.getRandomQuoteFromMovie(id);
    }

    // Return a random quote from a random movie
    @GetMapping("/movies/random/quotes/random")
    public Quote getRandomQuoteFromRandomMovie() {
        return service.getRandomQuoteFromRandomMovie();
    }

    // Add a movie
    @PostMapping("/movies/add")
    public Movie addMovie(@Valid @RequestBody Movie movie) {
        return service.saveMovie(movie);
    }

    // Add a quote
    @PostMapping("/quotes/add")
    public Quote addQuote(@Valid @RequestBody Quote quote) {
        return service.saveQuote(quote);
    }
}
