package propertynmoney;

/**
 * Holds tile type information, so that when the player lands on a tile, the game knows what events to play depending on
 * the tile type the player landed on
 * @author Nevin Fullerton
 */
public class Tile {

    private final TileTypes tileType; // Tile type of palyer
    private final String title;

    /**
     * Sets up tile type of tile
     * @param tileType tile type of tile
     */
    Tile (TileTypes tileType, String tileName) {
        this.title = tileName;
        this.tileType = tileType;
    }

    /**
     * Get the tile type of tile
     * @return tile type of tile
     */
    public TileTypes getTileType() {return tileType;}

    /**
     * Get the string value for the tile's Name.
     * @return Title of the tile.
     */
    @Override
    public String toString() {return title;}

}
