package propertynmoney;

/**
 * Cards are drawn when a player lands on a chance or community chest tile, the card then affects the player that will be
 * implemented by classes that implement this interface
 * @author Nevin Fullerton
 */
public interface Card {

    /**
     * Will put an effect onto the player, examples including, giving or taking money, moving player to a position on
     * the game board, or going to jail
     * @param player Player that will be effected
     * @return a boolean used to determine if certain traits of the player need to be checked, like if they passed go or
     * are bankrupt, return false if nothing needs to be checked
     */
    public boolean playerEffect(Player player); //

    /**
     * What is displayed to user to communicate the effect on the player.
     * @return Text to display to user
     */
    public String getCardText();

}
