package propertynmoney;

/**
 * @author: Frank Pope and Nevin Fullerton
 * @enum: PropertyNames:
 * <p>
 * The PropertyNames enum is the collection of properties by Name.
 * Each property name is associated with its display name, property color,
 * and purchase value.
 * <p/>
 * Each enumerated constant in the PropertyNames enum contains:
 * <p>
 * - A unique display name representing the property's name.
 * <p>
 * - The Color assoicated with the property
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
    MEDITERRANEAN_AVE("Mediterranean Ave",60),
    /** BALTIC_AVE - "Baltic Ave" -BROWN- 60 */
    BALTIC_AVE("Baltic Ave",60),
    /** ORIENTAL_AVE - "Oriental Ave" -CYAN- 100 */
    ORIENTAL_AVE("Oriental Ave", 100),
    /** VERMONT_AVE - "Vermont Ave" -CYAN- 100 */
    VERMONT_AVE("Vermont Ave", 100),
    /** CONNECTICUT_AVE - "Connecticut Ave" -CYAN- 120 */
    CONNECTICUT_AVE("Connecticut Ave", 120),
    /** ST_CHARLES_PL - "St. Charles Pl" -MAGENTA- 140 */
    ST_CHARLES_PL("St. Charles Pl", 140),
    /** STATES_AVE - "States Ave" -MAGENTA- 140 */
    STATES_AVE("States Ave", 140),
    /** VIRGINIA_AVE - "Virginia Ave" -MAGENTA- 160 */
    VIRGINIA_AVE("Virginia Ave", 160),
    /** ST_JAMES_PL - "St. James Pl" -ORANGE- 180 */
    ST_JAMES_PL("St. James Pl", 180),
    /** TENNESSEE_AVE - "Tennessee Ave" -ORANGE- 180 */
    TENNESSEE_AVE("Tennessee Ave", 180),
    /** NEW_YORK_AVE - "New York Ave" -ORANGE- 200 */
    NEW_YORK_AVE("New York Ave", 200),
    /** KENTUCKY_AVE - "Kentucky Ave" -RED- 220 */
    KENTUCKY_AVE("Kentucky Ave", 220),
    /** INDIANA_AVE - "Indiana Ave" -RED- 220 */
    INDIANA_AVE("Indiana Ave", 220),
    /** INDIANA_AVE - "Illisnois Ave" -RED- 240 */
    ILLINOIS_AVE("Illisnois Ave", 240),
    /** ATLANTIC_AVE - "Atlanic Ave" -YELLOW'- 260 */
    ATLANTIC_AVE("Atlanic Ave", 260),
    /** VENTNOR_AVE - "Ventor Ave" -YELLOW- 260 */
    VENTNOR_AVE("Ventor Ave", 260),
    /** MARVIN_GAR - "Marvin Gardens" -YELLOW- 280 */
    MARVIN_GAR("Marvin Gardens", 280),
    /** PACIFIC_AVE - "Pacific Ave" -GREEN- 300 */
    PACIFIC_AVE("Pacific Ave", 300),
    /** NORTH_CAROLINA_AVE - "North Carolina Ave" -GREEN- 300 */
    NORTH_CAROLINA_AVE("North Carolina Ave", 300),
    /** PENNSYLVANIA_AVE - "Pennsylvania Ave" -GREEN- 320 */
    PENNSYLVANIA_AVE("Pennsylvania Ave", 320),
    /** PARK_PL - "Park Pl" -PURPLE- 350 */
    PARK_PL("Park Pl", 350),
    /** BOARDWALK - "Boardwalk" -PURPLE- 400 */
    BOARDWALK("Boardwalk", 400);

    private final String displayName;
    private final int buyValue;

    PropertyNames(String displayName, int buyVal){
        this.displayName = displayName;
        this.buyValue = buyVal;
    }

    @Override
    public String toString(){
        return displayName;
    }

    public int getBuyValue() {
        return buyValue;
    }
}
