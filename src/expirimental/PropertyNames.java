package expirimental;

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
 * Key Methods:
 * <p>
 * - toString(): Overrides the toString method to return the display name associated
 *   with each property.
 * <p>
 * - getBuyValue(): returns the value of the property at the time to purchase.
 * <p>
 * <p>
 * Enumerated Property Names:
 * <p>
 * - ex: MEDITERRANEAN_AVE, BALTIC_AVE, PARK_AVE, BOARDWALK
 */
public enum PropertyNames {
    /** MEDITERRANEAN_AVE - "Mediterranean Ave" -BROWN- 60 */
    MEDITERRANEAN_AVE("Mediterranean Ave", propertynmoney.PropertyColors.BROWN,60),
    /** BALTIC_AVE - "Baltic Ave" -BROWN- 60 */
    BALTIC_AVE("Baltic Ave", propertynmoney.PropertyColors.BROWN,60),
    /** ORIENTAL_AVE - "Oriental Ave" -CYAN- 100 */
    ORIENTAL_AVE("Oriental Ave", propertynmoney.PropertyColors.CYAN,100),
    /** VERMONT_AVE - "Vermont Ave" -CYAN- 100 */
    VERMONT_AVE("Vermont Ave", propertynmoney.PropertyColors.CYAN,100),
    /** CONNECTICUT_AVE - "Connecticut Ave" -CYAN- 120 */
    CONNECTICUT_AVE("Connecticut Ave", propertynmoney.PropertyColors.CYAN,120),
    /** ST_CHARLES_PL - "St. Charles Pl" -MAGENTA- 140 */
    ST_CHARLES_PL("St. Charles Pl", propertynmoney.PropertyColors.MAGENTA,140),
    /** STATES_AVE - "States Ave" -MAGENTA- 140 */
    STATES_AVE("States Ave", propertynmoney.PropertyColors.MAGENTA,140),
    /** VIRGINIA_AVE - "Virginia Ave" -MAGENTA- 160 */
    VIRGINIA_AVE("Virginia Ave", propertynmoney.PropertyColors.MAGENTA,160),
    /** ST_JAMES_PL - "St. James Pl" -ORANGE- 180 */
    ST_JAMES_PL("St. James Pl", propertynmoney.PropertyColors.ORANGE,180),
    /** TENNESSEE_AVE - "Tennessee Ave" -ORANGE- 180 */
    TENNESSEE_AVE("Tennessee Ave", propertynmoney.PropertyColors.ORANGE,180),
    /** NEW_YORK_AVE - "New York Ave" -ORANGE- 200 */
    NEW_YORK_AVE("New York Ave", propertynmoney.PropertyColors.ORANGE,200),
    /** KENTUCKY_AVE - "Kentucky Ave" -RED- 220 */
    KENTUCKY_AVE("Kentucky Ave", propertynmoney.PropertyColors.RED,220),
    /** INDIANA_AVE - "Indiana Ave" -RED- 220 */
    INDIANA_AVE("Indiana Ave", propertynmoney.PropertyColors.RED,220),
    /** INDIANA_AVE - "Illisnois Ave" -RED- 240 */
    ILLINOIS_AVE("Illisnois Ave", propertynmoney.PropertyColors.RED,240),
    /** ATLANTIC_AVE - "Atlanic Ave" -YELLOW- 260 */
    ATLANTIC_AVE("Atlanic Ave", propertynmoney.PropertyColors.YELLOW,260),
    /** VENTNOR_AVE - "Ventor Ave" -YELLOW- 260 */
    VENTNOR_AVE("Ventor Ave", propertynmoney.PropertyColors.YELLOW,260),
    /** MARVIN_GAR - "Marvin Gardens" -YELLOW- 280 */
    MARVIN_GAR("Marvin Gardens", propertynmoney.PropertyColors.YELLOW,280),
    /** PACIFIC_AVE - "Pacific Ave" -GREEN- 300 */
    PACIFIC_AVE("Pacific Ave", propertynmoney.PropertyColors.GREEN,300),
    /** NORTH_CAROLINA_AVE - "North Carolina Ave" -GREEN- 300 */
    NORTH_CAROLINA_AVE("North Carolina Ave", propertynmoney.PropertyColors.GREEN,300),
    /** PENNSYLVANIA_AVE - "Pennsylvania Ave" -GREEN- 320 */
    PENNSYLVANIA_AVE("Pennsylvania Ave", propertynmoney.PropertyColors.GREEN,320),
    /** PARK_PL - "Park Pl" -PURPLE- 350 */
    PARK_PL("Park Pl", propertynmoney.PropertyColors.BLUE,350),
    /** BOARDWALK - "Boardwalk" -PURPLE- 400 */
    BOARDWALK("Boardwalk", propertynmoney.PropertyColors.BLUE,400);

    private final String displayName;
    private final int buyValue;
    private final propertynmoney.PropertyColors pColor;

    /**
     * TODO Finish Java Doc for Constructor
     * @param displayName String - the string name of the property
     * @param color Java.awt.Color - the display color for the property from the PropertyColors Enum
     * @param buyVal int - the value to buy the property
     */
    PropertyNames(String displayName, propertynmoney.PropertyColors color, int buyVal){
        this.displayName = displayName;
        this.buyValue = buyVal;
        this.pColor = color;
    }

    /**
     * Gets the display name for the property
     * @return Name of the Property
     */
    @Override
    public String toString(){
        return displayName;
    }

    public String getDisplayName() {
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

}
