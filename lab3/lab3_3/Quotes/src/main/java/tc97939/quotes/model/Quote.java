package tc97939.quotes.model;

import javax.persistence.*;

@Entity
@Table(name = "quotes")
public class Quote {
    private long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public Quote() {

    }

    public Quote(String text, Movie movie) {
        this.text = text;
        this.movie = movie;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", movie_id=" + movie.getId() +
                '}';
    }
}
