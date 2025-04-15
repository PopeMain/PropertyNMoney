package propertynmoney;

/**
 * Implementation of card that takes money from player
 * @author Nevin Fullerton
 */
public class SubMoneyCard implements Card {

    private final String cardText; // Text to display to user, showing money taken from them
    private final int moneyToRemove; // Amount of money player loses

    /**
     * Sets up text to display to player and money that will be taken from player
     * @param cardText Text to display to player
     * @param moneyToRemove Money player loses
     */
    SubMoneyCard(String cardText, int moneyToRemove) {
        this.cardText = cardText;
        this.moneyToRemove = moneyToRemove;
    }

    /**
     * Takes money from player
     * @param player Player that will be effected
     * @return Whether the player has a balance below 0
     */
    @Override
    public boolean playerEffect(Player player) {
        return player.subMoney(moneyToRemove);
    }

    /**
     * Displays how much money player lost, plus descriptive text
     * @return Text to display to user
     */
    @Override
    public String getCardText() {
        return cardText;
    }

}
