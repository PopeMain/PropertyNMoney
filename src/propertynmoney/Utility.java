package propertynmoney;

public class Utility extends Tile {
    int buyValue;
    int rentValue;
    private final String name;
    private Player owner;

    enum Houses {MORTGAGE, ZERO, ONE, TWO, THREE, FOUR, HOTEL}

    public Utility(int value, String name) {
        super(TileTypes.UTILITY);
        this.buyValue = value;
        this.name = name;
        this.owner = null;
        this.rentValue = 20;
    }

    public String getName() {
        return name.toString();
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


