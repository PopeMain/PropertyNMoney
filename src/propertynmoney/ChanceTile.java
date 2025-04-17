package propertynmoney;

import java.util.Random;

/**
 * Chance tile pulls a card from the chance card deck and applies the effect onto the player. Chance deck includes more
 * movement cards than the community chest.
 * @author Nevin
 */
public class ChanceTile extends Tile{

    private final Card[] cards; // Deck cards
    private final Random cardRand = new Random(); // Used to pick random card

    /**
     * Sets up card deck for the chance tile
     */
    ChanceTile() {
        super(TileTypes.CHANCE);
        cards = new Card[14];
        // Set up cards in the deck
        cards[0] = new MovementCard("Advance to Go! Collect $200.", 0);
        cards[1] = new MovementCard("Advance to Illinois Ave.", 24);
        cards[2] = new MovementCard("Advance to St. Charles Place.", 11);
        cards[3] = new MovementCard("Take a walk on the Boardwalk. Advance token to Boardwalk.", 39);
        cards[4] = new RealtiveMoveCard("Go back 3 spaces.", false, 3);
        cards[5] = new RealtiveMoveCard("Here's a boost, go forward 3 spaces.", true, 3);
        cards[6] = new SubMoneyCard("Pay poor tax of $15", 15);
        cards[7] = new GiveMoneyCard("Bank pays you dividend of $50.", 50);
        cards[8] = new GiveMoneyCard("Your building loan matures. Collect $150", 150);
        cards[9] = new RealtiveMoveCard("Have a huge boost! Go forward 5 spaces.", true, 5);
        cards[10] = new MovementCard("Advance to New York Ave.", 19);
        cards[11] = new MovementCard("Advance to Pacific Ave.", 31);
        cards[12] = new RealtiveMoveCard("Hold up! You forgot your luggage, go back 5 spaces.", false, 5);        cards[13] = new JailCard();
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
