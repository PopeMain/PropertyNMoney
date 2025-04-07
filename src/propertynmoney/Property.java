package propertynmoney;

import java.awt.*;

/**
 * @author Frank
 * @author Nevin
 * @class Property
 * <p>
 * The Property Class is an extension of the tile class that handles specifically the purchasable properties
 * that can be upgraded and rented. This class is an instance of the TileTypes Enum as PROPERTY and uses the PropertyNames
 * Enum to set the predetermined Properties by Name, buy Value and Color. The class also stores the current Player Owner
 * </p>
 * Key Methods:<p>
 * -getName: returns the string value for the property name <p>
 * -getColorEnum: returns the Enum value from the PropertyColor Enum <p>
 * -getColor: returns the Java.awt.Color value of the property <p>
 * -toString: uses the getName method <p>
 * -getBuyValue: returns the buyValue as an integer <p>
 * -setBuyValue: Shouldn't exist it's a value set in the PropertyNames Enum <p>
 * -getRentValue: I guess the rent is only 20$. The original function has been removed <p>
 * -setRentValue: an unused function to magically change the value of the rent TBD?? <p>
 * -isOwned: returns false if the owner value is null and true if the value has been replaced by a player. <p>
 * -getOwner: returns the player in owner <p>
 * -setOwner: sets the owner to specified player. <p>
 */
public class Property extends Tile{
    int buyValue;
    int rentValue;
    private final PropertyNames name;
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
        this.rentValue = 20;
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
    public java.awt.Color getColor() {
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
     * Getters and Setters for the other Property Values
     * @return the buy value, rent value, is owned bool, and owner
     */
    public int getBuyValue() {return buyValue;}
    public void setBuyValue(int value) {buyValue = value;}

    public int getRentValue() {return rentValue;}
    public void setRentValue(int value) {rentValue = value;}

    public boolean isOwned() {return this.owner != null;}

    public Player getOwner() {return owner;}
    public void setOwner(Player owner) {this.owner = owner;}


}
