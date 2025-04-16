package propertynmoney;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the information of the player, which includes their money, position, name, if they are in jail, amount of turns
 * in jail, if they are bankrupt, and their properties and utilities.
 * @author Frank Pope and Nevin Fullerton
 */
public class Player {
    private int money; // Balance of player, used to buy properties, if hits 0, delcared bankruptcy
    private int position; // Position on game board from 0-39
    private String name; // Name of player
    private boolean inJail; // Used to determine which button frame to paint when it's their turn
    private int turnsInJail; // Prevents player from staying in jail for too long
    private boolean bankrupt; // Out of game
    private final List<Property> properties; // Properties which player can upgrade and collect rent from
    private final List<Utility> utilities; // Utilities which player can collect rent from
    private final int iconIndex;

    /**
     * Constructor of the Player class, initializes variables to default state, with name and money being variable to each
     * player
     * @param money Starting money of player
     * @param name Name of player
     */
    public Player(int money, String name, int iconIndex) {
        // Initializes variables to default state
        this.money = money;
        this.name = name;
        this.position = 0;
        this.inJail = false;
        this.turnsInJail = 0;
        this.bankrupt = false;
        this.properties = new ArrayList<Property>();
        this.utilities =  new ArrayList<Utility>();
        this.iconIndex = iconIndex;
    }

    /**
     * Get current balance from player
     * @return Balance of player
     */
    public int getMoney() {return money;}

    /**
     * Get name of player
     * @return Name of player
     */
    public String getName() {return name;}

    /**
     * Add money to player's balance
     * @param money Money to add to balance
     */
    public void addMoney(int money) {this.money += money;}

    /**
     * Subtract money from player's account, and check if balance is zero to determine if player is bankrupt
     * @param money Money to take from account
     * @return Whether player's account is below 0
     */
    public boolean subMoney(int money) {
        this.money -= money;
        return this.money < 0;
    }

    /**
     * Get position of player on game board
     * @return Position of player
     */
    public int getPosition() {return position;}

    /**
     * Move player by taking the roll value and adding it to current position, if player passes go, return true
     * @param rollValue Amount player moves by
     * @return Whether player has passed or landed on go
     */
    public boolean movePosition(int rollValue){
        int lastPosition = position; // Used to see if player passed go
        position = (position + rollValue) % 40; // Ensure player only can be at a position from 0-39, inside the board

        return lastPosition + rollValue >= 40 && !inJail; // Check if player passed go, if true, give player $200
    }

    /**
     * Move player to a specific position on the board, returns whether the player has passed go
     * @param position Position on the board from 0-39 the player is moved to
     * @return Whether the player has passed go
     */
    public boolean moveSpecificPosition(int position) {
        int lastPosition = this.position; // Used to check if player has passed go
        this.position = position;

        // If position is less than last position, than player must have passed go because player only goes clockwise around board
        return position <= lastPosition;

    }

    /**
     * Gets if the player is bankrupt or not
     * @return Is the player bankrupt
     */
    public boolean isBankrupt() {return bankrupt;}

    /**
     * Sets whether the player is bankrupt
     * @param bankrupt New status on bankruptcy
     */
    public void setBankrupt(boolean bankrupt) {this.bankrupt = bankrupt;}

    /**
     * Gets if the player is in jail or not
     * @return Is the player in jail
     */
    public boolean isInJail() {return inJail;}

    /**
     * Sets whether the player is in jail or not
     * @param inJail  New status on in jail
     */
    public void setInJail(boolean inJail) {this.inJail = inJail;}

    /**
     * Gets a list of the properties the player owns
     * @return List of properties the player owns
     */
    public List<Property> getProperties() {return properties;}

    /**
     * Adds a property to the player
     * @param property New property
     */
    public void addProperty(Property property) {this.properties.add(property);}

    /**
     * Returns list of the utilities the player owns
     * @return List of utilities the player owns
     */
    public List<Utility> getUtilities() {return utilities;}

    /**
     * Add a utility to the player
     * @param utility New utility
     */
    public void addUtility(Utility utility) {this.utilities.add(utility);}

    /**
     * Get amount of turns the player has been in jail
     * @return Turns the player has been in jail
     */
    public int getTurnsInJail() {return turnsInJail;}

    /**
     * Set turns in jail to a new int
     * @param turns New turn amount
     */
    public void setTurnsInJail(int turns) {this.turnsInJail = turns;}

    /**
     * Get's the index value for the player's choosen Icon image.
     * @return iconIndex an integer for the location of the icon for player.
     */
    public int getIconIndex() {return iconIndex;}
}
