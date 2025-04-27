package propertynmoney;

/**
 * Tests the functionality of all classes that implement the Card interface
 * by verifying their effects on Player objects
 * @author Nevin Fullerton
 */
public class CardTesting {

    private static boolean allTestsPassed = true; // Used to determine if all tests passed, if one test fails, set to false

    /**
     * Where functionality of all Card implementations is tested
     */
    public static void main(String[] args) {
        System.out.println("Starting Card implementation tests...");

        // Test GiveMoneyCard
        testGiveMoneyCard();

        // Test SubMoneyCard
        testSubMoneyCard();

        // Test MovementCard
        testMovementCard();

        // Test RelativeMoveCard
        testRelativeMoveCard();

        // Test JailCard
        testJailCard();

        if (allTestsPassed) {
            System.out.println("All Card implementation tests passed successfully!");
        } else {
            System.err.println("At least one Card implementation test failed!");
        }
    }

    /**
     * Test the GiveMoneyCard implementation
     */
    private static void testGiveMoneyCard() {
        System.out.println("\n===== Testing GiveMoneyCard =====");

        Player player = new Player(1500, "TestPlayer", 1);
        GiveMoneyCard card = new GiveMoneyCard("Bank error in your favor, collect $200", 200);

        stringIsEqual(card.getCardText(), "Bank error in your favor, collect $200", "GiveMoneyCard text");

        boolean result = card.playerEffect(player);
        boolIsFalse(result, "GiveMoneyCard playerEffect return value");
        intIsEqual(player.getMoney(), 1700, "Player money after GiveMoneyCard effect");
    }

    /**
     * Test the SubMoneyCard implementation
     */
    private static void testSubMoneyCard() {
        System.out.println("\n===== Testing SubMoneyCard =====");

        // Test regular money subtraction (non-bankruptcy case)
        Player player1 = new Player(1500, "TestPlayer1", 1);
        SubMoneyCard card1 = new SubMoneyCard("Pay school tax of $150", 150);

        stringIsEqual(card1.getCardText(), "Pay school tax of $150", "SubMoneyCard text");

        boolean result1 = card1.playerEffect(player1);
        boolIsFalse(result1, "SubMoneyCard playerEffect return value (non-bankruptcy)");
        intIsEqual(player1.getMoney(), 1350, "Player money after SubMoneyCard effect (non-bankruptcy)");

        // Test bankruptcy case
        Player player2 = new Player(100, "TestPlayer2", 2);
        SubMoneyCard card2 = new SubMoneyCard("Pay hospital fees of $200", 200);

        boolean result2 = card2.playerEffect(player2);
        boolIsTrue(result2, "SubMoneyCard playerEffect return value (bankruptcy)");
        intIsEqual(player2.getMoney(), -100, "Player money after SubMoneyCard effect (bankruptcy)");
    }

    /**
     * Test the MovementCard implementation
     */
    private static void testMovementCard() {
        System.out.println("\n===== Testing MovementCard =====");

        // Test moving forward (not passing Go)
        Player player1 = new Player(1500, "TestPlayer1", 1);
        MovementCard card1 = new MovementCard("Advance to Boardwalk", 39);

        stringIsEqual(card1.getCardText(), "Advance to Boardwalk", "MovementCard text");

        boolean result1 = card1.playerEffect(player1);
        boolIsFalse(result1, "MovementCard playerEffect return value (forward without passing Go)");
        intIsEqual(player1.getPosition(), 39, "Player position after MovementCard effect (forward)");

        // Test moving backward (passing Go)
        Player player2 = new Player(1500, "TestPlayer2", 2);
        player2.moveSpecificPosition(35);
        MovementCard card2 = new MovementCard("Go back to Go", 0);

        boolean result2 = card2.playerEffect(player2);
        boolIsTrue(result2, "MovementCard playerEffect return value (passing Go)");
        intIsEqual(player2.getPosition(), 0, "Player position after MovementCard effect (passing Go)");
    }

    /**
     * Test the RelativeMoveCard implementation
     */
    private static void testRelativeMoveCard() {
        System.out.println("\n===== Testing RelativeMoveCard =====");

        // Test moving forward
        Player player1 = new Player(1500, "TestPlayer1", 1);
        player1.moveSpecificPosition(10);
        RealtiveMoveCard cardForward = new RealtiveMoveCard("Move forward 3 spaces", true, 3);

        stringIsEqual(cardForward.getCardText(), "Move forward 3 spaces", "RelativeMoveCard text (forward)");

        boolean resultForward = cardForward.playerEffect(player1);
        boolIsFalse(resultForward, "RelativeMoveCard playerEffect return value (forward)");
        intIsEqual(player1.getPosition(), 13, "Player position after RelativeMoveCard effect (forward)");

        // Test moving backward
        Player player2 = new Player(1500, "TestPlayer2", 2);
        player2.moveSpecificPosition(10);
        RealtiveMoveCard cardBackward = new RealtiveMoveCard("Move back 3 spaces", false, 3);

        stringIsEqual(cardBackward.getCardText(), "Move back 3 spaces", "RelativeMoveCard text (backward)");

        boolean resultBackward = cardBackward.playerEffect(player2);
        boolIsTrue(resultBackward, "RelativeMoveCard playerEffect return value (backward)");
        intIsEqual(player2.getPosition(), 7, "Player position after RelativeMoveCard effect (backward)");

        // Test moving backward past Go
        Player player3 = new Player(1500, "TestPlayer3", 3);
        player3.moveSpecificPosition(2);
        RealtiveMoveCard cardPastGo = new RealtiveMoveCard("Move back 5 spaces", false, 5);

        boolean resultPastGo = cardPastGo.playerEffect(player3);
        boolIsFalse(resultPastGo, "RelativeMoveCard playerEffect return value (past Go)");
        intIsEqual(player3.getPosition(), 37, "Player position after RelativeMoveCard effect (past Go)");
    }

    /**
     * Test the JailCard implementation
     */
    private static void testJailCard() {
        System.out.println("\n===== Testing JailCard =====");

        Player player = new Player(1500, "TestPlayer", 1);
        JailCard card = new JailCard();

        stringIsEqual(card.getCardText(), "Go directly to jail. Do not pass Go, Do not collect $200.", "JailCard text");

        boolean result = card.playerEffect(player);
        boolIsTrue(result, "JailCard playerEffect return value");

        // Note: The JailCard doesn't directly modify the player's inJail status
        // as that's handled by the GUI class
        boolIsFalse(player.isInJail(), "Player jail status after JailCard effect (still false)");
    }

    /**
     * Checks if both ints are equal and outputs if test was successful or not
     * @param actual The result that was produced
     * @param expected The result the was expected
     * @param testName Name of the test that will be displayed to screen
     */
    private static void intIsEqual(int actual, int expected, String testName) {
        if (actual != expected) {
            System.err.println("FAILED: " + testName + " | Expected: " + expected + ", but got: " + actual);
            allTestsPassed = false;
        } else {
            System.out.println("PASSED: " + testName);
        }
    }

    /**
     * Checks if both strings are equal and outputs if test was successful or not
     * @param actual The result that was produced
     * @param expected The result the was expected
     * @param testName Name of the test that will be displayed to screen
     */
    private static void stringIsEqual(String actual, String expected, String testName) {
        if (!actual.equals(expected)) {
            System.err.println("FAILED: " + testName + " | Expected: " + expected + ", but got: " + actual);
            allTestsPassed = false;
        } else {
            System.out.println("PASSED: " + testName);
        }
    }

    /**
     * Checks if bool is true and outputs if test was successful or not
     * @param actual The result that was produced
     * @param testName Name of the test that will be displayed to screen
     */
    private static void boolIsTrue(boolean actual, String testName) {
        if (!actual) {
            System.err.println("FAILED: " + testName + " | Expected true but was false");
            allTestsPassed = false;
        } else {
            System.out.println("PASSED: " + testName);
        }
    }

    /**
     * Checks if bool is false and outputs if test was successful or not
     * @param actual The result that was produced
     * @param testName Name of the test that will be displayed to screen
     */
    private static void boolIsFalse(boolean actual, String testName) {
        if (actual) {
            System.err.println("FAILED: " + testName + " | Expected: false");
            allTestsPassed = false;
        } else {
            System.out.println("PASSED: " + testName);
        }
    }
}