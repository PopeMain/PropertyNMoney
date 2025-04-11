package propertynmoney;

import java.util.Random;

public class CommunityTile extends Tile{

    Card[] cards;
    private final Random cardRand = new Random();

    CommunityTile() {
        super(TileTypes.COMMUNITYCHEST);
        cards = new Card[16];
        cards[0] = new SubMoneyCard("Pay Doctor fees of $50.", 50);
        cards[1] = new GiveMoneyCard("Receive $100 in dividends from the bank.", 50);
        cards[2] = new GiveMoneyCard("Receive $100 in dividends from the bank.", 50);
        cards[3] = new GiveMoneyCard("Receive $100 in dividends from the bank.", 50);
        cards[4] = new SubMoneyCard("Pay Doctor fees of $50.", 50);
        cards[5] = new GiveMoneyCard("Receive $50.", 50);
        cards[6] = new GiveMoneyCard("Receive $50.", 50);
        cards[7] = new GiveMoneyCard("Receive $50.", 50);
        cards[8] = new GiveMoneyCard("Receive $50.", 50);
        cards[9] = new GiveMoneyCard("Receive $50.", 50);
        cards[10] = new GiveMoneyCard("Receive $50.", 50);
        cards[11] = new GiveMoneyCard("Receive $50.", 50);
        cards[12] = new GiveMoneyCard("Receive $50.", 50);
        cards[13] = new GiveMoneyCard("Receive $50.", 50);
        cards[14] = new GiveMoneyCard("Receive $50.", 50);
        cards[15] = new GiveMoneyCard("Receive $50.", 50);
    }

    /**
     * Draws a random card from the deck of 16 cards
     * @return A card which will puts its effect onto the player
     */
    public Card drawCard() {
        int cardIndex = cardRand.nextInt(cards.length);
        return cards[cardIndex];
    }
}
