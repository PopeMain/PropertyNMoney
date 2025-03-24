package propertynmoney;

/**
 * @Author: Frank Pope and Nevin Fullerton
 * The PropertyNames enum is the collection of property names.
 * Each property name is associated with it's display name.
 * Each enumerated constant in the PropertyNames enum contains:
 * - A unique display name representing the property's name.
 *
 * Key Methods:
 * - toString(): Overrides the toString method to return the display name associated
 *   with each property.
 *
 * Enumerated Property Names:
 * - Examples include "Mediterranean Ave", "Baltic Ave", "Park Pl", "Boardwalk", among others.
 */
public enum PropertyNames {
    MEDITERRANEAN_AVE("Mediterranean Ave"), BALTIC_AVE("Baltic Ave"),
    ORIENTAL_AVE("Oriental Ave"), VERMONT_AVE("Vermont Ave"), CONNECTICUT_AVE("Connecticut Ave"),
    ST_CHARLES_PL("St. Charles Pl"), STATES_AVE("States Ave"), VIRGINIA_AVE("Virginia Ave"),
    ST_JAMES_PL("St. James Pl"), TENNESSEE_AVE("Tennessee Ave"), NEW_YORK_AVE("New York Ave"),
    KENTUCKY_AVE("Kentucky Ave"), INDIANA_AVE("Indiana Ave"), ILLINOIS_AVE("Illisnois Ave"),
    ATLANTIC_AVE("Atlanic Ave"), VENTNOR_AVE("Ventor Ave"), MARVIN_GAR("Marvin Gardens"),
    PACIFIC_AVE("Pacific Ave"), NORTH_CAROLINA_AVE("North Carolina Ave"), PENNSYLVANIA_AVE("Pennsylvania Ave"),
    PARK_PL("Park Pl"), BOARDWALK("Boardwalk");

    private String displayName;

    PropertyNames(String displayName){this.displayName = displayName;}

    @Override
    public String toString(){return displayName;}
}
