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

    @GetMapping("/quote")
    public QuoteJSON quote(@RequestParam(value = "show", defaultValue = "") String idStr) {
        String[] quote;
        if (idStr.equals(""))
            quote = ShowQuotes.randomQuote();
        else {
            try {
                quote = ShowQuotes.randomQuote(Integer.parseInt(idStr));
            } catch (NumberFormatException e) {
                System.err.println("Invalid 'show' parameter: '" + idStr + "', fetching random quote.");
                quote = ShowQuotes.randomQuote();
            }
        }
        return new QuoteJSON(counter.incrementAndGet(), quote[0], quote[1]);
    }

    @GetMapping("/shows")
    public List<ShowJSON> shows() {
        List<ShowJSON> list = new ArrayList<>();
        ShowQuotes.shows().forEach(show -> list.add(new ShowJSON(show)));
        return list;
    }
}