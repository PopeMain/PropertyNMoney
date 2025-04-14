package propertynmoney;

/**
 * Implementation of card that gives player money
 * @author Nevin
 */
public class GiveMoneyCard implements Card {

    private final String cardText; // Text of card
    private final int moneyToGive; // Money given to player

    /**
     * Initializes card text and money amount of card
     * @param cardText Text displayed to player
     * @param moneyToGive Money player gains
     */
    GiveMoneyCard(String cardText, int moneyToGive) {
        this.cardText = cardText;
        this.moneyToGive = moneyToGive;
    }

    /**
     * Give player money
     * @param player Player that will be effected
     */
    @Override
    public void playerEffect(Player player) {
        player.addMoney(moneyToGive);
    }

    /**
     * Text of the card that will be displayed to player, shows money gained
     * @return Text to display to player
     */
    @Override
    public String getCardText() {
        return cardText;
    }
}
