package propertynmoney;

public class GiveMoneyCard implements Card {

    private final String cardText;
    private final int moneyToGive;

    GiveMoneyCard(String cardText, int moneyToGive) {
        this.cardText = cardText;
        this.moneyToGive = moneyToGive;
    }

    @Override
    public void playerEffect(Player player) {
        player.addMoney(moneyToGive);
    }

    @Override
    public String getCardText() {
        return cardText;
    }
}
