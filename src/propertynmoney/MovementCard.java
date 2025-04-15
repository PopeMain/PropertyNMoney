package propertynmoney;

/**
 * Implementation of Card that moves the player to a specific property on the board
 * @author Nevin Fullerton
 */
public class MovementCard implements Card{

    private final String cardText; // Text that tells player what position they moved to
    private final int locationIndex; // Position player is moved to from 0-39

    /**
     * Sets up text of card and location player moves to
     * @param cardText Text to display to player
     * @param locationIndex Location player is moved to
     */
    MovementCard(String cardText, int locationIndex) {
        this.cardText = cardText;
        this.locationIndex = locationIndex;
    }

    /**
     * Moves player to a specific location on the board
     * @param player Player that will be effected
     * @return Whether the player passed go
     */
    @Override
    public boolean playerEffect(Player player) {
        return player.moveSpecificPosition(locationIndex);
    }

    /**
     * Text displayed to player, shows position/property they were moved to
     * @return Text to display to player
     */
    @Override
    public String getCardText() {
        return cardText;
    }


}
