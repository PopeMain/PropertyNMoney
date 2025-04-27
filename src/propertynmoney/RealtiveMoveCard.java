package propertynmoney;

/**
 * Move player either multiple spaces back or forward when drawn.
 * @author Nevin Fullerton
 */
public class RealtiveMoveCard implements Card{

    private final String cardText; // Text displayed to user
    private final boolean direction; // true for forwards, false for backwards
    private final int movementAmount; // Amount player will be moved by

    RealtiveMoveCard(String cardText, boolean direction, int movementAmount) {
        this.cardText = cardText;
        this.direction = direction;
        this.movementAmount = movementAmount;
    }

    @Override
    public boolean playerEffect(Player player) {
        boolean result = false; // If player passed go or not
        // Move player forward or backwards depending on direction
        if (direction) {
            result = player.movePosition(movementAmount);
        } else {
            if (player.getPosition() - movementAmount < 0) {
                result = player.moveSpecificPosition(40 + (player.getPosition() - movementAmount)); // Make sure position is not negative
            } else {
                result = player.moveSpecificPosition(player.getPosition() - movementAmount);
            }
        }

        return result; // If player passed go or not
    }

    @Override
    public String getCardText() {
        return cardText;
    }
}
