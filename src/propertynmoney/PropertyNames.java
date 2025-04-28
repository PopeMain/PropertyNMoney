package propertynmoney;

import java.awt.*;

/**
 * @author Frank Pope
 * @author Nevin Fullerton
 * @enum PropertyNames:
 * <p>
 * The PropertyNames enum is the collection of properties by Name.
 * Each property name is associated with its display name, property color,
 * and purchase value.
 * <p/>
 * Each enumerated constant in the PropertyNames enum contains:
 * <p>
 * - A unique display name representing the property's name.
 * <p>
 * - The Color associated with the property
 * <p>
 * - The purchase value of the property.
 * <p>
 * Enumerated Property Names:
 * <p>
 * - ex: MEDITERRANEAN_AVE, BALTIC_AVE, PARK_AVE, BOARDWALK
 */
public enum PropertyNames {
    /** MEDITERRANEAN_AVE - "Mediterranean Ave" -BROWN- 60 */
    MEDITERRANEAN_AVE("Mediterranean Ave", PropertyColors.BROWN,60, new int[]{2, 10, 30, 90, 160, 250}, 50),
    /** BALTIC_AVE - "Baltic Ave" -BROWN- 60 */
    BALTIC_AVE("Baltic Ave", PropertyColors.BROWN,60, new int[]{4, 20, 60, 180, 320, 450}, 50),
    /** ORIENTAL_AVE - "Oriental Ave" -CYAN- 100 */
    ORIENTAL_AVE("Oriental Ave", PropertyColors.CYAN,100, new int[]{6, 30, 90, 270, 400, 550}, 50),
    /** VERMONT_AVE - "Vermont Ave" -CYAN- 100 */
    VERMONT_AVE("Vermont Ave", PropertyColors.CYAN,100, new int[]{6, 30, 90, 270, 400, 550}, 50),
    /** CONNECTICUT_AVE - "Connecticut Ave" -CYAN- 120 */
    CONNECTICUT_AVE("Connecticut Ave", PropertyColors.CYAN,120, new int[]{8, 40, 100, 300, 450, 600}, 50),
    /** ST_CHARLES_PL - "St. Charles Pl" -MAGENTA- 140 */
    ST_CHARLES_PL("St. Charles Pl", PropertyColors.MAGENTA,140, new int[]{10, 50, 150, 450, 625, 750}, 100),
    /** STATES_AVE - "States Ave" -MAGENTA- 140 */
    STATES_AVE("States Ave", PropertyColors.MAGENTA,140, new int[]{10, 50, 150, 450, 625, 750}, 100),
    /** VIRGINIA_AVE - "Virginia Ave" -MAGENTA- 160 */
    VIRGINIA_AVE("Virginia Ave", PropertyColors.MAGENTA,160, new int[]{12, 60, 180, 500, 700, 900}, 100),
    /** ST_JAMES_PL - "St. James Pl" -ORANGE- 180 */
    ST_JAMES_PL("St. James Pl", PropertyColors.ORANGE,180, new int[]{14, 70, 200, 550, 750, 950}, 100),
    /** TENNESSEE_AVE - "Tennessee Ave" -ORANGE- 180 */
    TENNESSEE_AVE("Tennessee Ave", PropertyColors.ORANGE,180, new int[]{14, 70, 200, 550, 750, 950}, 100),
    /** NEW_YORK_AVE - "New York Ave" -ORANGE- 200 */
    NEW_YORK_AVE("New York Ave", PropertyColors.ORANGE,200, new int[]{16, 80, 220, 600, 800, 1000}, 100),
    /** KENTUCKY_AVE - "Kentucky Ave" -RED- 220 */
    KENTUCKY_AVE("Kentucky Ave", PropertyColors.RED,220, new int[]{18, 90, 250, 700, 875, 1050}, 150),
    /** INDIANA_AVE - "Indiana Ave" -RED- 220 */
    INDIANA_AVE("Indiana Ave", PropertyColors.RED,220, new int[]{18, 90, 250, 700, 875, 1050}, 150),
    /** INDIANA_AVE - "Illisnois Ave" -RED- 240 */
    ILLINOIS_AVE("Illisnois Ave", PropertyColors.RED,240, new int[]{20, 100, 300, 750, 925, 1100}, 150),
    /** ATLANTIC_AVE - "Atlanic Ave" -YELLOW- 260 */
    ATLANTIC_AVE("Atlanic Ave", PropertyColors.YELLOW,260, new int[]{22, 110, 330, 800, 975, 1150}, 150),
    /** VENTNOR_AVE - "Ventor Ave" -YELLOW- 260 */
    VENTNOR_AVE("Ventor Ave", PropertyColors.YELLOW,260, new int[]{22, 110, 330, 800, 975, 1150}, 150),
    /** MARVIN_GAR - "Marvin Gardens" -YELLOW- 280 */
    MARVIN_GAR("Marvin Gardens", PropertyColors.YELLOW,280, new int[]{24, 120, 360, 850, 1025, 1200}, 150),
    /** PACIFIC_AVE - "Pacific Ave" -GREEN- 300 */
    PACIFIC_AVE("Pacific Ave", PropertyColors.GREEN,300, new int[]{26, 130, 390, 900, 1100, 1275}, 200),
    /** NORTH_CAROLINA_AVE - "North Carolina Ave" -GREEN- 300 */
    NORTH_CAROLINA_AVE("North Carolina Ave", PropertyColors.GREEN,300, new int[]{26, 130, 390, 900, 1100, 1275}, 200),
    /** PENNSYLVANIA_AVE - "Pennsylvania Ave" -GREEN- 320 */
    PENNSYLVANIA_AVE("Pennsylvania Ave", PropertyColors.GREEN,320, new int[]{28, 150, 450, 1000, 1200, 1400}, 200),
    /** PARK_PL - "Park Pl" -PURPLE- 350 */
    PARK_PL("Park Pl", PropertyColors.BLUE,350, new int[]{35, 175, 500, 1100, 1300, 1500}, 200),
    /** BOARDWALK - "Boardwalk" -PURPLE- 400 */
    BOARDWALK("Boardwalk", PropertyColors.BLUE,400, new int[]{50, 200, 600, 1400, 1700, 2000}, 200);

    private final String displayName;
    private final int buyValue;
    private final PropertyColors pColor;
    private final int[] rentValues;
    private final int mortgageValue;
    private final int housePrice;

    /**
     * TODO Finish Java Doc for Constructor
     * @param displayName String - the string name of the property
     * @param color Java.awt.Color - the display color for the property from the PropertyColors Enum
     * @param buyVal int - the value to buy the property
     */
    PropertyNames(String displayName, PropertyColors color, int buyVal, int[] rentValues, int housePrice){
        this.displayName = displayName;
        this.buyValue = buyVal;
        this.pColor = color;
        this.rentValues = rentValues;
        this.housePrice = housePrice;
        this.mortgageValue = buyValue / 2;
    }

    /**
     * Gets the display name for the property
     * @return Name of the Property
     */
    @Override
    public String toString(){
        return displayName;
    }

    /**
     * Gets the predetermined value for the property
     * @return The value of the property
     */
    public int getBuyValue() {
        return buyValue;
    }

    /**
     * Gets the java.awt.Color from the PropertyColors Enum.
     * @return the color from the property
     */
    public Color getColor(){
        return pColor.getColor();
    }

    public PropertyColors getColorEnum(){
        return pColor;
    }

    public int[] getRentValues() {return rentValues;}

    public int getMortgageValue() {return mortgageValue;}

    public int getHousePrice() {return housePrice;}
}