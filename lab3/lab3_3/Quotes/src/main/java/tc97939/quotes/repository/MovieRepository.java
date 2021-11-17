package tc97939.quotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tc97939.quotes.model.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT * FROM movies ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Movie randomMovie();
}
