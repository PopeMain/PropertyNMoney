package propertynmoney;

public class MovementCard implements Card{

    private final String cardText;
    private final int locationIndex;

    MovementCard(String cardText, int locationIndex) {
        this.cardText = cardText;
        this.locationIndex = locationIndex;
    }

    @Override
    public void playerEffect(Player player) {
        player.moveSpecificPosition(locationIndex);
    }

    @Override
    public String getCardText() {
        return cardText;
    }


}
