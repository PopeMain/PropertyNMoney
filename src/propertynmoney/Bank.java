package propertynmoney;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;

public class Bank {
    private List<Property> properties;

    public Bank() {
        this.properties = new ArrayList<Property>();
        fillProperties();
    }

    private void fillProperties() {
        for(PropertyNames p : PropertyNames.values() ){
            properties.add(new Property(p));
        }
    }

    public List<Property> getProperties() {return properties;}
    //Get Property should be changes to get property by index(GameBoard Location), or name?
    public Property getProperty(int index){
        return properties.get(index%properties.size());
    }
    public Property getProperty(PropertyNames name){
        return properties.get(name.ordinal());
    }
}
