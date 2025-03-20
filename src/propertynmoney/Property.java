package propertynmoney;

import java.awt.Color;
import propertynmoney.PropertyColors;
import propertynmoney.PropertyNames;

public class Property {
    int buyValue;
    private final PropertyNames name;
    private final PropertyColors color;

    enum Houses {MORTGAGE, ZERO, ONE, TWO, THREE, FOUR, HOTEL}

    public Property(int value, PropertyNames name, PropertyColors color) {
        this.buyValue = value;
        this.name = name;
        this.color = color;
    }
    public String getName() {
        return name.toString();
    }

    public PropertyColors getColorEnum() {
        return color;
    }

    public java.awt.Color getColor() {
        return color.getColor();
    }

    @Override
    public String toString() {
        return getName();
    }
}
