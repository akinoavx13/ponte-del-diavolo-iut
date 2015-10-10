package Game;

/**
 * Created by Maxime on 10/10/2015.
 */
public class Cell {

    private int x;
    private int y;
    private String color;

    public Cell(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
