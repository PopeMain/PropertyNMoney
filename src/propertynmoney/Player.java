package propertynmoney;
import propertynmoney.Property;

import java.util.ArrayList;
import java.util.List;
/*
Auth: Frank Pope
GroupID: The Capitalists
Notes: This is currently Bullshit Code that can be used to store info specific to each player.
 */


public class Player {
    int money;
    int position;
    String name;
    boolean inJail;
    boolean bankrupt;
    List<Property> properties;
    List<Utility> utilities;

    public Player(int money, String name) {
        /*
        The Player only stores the information for each indivdual payer.
         */
        this.money = money;
        this.name = name;
        this.position = 0;
        this.inJail = false;
        this.bankrupt = false;
        this.properties = new ArrayList<Property>();
        this.utilities =  new ArrayList<Utility>();
    }

    public int getMoney() {return money;}
    public String getName() {return name;}

    public void addMoney(int money) {this.money += money;}
    public void subMoney(int money) {this.money -= money;}

    public int getPosition() {return position;}
    public void movePosition(int rollValue){
        position = (position + rollValue) % 40;
    }

    public boolean isBankrupt() {return bankrupt;}
    public void setBankrupt(boolean bankrupt) {this.bankrupt = bankrupt;}

    public boolean isInJail() {return inJail;}
    public void setInJail(boolean inJail) {this.inJail = inJail;}

    public List<Property> getProperties() {return properties;}
    public void addProperty(Property property) {this.properties.add(property);}

    public List<Utility> getUtilities() {return utilities;}
    public void addUtility(Utility utility) {this.utilities.add(utility);}

}
