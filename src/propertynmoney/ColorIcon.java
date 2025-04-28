package propertynmoney;

import javax.swing.*;
import java.awt.*;

/**
 * @author Frank Pope
 * @author Nevin Fullerton
 * A customizable icon that displays a square filled with a specified color.
 * The size of the square can be defined or defaults to 15x15 pixels.
 */
public class ColorIcon implements Icon {
    private final Color color;
    private final int size;

    /**
     * Creates a new ColorIcon with the specified color and size.
     * @param color Color of the icon
     */
    public ColorIcon(Color color) {
        this(color, 15); // Default size is 15x15
    }

    /**
     * Creates a new ColorIcon with the specified color and size.
     * @param color Color of the icon
     * @param size Size of the icon
     */
    public ColorIcon(Color color, int size) {
        this.color = color;
        this.size = size;
    }

    /**
     * Paints the icon at the specified location and size.
     * @param c  a {@code Component} to get properties useful for painting
     * @param g  the graphics context
     * @param x  the X coordinate of the icon's top-left corner
     * @param y  the Y coordinate of the icon's top-left corner
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }

    /**
     * Returns the width of the icon.
     * @return valueOf size for the width
     */
    @Override
    public int getIconWidth() {
        return size;
    }

    /**
     * Returns the height of the icon.
     * @return valueOf size for the height
     */
    @Override
    public int getIconHeight() {
        return size;
    }
}