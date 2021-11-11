package tc97939.quotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tc97939.quotes.model.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
