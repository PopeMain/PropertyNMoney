package propertynmoney;

/**
 * Cards are drawn when a player lands on a chance or community chest tile, the card then affects the player by either
 * giving or taking money from them, moving them to another location on the board, or sending them to jail.
 * @author Nevin Fullerton
 */

public interface Card {

    public void playerEffect(Player player); // The effect on the player
    public String getCardText(); // What is displayed to user to communicate the effect on the player.

}
