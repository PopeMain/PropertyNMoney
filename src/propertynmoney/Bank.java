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
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.*;

public class Bank {
    private List<Property> properties;

    public Bank() {
        this.properties = new ArrayList<Property>();
        fillProperties();
    }

    private void fillProperties() {
        int[] differentcolors = {2,3,3,3,3,3,3,2};
        int counter = 1;
        int index = 0;
        List<PropertyColors> colors = asList(PropertyColors.values());
        for(PropertyNames p : PropertyNames.values() ){
            if(counter > differentcolors[index]) {index++; counter = 1;}
            properties.add(new Property(100,p, colors.get(index)));
            counter++;
        }
    }

    public List<Property> getProperties() {return properties;}
    //Get Property should be changes to get property by index(GameBoard Location), or name?
    public Property getProperty(int index){
        return properties.get(index%properties.size());
    }
}
