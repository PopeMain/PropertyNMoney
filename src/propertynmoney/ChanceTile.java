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
        cards = new Card[16];
        // Set up cards in the deck
        cards[0] = new MovementCard("Advance to Go!", 0);
        cards[1] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[2] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[3] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[4] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[5] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[6] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[7] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[8] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[9] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[10] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[11] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[12] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[13] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[14] = new MovementCard("Advance to Illinois Ave.!", 24);
        cards[15] = new MovementCard("Advance to Illinois Ave.!", 24);
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
