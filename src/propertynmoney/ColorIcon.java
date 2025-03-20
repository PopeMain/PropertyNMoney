package propertynmoney;

import javax.swing.*;
import java.awt.*;

public class ColorIcon implements Icon {
    private final Color color;
    private final int size;

    public ColorIcon(Color color) {
        this(color, 15); // Default size is 15x15
    }

    public ColorIcon(Color color, int size) {
        this.color = color;
        this.size = size;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }
}