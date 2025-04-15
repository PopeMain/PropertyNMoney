package propertynmoney;

import java.awt.*;

/**
 * Colors that a property belongs to, used to determine the amount of properties of one color, and what color to paint
 * in the properties list next to the property name
 * @author Frank Pope
 */
public enum PropertyColors {
    BROWN(new Color(150,75,20)),
    CYAN(Color.CYAN),
    MAGENTA(Color.MAGENTA),
    ORANGE(Color.ORANGE),
    RED(Color.RED),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE);

    private final Color pcolor; // Color value

    /**
     * Sets up which color using rgb in Java swing that the property should use to draw
     * @param awtcolor RGB value of the color that is drawn to player
     */
    PropertyColors(Color awtcolor) {
        this.pcolor = awtcolor;
    }

    /**
     * Get what the color value is
     * @return Color of the property
     */
    public Color getColor() {
        return pcolor;
    }

}
