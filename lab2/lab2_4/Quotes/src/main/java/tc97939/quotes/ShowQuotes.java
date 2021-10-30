package tc97939.quotes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ShowQuotes {
    private static final List<Show> shows = new ArrayList<>(
            Arrays.asList(
                    new Show("Breaking Bad", new ArrayList<>(
                            Arrays.asList(
                                    "I am the one who knocks!",
                                    "I did it for me. I liked it. I was good at it. And I was really — I was alive.",
                                    "This is my own private domicile and I will not be harassed... bitch!",
                                    "Some straight like you, giant stick up his ass, age what, 60? He’s just gonna break bad?"
                            )
                    )),
                    new Show("BoJack Horseman", new ArrayList<>(
                            Arrays.asList(
                                    "He’s so stupid he doesn't realize how miserable he should be.",
                                    "The key to being happy isn’t the search for meaning; it’s just to keep yourself busy with unimportant nonsense, and eventually, you’ll be dead.",
                                    "You sleep on my couch and you don’t pay rent. I’ve had tapeworms that are less parasitic.",
                                    "I’m responsible for my own happiness? I can’t even be responsible for my own breakfast!"
                            )
                    )),
                    new Show("Line of Duty", new ArrayList<>(
                            Arrays.asList(
                                    "None of my people would plant evidence. They know I would throw the book at them. Followed by the bookshelf.",
                                    "Oh Jesus, Mary and Joseph and the wee donkey...",
                                    "For years, the security in this department has been watertight... then you come along, suddenly we’re leaking like a colander.",
                                    "No one makes mugs out of AC-12."
                            )
                    ))
            )
    );

    public static List<Show> shows() {
        List<Show> showsWithQuotes = new ArrayList<>();
        for (Show show : shows)
            if (show.hasQuotes())
                showsWithQuotes.add(show);
        return showsWithQuotes;
    }

    public static String[] randomQuote(int id) {
        return shows.get(id).getRandomQuote();
    }

    public static String[] randomQuote() {
        Random r = new Random();
        int id = r.nextInt(shows.size());
        return randomQuote(id);
    }
}
