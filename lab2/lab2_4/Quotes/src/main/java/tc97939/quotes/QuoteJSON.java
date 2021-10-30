package tc97939.quotes;

public class QuoteJSON {
    private final long id;
    private final String showName;
    private final String quote;

    public QuoteJSON(long id, String showName, String quote) {
        this.id = id;
        this.showName = showName;
        this.quote = quote;
    }

    public long getId() {
        return id;
    }

    public String getShowName() {
        return showName;
    }

    public String getQuote() {
        return quote;
    }
}