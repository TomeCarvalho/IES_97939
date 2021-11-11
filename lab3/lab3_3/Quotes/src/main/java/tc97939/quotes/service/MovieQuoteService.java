package tc97939.quotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tc97939.quotes.model.Movie;
import tc97939.quotes.model.Quote;
import tc97939.quotes.repository.MovieRepository;
import tc97939.quotes.repository.QuoteRepository;

import java.util.List;
import java.util.Random;

@Service
public class MovieQuoteService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> saveMovies(List<Movie> movies) {
        return movieRepository.saveAll(movies);
    }

    public Movie getMovieById(long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public String deleteMovie(long id) {
        movieRepository.deleteById(id);
        return "Deleted movie with ID " + id;
    }

    public Movie updateMovie(Movie movie) {
        Movie existingMovie = movieRepository.findById(movie.getId()).orElse(null);
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setYear(movie.getYear());
        return movieRepository.save(existingMovie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getRandomMovie() {
        List<Movie> movies = getAllMovies();
        int size = movies.size();
        return (size > 0) ? movies.get(new Random().nextInt(size)) : null;
    }

    public Quote saveQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public List<Quote> saveQuotes(List<Quote> quotes) {
        return quoteRepository.saveAll(quotes);
    }

    public Quote getQuoteById(long id) {
        return quoteRepository.findById(id).orElse(null);
    }

    public String deleteQuote(long id) {
        quoteRepository.deleteById(id);
        return "Deleted quote with ID " + id;
    }

    public Quote updateQuote(Quote quote) {
        Quote existingQuote = quoteRepository.findById(quote.getId()).orElse(null);
        existingQuote.setText(quote.getText());
        existingQuote.setMovie(quote.getMovie());
        return quoteRepository.save(existingQuote);
    }

    public Quote getRandomQuoteFromMovie(Movie movie) {
        return movie.randomQuote();
    }

    public Quote getRandomQuoteFromMovie(long movieId) {
        return getRandomQuoteFromMovie(getMovieById(movieId));
    }

    public Quote getRandomQuoteFromRandomMovie() {
        return getRandomQuoteFromMovie(getRandomMovie());
    }

    public List<Quote> getMovieQuotesById(long movieId) {
        return getMovieById(movieId).getQuotes();
    }

    public List<Quote> getRandomMovieQuotes() {
        return getRandomMovie().getQuotes();
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }
}
