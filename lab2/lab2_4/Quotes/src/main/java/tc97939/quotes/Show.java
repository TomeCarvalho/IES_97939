package tc97939.quotes;

import java.util.List;
import java.util.Random;

public class Show {
    private static int idCount = 0;
    private int id;
    private String name;
    private List<String> quotes;

    public Show(String name, List<String> quotes) {
        id = idCount++;
        this.name = name;
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }

    public String[] getRandomQuote() {
        Random rand = new Random();
        return new String[]{name, quotes.get(rand.nextInt(quotes.size()))};
    }

    public boolean hasQuotes() {
        return quotes.size() > 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<String> quotes) {
        this.quotes = quotes;
    }
}
