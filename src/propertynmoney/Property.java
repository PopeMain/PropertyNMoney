package propertynmoney;

import java.awt.*;

/**
 * The Property Class is an extension of the tile class that handles specifically the purchasable properties
 * that can be upgraded and rented. This class is an instance of the TileTypes Enum as PROPERTY and uses the PropertyNames
 * Enum to set the predetermined Properties by Name, buy Value and Color. The class also stores the current Player Owner
 * @author Frank Pope and Nevin Fullerton
 * */
public class Property extends Tile{
    private final int buyValue; // Cost to purchase property
    private final int[] rentValues; // An array with a length of five that holds the rent value of each house value from 0-5
    private int houseAmount; // Amount of houses on property
    private final int housePrice; // Price to purchase houses
    private final int mortgageAmount; // Amount player can mortgage property for
    private boolean mortgaged; // If property is mortgaged or not
    private final PropertyNames name; // Name of
    private final Color color;
    private Player owner;

    /**
     * Property Constructor, sets the buyValue, name, color, owner, and rentValue
     * @param name is a value from the PropertyNames Enum. The values are associated with the buy Val
     *             and the color.
     */
    public Property(PropertyNames name) {
        super(TileTypes.PROPERTY);
        this.buyValue = name.getBuyValue() ;
        this.name = name;
        this.color = name.getColor();
        this.owner = null;
        this.rentValues = name.getRentValues();
        this.houseAmount = 0;
        this.housePrice = name.getHousePrice();
        this.mortgageAmount = name.getMortgageValue();
        this.mortgaged = false;
    }

    /**
     * getName: uses the "toString" method set in the PropertyNames Enum
     * @return the name of the property.
     */
    public String getName() {
        return name.toString();
    }

    /**
     * getColorEnum get's the value from the PropertyColors Enum
     * @return the enum value of the property color.
     */
    public PropertyColors getColorEnum() {
        return name.getColorEnum();
    }

    /**
     * getColor takes the color value from the name.getColor() set at the time of construction
     * @return Java.awt.Color value for the property
     */
    public Color getColor() {
        return color;
    }

    /**
     * toString returns the getName() function. Redundancy?
     * @return getName() returns name.toString() from PropertyNames Enum
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * Gets the buy value
     * @return the buy value, rent value, is owned bool, and owner
     */
    public int getBuyValue() {return buyValue;}

    /**
     *
     * @param index The amount of houses the property has, to determine how high rent should be
     * @return The rent that has to be paid
     */
    public int getRentValue(int index) {return rentValues[index];}

    /**
     * Gets the amount of houses on the property
     * @return Amount of houses on property
     */
    public int getHouseAmount() {return houseAmount;}

    /**
     * Increase house amount by one
     */
    public void incrementHouseAmount() {houseAmount++;}

    /**
     * Decrease house amount by one
     */
    public void decrementHouseAmount() {houseAmount--;}

    /**
     * Gets the cost to construct a house on the property
     * @return The price of a house
     */
    public int getHouseCost() {return housePrice;}

    /**
     * Returns if the property is owned or not
     * @return If the house is owned
     */
    public boolean isOwned() {return this.owner != null;}

    /**
     * Gets owner of the property
     * @return Owner of the property
     */
    public Player getOwner() {return owner;}

    /**
     * Set owner of property
     * @param owner New owner of property
     */
    public void setOwner(Player owner) {this.owner = owner;}

    /**
     * Returns if the property is owned or not
     * @return if the property is owned or not
     */
    public boolean isMortgaged() {return mortgaged;}

    /**
     * Sets if the property is mortgaged or not
     * @param mortgaged New boolean if the property is mortgaged
     */
    public void setMortgaged(boolean mortgaged) {this.mortgaged = mortgaged;}

    /**
     * Gets the mortgage value of property
     * @return Mortgage value of property
     */
    public int getMortgageValue() {return mortgageAmount;}
}
