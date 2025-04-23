package propertynmoney;

/**
 * Card that sends the player to jail if drawn.
 * @author Nevin Fullerton
 */
public class JailCard implements Card {

    /**
     * Return true, let GUI class handle putting player in jail
     * @param player Player that will be effected
     * @return true, indicating player should go to jail
     */
    @Override
    public boolean playerEffect(Player player) {
        return true;
    }

    /**
     * Display text saying player is going to jail.
     * @return text to display
     */
    @Override
    public String getCardText() {
        return "Go directly to jail. Do not pass Go, Do not collect $200.";
    }
}
