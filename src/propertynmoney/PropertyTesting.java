package propertynmoney;

/**
 * Tests the functionality of the Property class by testing all methods of the class
 * @author Nevin Fullerton
 */
public class PropertyTesting {

    static boolean allTestsPassed = true; // Used to determine if all tests passed, if one test fails, set to false

    /**
     * Where functionality of Property class is tested
     */
    public static void main(String[] args) {
        // Create a Property using a PropertyNames enum value
        Property property = new Property(PropertyNames.BOARDWALK);

        stringIsEqual(property.getName(), "Boardwalk", "Initial Property Name");
        stringIsEqual(property.toString(), "Boardwalk", "toString returns Property Name");

        boolIsFalse(property.isOwned(), "Property should initially not be owned");
        boolIsFalse(property.isMortgaged(), "Property should initially not be mortgaged");

        intIsEqual(property.getBuyValue(), 400, "Property Buy Value");
        intIsEqual(property.getHouseCost(), 200, "House Cost");

        // Rent values depend on PropertyNames rent array
        intIsEqual(property.getRentValue(0), 50, "Rent value with 0 houses");
        intIsEqual(property.getRentValue(5), 2000, "Rent value with hotel (5 houses)");

        // Test house amount increase and decrease
        intIsEqual(property.getHouseAmount(), 0, "Initial House Amount");

        property.incrementHouseAmount();
        intIsEqual(property.getHouseAmount(), 1, "House Amount after increment");

        property.decrementHouseAmount();
        intIsEqual(property.getHouseAmount(), 0, "House Amount after decrement");

        // Test mortgage setting
        property.setMortgaged(true);
        boolIsTrue(property.isMortgaged(), "Property mortgaged after setting true");

        // Test owner setting
        Player player = new Player(1500, "TestPlayer", 1);
        property.setOwner(player);
        boolIsTrue(property.isOwned(), "Property should now be owned");
        playerIsEqual(property.getOwner(), player, "Owner should match the player");

        // Test mortgage value
        intIsEqual(property.getMortgageValue(), 200, "Mortgage value for property");

        // Test color enum (based on PropertyNames)
        isNotNull(property.getColorEnum(), "Property Color Enum should not be null");
        isNotNull(property.getColor(), "Property Color (awt.Color) should not be null");

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

    /**
     * Check if object is not null
     * @param obj Object to check
     * @param testName Name of the test that will be displayed to screen
     */
    private static void isNotNull(Object obj, String testName) {
        if (obj == null) {
            System.err.println("FAILED: " + testName + " | Expected non-null object");
            allTestsPassed = false;
        } else {
            System.out.println("PASSED: " + testName);
        }
    }
}