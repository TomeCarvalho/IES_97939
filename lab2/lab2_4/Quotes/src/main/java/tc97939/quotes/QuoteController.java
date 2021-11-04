package tc97939.quotes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class QuoteController {
    private final AtomicLong counter = new AtomicLong();

    // Return a random quote from a random show
    @GetMapping("/quote")
    public QuoteJSON quote() {
        String[] quote = ShowQuotes.randomQuote();
        return new QuoteJSON(counter.incrementAndGet(), quote[0], quote[1]);
    }

    // Return a random quote from a specified show
    @GetMapping("/quotes")
    public QuoteJSON quotes(@RequestParam(value = "show") int show_id) {
        String[] quote = ShowQuotes.randomQuote(show_id);
        return new QuoteJSON(counter.incrementAndGet(), quote[0], quote[1]);
    }

    // List all available shows (for which some quote exists)
    @GetMapping("/shows")
    public List<ShowJSON> shows() {
        List<ShowJSON> list = new ArrayList<>();
        ShowQuotes.shows().forEach(show -> list.add(new ShowJSON(show)));
        return list;
    }
}
