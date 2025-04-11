package propertynmoney;

public class SubMoneyCard implements Card {
    private final String cardText;
    private final int moneyToRemove;

    SubMoneyCard(String cardText, int moneyToRemove) {
        this.cardText = cardText;
        this.moneyToRemove = moneyToRemove;
    }

    @Override
    public void playerEffect(Player player) {
        player.subMoney(moneyToRemove);
    }

    @Override
    public String getCardText() {
        return cardText;
    }

}
