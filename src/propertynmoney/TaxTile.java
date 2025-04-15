package propertynmoney;

/**
 * Tile that when the player lands on it, they must pay the tax amount
 * @author Nevin
 */
public class TaxTile extends Tile {

    private final int taxAmount; // Amount player has to pay if they land on tile

    /**
     * Sets the tax amount of the tile, and sets its tile type to tax
     * @param taxAmount Tax value of the tile
     */
    TaxTile(int taxAmount) {
        super(TileTypes.TAX);
        this.taxAmount = taxAmount;
    }

    /**
     * Get the tax amount
     * @return tax amount
     */
    public int getTaxAmount() {return taxAmount;}

}
