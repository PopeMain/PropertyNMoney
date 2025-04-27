package propertynmoney;

/**
 * Tests the functionality of the Utility class by testing all methods of the class
 * @author Nevin Fullerton
 */
public class UtilityTesting {

    static boolean allTestsPassed = true; // Used to determine if all tests passed, if one test fails, set to false

    /**
     * Where functionality of Utility class is tested
     */
    public static void main(String[] args) {
        // Create a Utility
        Utility utility = new Utility(150, "Electric Company");

        stringIsEqual(utility.getName(), "Electric Company", "Initial Utility Name");
        stringIsEqual(utility.toString(), "Electric Company", "toString returns Utility Name");

        intIsEqual(utility.getBuyValue(), 150, "Utility Buy Value");
        intIsEqual(utility.getRentValue(), 20, "Utility Rent Value");
        intIsEqual(utility.getMortgageValue(), 75, "Utility Mortgage Value (half of buy value)");

        boolIsFalse(utility.isOwned(), "Utility should initially not be owned");
        boolIsFalse(utility.isMortgaged(), "Utility should initially not be mortgaged");

        // Test setting owner
        Player player = new Player(1500, "TestPlayer", 1);
        utility.setOwner(player);
        boolIsTrue(utility.isOwned(), "Utility should now be owned");
        playerIsEqual(utility.getOwner(), player, "Utility owner should be the set player");

        // Test setting mortgaged
        utility.setMortgaged(true);
        boolIsTrue(utility.isMortgaged(), "Utility should now be mortgaged");

        if (allTestsPassed) {
            System.out.println("All tests passed successfully!");
        } else {
            System.err.println("At least one test failed!");
        }
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
            System.err.println("FAILED: " + testName + " | Expected: true but was false");
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
            System.err.println("FAILED: " + testName + " | Expected: false but was true");
            allTestsPassed = false;
        } else {
            System.out.println("PASSED: " + testName);
        }
    }

    /**
     * Checks if both players are equal and output if test was successful or not
     * @param actual The result that was produced
     * @param expected The result that was expected
     * @param testName Name of the test that will be displayed to screen
     */
    private static void playerIsEqual(Player actual, Player expected, String testName) {
        if (actual != expected) {
            System.err.println("FAILED: " + testName + " | Expected objects to be the same");
            allTestsPassed = false;
        } else {
            System.out.println("PASSED: " + testName);
        }
    }
}
