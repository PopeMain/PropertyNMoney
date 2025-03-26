package propertynmoney;

import java.awt.*;

public enum PropertyColors {
    BROWN(new Color(150,75,20)),
    CYAN(Color.CYAN),
    MAGENTA(Color.MAGENTA),
    ORANGE(Color.ORANGE),
    RED(Color.RED),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN),
    PURPLE(new Color(128, 0, 128));

    private final Color pcolor;

    PropertyColors(Color awtcolor) {
        this.pcolor = awtcolor;
    }

    public Color getColor() {
        return pcolor;
    }

}
