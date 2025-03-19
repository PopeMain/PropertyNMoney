package propertynmoney;
/*
Author: Frank Pope
Group ID: The Capitalists
Comments: The Bank Class is the economic controller for the board Game.
Holds all the properties when the game starts. If there are any feature updates to the
rules of the game that change the way the economy works it will be put here.
 */
import propertynmoney.Property;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Property> properties;

    public Bank(List<Property> properties) {
        this.properties = new ArrayList<Property>();
        fillProperties();
    }

    private void fillProperties() {
        properties.add( new Property(100,Property.Name.MEDITERRANEAN_AVE, Property.Color.BROWN));
    }

    public List<Property> getProperties() {return properties;}
    //Get Property should be changes to get property by index(GameBoard Location), or name?
    public Property getProperty(){
        return properties.get(0);
    }
}
