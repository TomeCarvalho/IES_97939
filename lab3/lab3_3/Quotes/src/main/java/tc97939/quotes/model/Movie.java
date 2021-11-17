package tc97939.quotes.model;

import javax.persistence.*;


@Entity
@Table(name = "movies")
public class Movie {
    private long id;
    private String title;
    private int year;

    public Movie() {

    }

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public Movie(Object[] columns) {
        title = (String) columns[1];
        year = Integer.parseInt(String.valueOf(columns[0]));
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

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
