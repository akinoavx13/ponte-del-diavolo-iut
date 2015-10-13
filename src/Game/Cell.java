package Game;

/**
 * Created by Maxime on 10/10/2015.
 */
public class Cell {

    private int x;
    private int y;

    private String color;

    private boolean visited;

    private Bridge bridge;

    private boolean blocked;

    public Cell(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.visited = false;
        this.bridge = null;
        this.blocked = false;
    }

    /*********
     * GETTERS*
     *********/

    public String getColor() {
        return color;
    }

    /*********
     *SETTERS*
     *********/

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isThisColor(String color) {
        return this.color == color;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isBridge() {
        return bridge != null;
    }

    public void setBridge(Bridge bridge) {
        this.bridge = bridge;
    }
}
