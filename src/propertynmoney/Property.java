package propertynmoney;

import java.awt.Color;

public class Property {
    int buyValue;
    enum PropertyColor {
        BROWN(Color.BROWN),
        CYAN(Color.CYAN),
        MAGENTA(Color.MAGENTA),
        ORANGE(Color.ORANGE),
        RED(Color.RED),
        YELLOW(Color.YELLOW),
        GREEN(Color.GREEN),
        PURPLE(new Color(128, 0, 128))}
    enum Name {MEDITERRANEAN_AVE, BALTIC_AVE, ORIENTAL_AVE, VERMONT_AVE, CONNECTICUT_AVE,
        ST_CHARLES_PL, STATES_AVE, VIRGINIA_AVE, ST_JAMES_PL, TENNESSEE_AVE, NEW_YORK_AVE, KENTUCKY_AVE,
        INDIANA_AVE, ILLINOIS_AVE, ATLANTIC_AVE, VENTNOR_AVE, MARVIN_GAR, PACIFIC_AVE, NORTH_CAROLINA_AVE,
        PENNSYLVANIA_AVE, PARK_PL, BOARDWALK}
    enum Houses {MORTGAGE, ZERO, ONE, TWO, THREE, FOUR, HOTEL}

    public Property(int value, Name name, PropertyColor color) {
        this.buyValue = value;
    }

}
