package propertynmoney;

import java.util.Random;

/**
 * Community tile pulls a card from the community chest card deck and applies the effect onto the player. Community deck
 * includes more money giving and taking  cards than the chance deck.
 * @author Nevin
 */
public class CommunityTile extends Tile{

    private final Card[] cards; // Card in deck that will be pull from
    private final Random cardRand = new Random(); // Used to determine which card will be pulled

    /**
     * Sets up card deck for community chest deck
     */
    CommunityTile() {
        super(TileTypes.COMMUNITYCHEST);
        cards = new Card[13];
        // Sets up card deck
        cards[0] = new SubMoneyCard("Pay Doctor fees of $50.", 50);
        cards[1] = new GiveMoneyCard("Receive $100 in dividends from the bank.", 100);
        cards[2] = new GiveMoneyCard("Bank Error in your favor. Collect $200", 200);
        cards[3] = new GiveMoneyCard("From sale of stock, you get $50.", 50);
        cards[4] = new GiveMoneyCard("Holiday Fund matures. Receive $100.", 100);
        cards[5] = new GiveMoneyCard("Income tax refund. Collect $20", 20);
        cards[6] = new GiveMoneyCard("Life insurance matures. Collect $100", 100);
        cards[7] = new SubMoneyCard("Hospital fees. Pay $50", 50);
        cards[8] = new SubMoneyCard("School Fees. Pay $50", 50);
        cards[9] = new GiveMoneyCard("Receive $25 consultancy fee.", 25);
        cards[10] = new GiveMoneyCard("You have won second prize in a beauty contest. Collect $10", 10);
        cards[11] = new GiveMoneyCard("You inherit $100.", 100);
        cards[12] = new JailCard();
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
