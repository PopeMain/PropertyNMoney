package propertynmoney;

/**
 * The Utility Class is an extension of the tile class that handles specifically the purchasable utilities
 * that can be rented. THe class has its name and buy value manually set. The class also stores the current Player Owner
 * @author Nevin Fullerton and Frank Pope
 */
public class Utility extends Tile {

    private final int buyValue; // Amount of money player has to pay to purchase property
    private final int rentValue; // Amount of money player has to pay in rent
    private final String name; // Name of utility
    private boolean mortgaged; // If utility is mortgaged
    private final int mortgageValue;
    private Player owner; // Owner of property, who to pay rent to


    /**
     * Sets up buy value of utility and name
     * @param value Buy value of utility
     * @param name Name of utility
     */
    public Utility(int value, String name) {
        super(TileTypes.UTILITY);
        this.buyValue = value;
        this.mortgageValue = value / 2;
        this.name = name;
        this.owner = null;
        this.rentValue = 20;
    }

    /**
     * Get name of the utility
     * @return Name of utility
     */
    public String getName() {
        return name;
    }

    /**
     * Makes utility into a sting by using its name as the string
     * @return Name of utility
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * Get buy value of utility, the amount player has to pay to purchase utility
     * @return Buy value of utility
     */
    public int getBuyValue() {return buyValue;}

    /**
     * Get rent value of utility, the amount of rent player has to pay if they land on an owned utility
     * @return Rent value of utility
     */
    public int getRentValue() {return rentValue;}

    /**
     * Get mortgage value of utility
     * @return Mortgage value of utility
     */
    public int getMortgageValue() {return mortgageValue;}

    /**
     * Checks if utility is mortgaged by owner, for checking if player has to pay rent of not
     * @return Is utility is mortgaged
     */
    public boolean isMortgaged() {return mortgaged;}

    /**
     * Sets whether the utility is mortgaged
     * @param mortgaged The new state of is mortgaged
     */
    public void setMortgaged(boolean mortgaged) {this.mortgaged = mortgaged;}

    /**
     * Checks if utility is owned
     * @return Is utility owned
     */
    public boolean isOwned() {return this.owner != null;}

    /**
     * Gets owner of utility
     * @return owner of utility
     */
    public Player getOwner() {return owner;}

    /**
     * Sets owner of utility
     * @param owner New owner
     */
    public void setOwner(Player owner) {this.owner = owner;}

}


