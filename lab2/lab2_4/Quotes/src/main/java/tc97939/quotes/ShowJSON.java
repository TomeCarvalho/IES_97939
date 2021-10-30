package tc97939.quotes;

public class ShowJSON {
    private int id;
    private String name;

    public ShowJSON(Show show) {
        id = show.getId();
        name = show.getName();
    }

    public ShowJSON(int id, String name) {
        this.id = id;
        this.name = name;
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
}
