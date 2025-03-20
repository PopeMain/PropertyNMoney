package propertynmoney;

import java.awt.*;

public enum PropertyColors {
    BROWN(new Color(150,75,0)),
    CYAN(Color.CYAN),
    MAGENTA(Color.MAGENTA),
    ORANGE(Color.ORANGE),
    RED(Color.RED),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN),
    PURPLE(new Color(128, 0, 128));

    private Color color;

    PropertyColors(Color awtcolor) {
        this.color = awtcolor;
    }

    public Color getColor() {
        return color;
    }

}
