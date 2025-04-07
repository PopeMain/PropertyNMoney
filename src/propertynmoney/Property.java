package propertynmoney;

import java.awt.*;

public class Property extends Tile{
    int buyValue;
    int rentValue;
    private final PropertyNames name;
    private final Color color;
    private Player owner;

    enum Houses {MORTGAGE, ZERO, ONE, TWO, THREE, FOUR, HOTEL}

    public Property(PropertyNames name) {
        super(TileTypes.PROPERTY);
        this.buyValue = name.getBuyValue() ;
        this.name = name;
        this.color = name.getColor();
        this.owner = null;
        this.rentValue = 20;
    }

    public String getName() {
        return name.toString();
    }

    public PropertyColors getColorEnum() {
        return name.getColorEnum();
    }

    public java.awt.Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return getName();
    }

    public int getBuyValue() {return buyValue;}
    public void setBuyValue(int value) {buyValue = value;}

    public int getRentValue() {return rentValue;}
    public void setRentValue(int value) {rentValue = value;}

    public boolean isOwned() {return this.owner != null;}

    public Player getOwner() {return owner;}
    public void setOwner(Player owner) {this.owner = owner;}


}
