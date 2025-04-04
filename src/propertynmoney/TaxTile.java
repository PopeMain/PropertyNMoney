package propertynmoney;

public class TaxTile extends Tile {

    private int taxAmount;

    TaxTile(int taxAmount) {
        super(TileTypes.TAX);
        this.taxAmount = taxAmount;
    }

    public int getTaxAmount() {return taxAmount;}

}
