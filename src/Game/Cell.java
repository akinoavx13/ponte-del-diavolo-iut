package Game;

/**
 * Created by Maxime on 10/10/2015.
 */
public class Cell {

    private int x;
    private int y;
    private String color;
    private boolean visited;

    public Cell(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.visited = false;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isThisColor(String color) {
        return this.color == color;
    }
}
