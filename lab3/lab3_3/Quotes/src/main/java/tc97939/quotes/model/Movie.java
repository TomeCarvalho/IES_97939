package tc97939.quotes.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "movies")
public class Movie {
    private long id;
    private String title;
    private int year;
    private List<Quote> quotes;

    public Movie() {

    }

    public Movie(String title, int year, List<Quote> quotes) {
        this.title = title;
        this.year = year;
        this.quotes = quotes;
    }

    public Movie(String title, int year) {
        this(title, year, new ArrayList<>());
    }

    public Quote randomQuote() {
        int size = quotes.size();
        return (size > 0) ? quotes.get(new Random().nextInt(size)) : null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @OneToMany(mappedBy = "movie")
    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
