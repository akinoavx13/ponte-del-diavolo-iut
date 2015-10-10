package Game;

import Constant.ColorConstants;

/**
 * Created by Maxime on 10/10/2015.
 */
public class Player {

    private boolean firstPlayer;
    private String color;

    public Player(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
        if (firstPlayer) {
            color = ColorConstants.CLEAR;
        } else {
            color = ColorConstants.DARK;
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }
}
