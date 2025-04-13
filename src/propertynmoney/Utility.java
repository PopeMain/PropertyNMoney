package propertynmoney;

public class Utility extends Tile {
    private final int buyValue;
    private final int rentValue;
    private final String name;
    private boolean mortgaged;
    private Player owner;


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

    public int getRentValue() {return rentValue;}

    public boolean isMortgaged() {return mortgaged;}
    public void setMortgaged(boolean mortgaged) {this.mortgaged = mortgaged;}

    public boolean isOwned() {return this.owner != null;}

    public Player getOwner() {return owner;}
    public void setOwner(Player owner) {this.owner = owner;}

}


