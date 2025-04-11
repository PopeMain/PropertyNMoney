package propertynmoney;
import propertynmoney.Property;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
/*
Auth: Frank Pope
GroupID: The Capitalists
Notes: This is currently Bullshit Code that can be used to store info specific to each player.
 */


public class Player {
    private int money;
    private int position;
    private String name;
    private boolean inJail;
    private int turnsInJail;
    private boolean bankrupt;
    private final List<Property> properties;
    private final List<Utility> utilities;

    public Player(int money, String name) {
        /*
        The Player only stores the information for each indivdual payer.
         */
        this.money = money;
        this.name = name;
        this.position = 0;
        this.inJail = false;
        this.turnsInJail = 0;
        this.bankrupt = false;
        this.properties = new ArrayList<Property>();
        this.utilities =  new ArrayList<Utility>();
    }

    public int getMoney() {return money;}
    public String getName() {return name;}

    public void addMoney(int money) {this.money += money;}
    public boolean subMoney(int money) {
        this.money -= money;
        return this.money < 0;
    }

    public int getPosition() {return position;}
    public boolean movePosition(int rollValue){
        int lastPosition = position;
        position = (position + rollValue) % 40;

        return lastPosition + rollValue >= 40 && !inJail; // Check if player passed go, if true, give player $200
    }
    public boolean moveSpecificPosition(int position) {
        int lastPosition = this.position;
        this.position = position;

        if (position > lastPosition)
            return false;
        else
            return true;

    }

    public boolean isBankrupt() {return bankrupt;}
    public void setBankrupt(boolean bankrupt) {this.bankrupt = bankrupt;}

    public boolean isInJail() {return inJail;}
    public void setInJail(boolean inJail) {this.inJail = inJail;}

    public List<Property> getProperties() {return properties;}
    public void addProperty(Property property) {this.properties.add(property);}

    public List<Utility> getUtilities() {return utilities;}
    public void addUtility(Utility utility) {this.utilities.add(utility);}

    public int getTurnsInJail() {return turnsInJail;}
    public void setTurnsInJail(int turns) {this.turnsInJail = turns;}

}
