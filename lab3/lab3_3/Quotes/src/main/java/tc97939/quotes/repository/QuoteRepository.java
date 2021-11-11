package tc97939.quotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tc97939.quotes.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

}
