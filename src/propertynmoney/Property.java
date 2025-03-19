package propertynmoney;

public class Property {
    int buyValue;
    enum Color {BROWN, LIGHT_BLUE, PINK, ORANGE, RED, YELLOW, GREEN, PURPLE}
    enum Name {MEDITERRANEAN_AVE, BALTIC_AVE, ORIENTAL_AVE, VERMONT_AVE, CONNECTICUT_AVE,
        ST_CHARLES_PL, STATES_AVE, VIRGINIA_AVE, ST_JAMES_PL, TENNESSEE_AVE, NEW_YORK_AVE, KENTUCKY_AVE,
        INDIANA_AVE, ILLINOIS_AVE, ATLANTIC_AVE, VENTNOR_AVE, MARVIN_GAR, PACIFIC_AVE, NORTH_CAROLINA_AVE,
        PENNSYLVANIA_AVE, PARK_PL, BOARDWALK}
    enum Houses {MORTGAGE, ZERO, ONE, TWO, THREE, FOUR, HOTEL}

    public Property(int value, Name name, Color color) {
        this.buyValue = value;
    }
}
