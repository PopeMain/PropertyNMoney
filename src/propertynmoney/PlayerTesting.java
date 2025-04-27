package propertynmoney;

/**
 * Tests the functionality of the player class by testing all methods of the class
 * @author Nevin Fullerton
 */
public class PlayerTesting {

    private static boolean allTestsPassed = true; // Used to determine if all tests passed, if one test fails, set to false

    /**
     * Where functionality of Player class is tested
     */
    public static void main(String[] args) {
        Player player = new Player(1500, "TestPlayer", 1);

        intIsEqual(player.getMoney(), 1500, "Initial Money");
        stringIsEqual(player.getName(), "TestPlayer", "Initial Name");
        intIsEqual(player.getPosition(), 0, "Initial Position");
        boolIsFalse(player.isInJail(), "Initial Jail Status");
        intIsEqual(player.getTurnsInJail(), 0, "Initial Turns in Jail");
        boolIsFalse(player.isBankrupt(), "Initial Bankruptcy Status");
        intIsEqual(player.getProperties().size(), 0, "Initial Properties");
        intIsEqual(player.getUtilities().size(), 0, "Initial Utilities");
        intIsEqual(player.getIconIndex(), 1, "Initial Icon Index");

        player.addMoney(500);
        intIsEqual(player.getMoney(), 2000, "Money after adding 500");

        boolean isNegative = player.subMoney(2100);
        intIsEqual(player.getMoney(), -100, "Money after subtracting 2100");
        boolIsTrue(isNegative, "SubMoney returned negative");

        boolean passedGo = player.movePosition(10);
        intIsEqual(player.getPosition(), 10, "Position after moving 10");
        boolIsFalse(passedGo, "Did not Passed Go after moving 10");

        player.moveSpecificPosition(38);
        passedGo = player.movePosition(5);
        intIsEqual(player.getPosition(), 3, "Position after passing Go");
        boolIsTrue(passedGo, "Passed Go after moving 5 from 38");

        passedGo = player.moveSpecificPosition(2);
        intIsEqual(player.getPosition(), 2, "Position after moving 2");
        boolIsTrue(passedGo, "Passed Go after moving 2 from 2");

        player.setBankrupt(true);
        boolIsTrue(player.isBankrupt(), "Bankrupt after setting true");

        player.setInJail(true);
        boolIsTrue(player.isInJail(), "In Jail after setting true");

        player.setTurnsInJail(2);
        intIsEqual(player.getTurnsInJail(), 2, "Turns in Jail after setting 2");

        // Testing Property and Utility
        Property prop = new Property(PropertyNames.BOARDWALK);
        Utility util = new Utility(1500, "Railroad 1");
        player.addProperty(prop);
        player.addUtility(util);

        boolIsTrue(player.getProperties().contains(prop), "Properties contains added property");
        boolIsTrue(player.getUtilities().contains(util), "Utilities contains added utility");

        stringIsEqual(player.toString(), "TestPlayer", "toString output");

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

