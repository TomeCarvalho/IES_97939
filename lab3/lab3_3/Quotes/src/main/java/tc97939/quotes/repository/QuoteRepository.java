package tc97939.quotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tc97939.quotes.model.Movie;
import tc97939.quotes.model.Quote;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findQuotesByMovie(Movie movie);

    @Query(value = "SELECT * FROM quotes WHERE movie_id = ?1 ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote randomQuoteFromMovie(long movieId);

    @Query(value = "SELECT * FROM quotes WHERE movie_id = ?1", nativeQuery = true)
    List<Quote> findQuotesByMovieId(long movieId);
}
