package propertynmoney;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

/**
 * Constructs and displays GUI, and manages the game by moving players, handling button inputs, taking and giving money
 * determining bankruptcy, setting up game, determining victor, ending gaming, and restarting game.
 * @author Nevin Fullerton and Frank Pope
 */
public class GUI extends JPanel {
    private final JPanel boardPanel; // Holds game board and shows player locations on board
    private final JPanel sideBarPanel; // Shows player information and allows user to select their properties
    private final JPanel actionPanel; // Shows the actions the player is able to take depending on the state they are in
    private JList<Object> propertiesList; // Shows the current player's properties, allowing them to buy/sell houses and mortgage them

    private final Player[] players; // Holds the players in the game
    private int currentPlayer; // The index of the current player in the array of players
    private int amountOfPlayers; // Amount of players - 1, used to know where end of players array is
    private boolean diceRolled; // If dice have been rolled this turn
    private int doubleAmount; // Used to detect if player is speeding to send them to jail

    private Tile[] tiles; // When the player moves, the position will be used as an index to determine what should happen to player

    private int freeParkingMoney; // Used to store money from player landing on tax tiles, then if a player lands on free parking tile, they get this money

    // Panels that hold the icons of each player, to show their location on the board
    private final JPanel northPanel;
    private final JPanel southPanel;
    private final JPanel eastPanel;
    private final JPanel westPanel;

    private final Random diceRand; // Random number generation for dice rolls
    private final int IMAGE_WIDTH; // Used to paint player names to align them with the tiles on the game board
    private final int IMAGE_HEIGHT;

    private final Map<PropertyColors, Integer> houseAmounts = new HashMap<PropertyColors, Integer>(); // Used to see if player owns all properties of one color for buying and selling houses

    /**
     * Constructor that sets up the GUI and sets up important game variables like amount of players, player names, positions
     */
    GUI() {
        this.setSize(1000, 800);
        this.setLayout(new BorderLayout());

        diceRand = new Random();
        final ImageIcon gameBoard = new ImageIcon("src/GameBoard_Resized.png");

        players = new Player[8];
        players[0] = new Player(1500, "Nevin"); // TODO Testing ** Remove when done
        players[1] = new Player(1500, "Frank");
        players[2] = new Player(1500, "Nathan");
        players[0].moveSpecificPosition(0);
        players[1].moveSpecificPosition(0);
        players[2].moveSpecificPosition(0);

        currentPlayer = 0;
        amountOfPlayers = 2;

        freeParkingMoney = 0;

        // The amount of houses per property color, to know if player owns all properties of one color for buying and selling houses
        houseAmounts.put(PropertyColors.BROWN, 2);
        houseAmounts.put(PropertyColors.CYAN, 3);
        houseAmounts.put(PropertyColors.MAGENTA, 3);
        houseAmounts.put(PropertyColors.ORANGE, 3);
        houseAmounts.put(PropertyColors.RED, 3);
        houseAmounts.put(PropertyColors.YELLOW, 3);
        houseAmounts.put(PropertyColors.GREEN, 3);
        houseAmounts.put(PropertyColors.BLUE, 2);

        IMAGE_WIDTH = gameBoard.getIconWidth(); // Width of game board, used to align player names to tiles on board
        IMAGE_HEIGHT = gameBoard.getIconHeight(); // Height of game board, used to align player names to tiles on board

        // Holders used to alter position of normal panels
        JPanel northPanelHolder = new JPanel();
        JPanel southPanelHolder = new JPanel();
        JPanel eastPanelHolder = new JPanel();
        JPanel westPanelHolder = new JPanel();

        // Construct player holders, to ensure player names are aligned with board
        northPanel = new JPanel();
        northPanel.setLayout(null);
        northPanel.setPreferredSize(new Dimension(IMAGE_WIDTH + 300, 60));

        northPanelHolder.setLayout(new BorderLayout());
        northPanelHolder.add(northPanel, BorderLayout.WEST);

        southPanel = new JPanel();
        southPanel.setLayout(null);
        southPanel.setPreferredSize(new Dimension(IMAGE_WIDTH + 300, 60));

        southPanelHolder.setLayout(new BorderLayout());
        southPanelHolder.add(southPanel, BorderLayout.EAST);

        eastPanel = new JPanel();
        eastPanel.setLayout(null);
        eastPanel.setPreferredSize(new Dimension(80, IMAGE_HEIGHT - 10));

        eastPanelHolder.setLayout(new BorderLayout());
        eastPanelHolder.add(eastPanel, BorderLayout.NORTH);

        westPanel = new JPanel();
        westPanel.setLayout(null);
        westPanel.setPreferredSize(new Dimension(80, IMAGE_HEIGHT - 10));

        westPanelHolder.setLayout(new BorderLayout());
        westPanelHolder.add(westPanel, BorderLayout.SOUTH);

        // Construct Board Panel
        boardPanel = new JPanel();
        JLabel boardLabel = new JLabel();
        boardPanel.setLayout(new BorderLayout());
        boardLabel.setIcon(gameBoard);
        boardLabel.setBounds(0, 0, gameBoard.getIconWidth(), gameBoard.getIconHeight());
        System.out.println(gameBoard.getIconHeight());
        boardLabel.setVisible(true);
        boardPanel.add(boardLabel, BorderLayout.CENTER);

        // Position panels where player names are drawn to be aligned with board
        boardPanel.add(northPanelHolder, BorderLayout.NORTH);
        boardPanel.add(southPanelHolder, BorderLayout.SOUTH);
        boardPanel.add(eastPanelHolder, BorderLayout.EAST);
        boardPanel.add(westPanelHolder, BorderLayout.WEST);

        // Set up tiles so the game knows what to do when player moves onto a tile
        setUpTiles();
        paintBoardPanel();

        this.add(boardPanel, BorderLayout.CENTER);

        // Construct Side Bar Panel
        sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        sideBarPanel.setPreferredSize(new Dimension(165, 900));
        paintPlayerSidePanel();

        this.add(sideBarPanel, BorderLayout.EAST);

        // Construct Action Panel
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
        paintStandardButtonFrame();

        this.add(actionPanel, BorderLayout.SOUTH);

        this.setSize(950, 800);
        this.setVisible(true);
    }

    /**
     * Rolls two dice and moves the player by the sum of the dice. If the dice have equal value, the player can roll again,
     * but if they roll three doubles in a row, they go to jail for speeding.
     */
    private void rollDice() {
        // Roll the two dice
        int dice1 = diceRand.nextInt(1,7);
        int dice2 = diceRand.nextInt(1,7);

        // Move player and check if they passed go
        boolean passedGo = players[currentPlayer].movePosition(dice1 + dice2);

        // Show player what dice they rolled
        JOptionPane.showMessageDialog(this, "You rolled a " + dice1 + ", and a " + dice2);

        // Give player $200  if they pass go
        if (passedGo)
            passedGo();

        // Allow player to roll again if they roll doubles (both dice have same value), or put them in jail
        // if they roll three doubles in a row
        if (dice1 == dice2) {
            if (doubleAmount == 3) {
                JOptionPane.showMessageDialog(this, "Oops! You were caught speeding! You must go to jail.");
                goToJail(players[currentPlayer]);
            } else {
                JOptionPane.showMessageDialog(this, "You rolled doubles! You get to roll again after you land on tile.");
                doubleAmount++;
            }
        } else {
            diceRolled = true; // Prevent player from rolling in the same turn
        }

        paintBoardPanel();
        paintPlayerSidePanel();
        determineMovementResult();

    }

    /**
     * Rolls two dice, if they are of equal value, the player gets out of jail and moves by the sum of the dice, else
     * player remains in jail.
     */
    private void rollJailDice() {
        // Roll two dice
        int dice1 = diceRand.nextInt(1,6);
        int dice2 = diceRand.nextInt(1,6);
        diceRolled = true; // Prevent player from rolling again in same turn

        JOptionPane.showMessageDialog(this, "You rolled a " + dice1 + ", and a " + dice2);

        // Check if two dice share the same value, if true get player out of jail and move them by dice amount
        if (dice1 == dice2) {
            JOptionPane.showMessageDialog(this, "You rolled a double! You get out of jail and move by " + (dice1 + dice2));
            players[currentPlayer].movePosition(dice1 + dice2);
            paintBoardPanel();
            paintPlayerSidePanel();
            paintStandardButtonFrame();
            determineMovementResult();
        }

    }

    /**
     * Give player $200 and inform player that they have passed go
     */
    private void passedGo() {
        JOptionPane.showMessageDialog(this, "You passed go! Collect $200.");
        players[currentPlayer].addMoney(200);
    }

    /**
     * Will end the turn of the current player and pass onto the next eligible player, making them the
     * current player. The side panel is then updated to show the new current player's money, properties, name,
     * and position.
     */
    private void endTurn() {
        // Increase player in jail turn amount to prevent player from being in jail forever
        if (players[currentPlayer].isInJail()) {
            players[currentPlayer].setTurnsInJail(players[currentPlayer].getTurnsInJail() + 1);
        }

        doubleAmount = 0; // Reset double amount for next player

        // Find next eligible player by linearly searching through players array
        boolean nextPlayerFound = false;
        while (!nextPlayerFound) {
            // Go to array start if reached end of array
            if (currentPlayer == amountOfPlayers) {
                currentPlayer = 0;
            } else {
                currentPlayer++;
            }

            // Ensure player is not bankrupt before going assigning nextPlayerFound
            if (!players[currentPlayer].isBankrupt()) {
                nextPlayerFound = true;
            }
        }

        // Paint button frame depending on which state they are in
        if (players[currentPlayer].isInJail()) {
            paintJailButtonFrame();
        } else {
            paintStandardButtonFrame();
        }

        diceRolled = false; // Reset diceRolled to allow the next player to roll
        paintPlayerSidePanel();
    }

    /**
     * Will determine which position the player is on, and will react accordingly to what type of tile the player is on.
     */
    private void determineMovementResult() {
        Player player = players[currentPlayer]; // Current player that just moved
        Tile tile = tiles[player.getPosition()]; // Tile the player just landed on

        // Find type of tile player landed on, covert tile to respective child class, and run method related to that tile type
        if (tile.getTileType() == TileTypes.PROPERTY) {
            Property property = (Property) tile;
            onProperty(property, player);
        } else if (tile.getTileType() == TileTypes.UTILITY) {
            Utility utility = (Utility) tile;
            onUtility(utility, player);
        } else if (tile.getTileType() == TileTypes.TAX) {
            TaxTile tax = (TaxTile) tile;
            onTax(tax, player);
        } else if (tile.getTileType() == TileTypes.CHANCE) {
            ChanceTile chance = (ChanceTile) tile;
            onChance(chance, player);
        } else if (tile.getTileType() == TileTypes.COMMUNITYCHEST) {
            CommunityTile community = (CommunityTile) tile;
            onCommunityChest(community, player);
        } else if (tile.getTileType() == TileTypes.GOTOJAIL) {
            goToJail(player);
        } else if (tile.getTileType() == TileTypes.PARKING) {
            onParking(player);
        }

        paintPlayerSidePanel();
        paintBoardPanel();
    }

    /**
     * The events that happen when the player lands on a property tile, either the option to buy the property or paying rent to the
     * owner.
     * @param property The property tile the player landed on
     * @param player The player that landed on the tile
     */
    private void onProperty(Property property, Player player) {
        // Make player pay rent to owner if they land on owned unmortgaged property
        if (property.isOwned()) {
            // Don't pay rent if property is mortgaged
            if (property.isMortgaged()) {
                JOptionPane.showMessageDialog(this, "You landed on an owned property, but it is mortgaged. So you do not have to pay rent");
            } else {
                JOptionPane.showMessageDialog(this, "You must pay " + property.getRentValue(property.getHouseAmount()) + " to " + property.getOwner().getName() + " in order stay here.");
                boolean bankrupt = player.subMoney(property.getRentValue(property.getHouseAmount())); // TODO bankruptcy
                // Take money from player and check if they are bankrupt
                if (bankrupt) {
                    bankruptcy();
                }
                property.getOwner().addMoney(property.getRentValue(property.getHouseAmount())); // Pay rent to owner
            }

        } else {
            // Allow player to buy property if they have sufficient funds
            if (property.getBuyValue() > player.getMoney()) {
                JOptionPane.showMessageDialog(this, "You don't have enough money to buy this property.");
            } else {
                int result = JOptionPane.showConfirmDialog(this, "Do you wish to buy the property, '" + property.getName() + "', for $" + property.getBuyValue() + " ?", "Buying Property", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // Give player property and take money from player if they buy property
                    JOptionPane.showMessageDialog(this, player.getName() +  " now owns " + property.getName());
                    player.subMoney(property.getBuyValue());
                    player.addProperty(property);
                    property.setOwner(player);
                } else {
                    JOptionPane.showMessageDialog(this, "You don't wish to buy this property.");
                }
            }
        }
    }

    /**
     * The events that happen when a player lands on a utility tile, either the option to buy the utility or pay rent
     * to the utility of the property.
     * @param utility The utility tile the player landed on
     * @param player Player that landed on the tile
     */
    private void onUtility(Utility utility, Player player) {
        // Make player pay rent to owner if they land on owned unmortgaged utility
        if (utility.isOwned()) {
            // Don't pay rent if utility is mortgaged
            if (utility.isMortgaged()) {
                JOptionPane.showMessageDialog(this, "You landed on an owned utility, but it is mortgaged. So you do not have to pay rent");
            } else {
                JOptionPane.showMessageDialog(this, "You must pay " + utility.getRentValue() + " to stay here.");
                boolean bankrupt = player.subMoney(utility.getRentValue());
                // Take money from player and check if they are bankrupt
                if (bankrupt) {
                    bankruptcy();
                }
                utility.getOwner().addMoney(utility.getRentValue()); // Give money to owner
            }
        } else {
            // Allow player to buy utility if eligible
            if (utility.getBuyValue() > player.getMoney()) {
                JOptionPane.showMessageDialog(this, "You don't have enough money to buy this utility.");
            } else {
                // Give player utility and take money from player if they buy property
                int result = JOptionPane.showConfirmDialog(this, "Do you wish to buy the utility, '" + utility.getName() +  "' for " + utility.getBuyValue() + " ?", "Buying Utility", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(this, player.getName() +  " now owns " + utility.getName());
                    player.subMoney(utility.getBuyValue());
                    player.addUtility(utility);
                    utility.setOwner(player);
                } else {
                    JOptionPane.showMessageDialog(this, "You don't wish to buy this property.");
                }
            }
        }
    }

    /**
     * The events that happen when player lands on tax tile, take tax amount from player's balance and increase free
     * parking money.
     * @param tax The tax tile the player landed on
     * @param player Player that landed on tile
     */
    private void onTax(TaxTile tax, Player player) {
        boolean bankruptcy = player.subMoney(tax.getTaxAmount());
        freeParkingMoney += tax.getTaxAmount(); // Add money to pool to pay to player who land on free parking
        JOptionPane.showMessageDialog(this, "You must pay a tax of " + tax.getTaxAmount() + ". The amount of money in the free parking pool is now $" + freeParkingMoney + ".");

        if(bankruptcy)
            bankruptcy();
    }

    /**
     * The events that happen when a player lands on a chance tile, draws a card from the chance card deck and applies
     * effect onto the player
     * @param chance The chance tile the player landed on
     * @param player Player that landed on the tile
     */
    private void onChance(ChanceTile chance, Player player) {
        JOptionPane.showMessageDialog(this, "You landed on a chance tile. Drawing a card now.");

        int playerLastPosition = player.getPosition(); // Used to determine if player was moved by card

        Card chanceCard = chance.drawCard();
        JOptionPane.showMessageDialog(this, chanceCard.getCardText());
        boolean checkPlayer = chanceCard.playerEffect(player); // Used to check certain variables after player has been affected

        if ((chanceCard instanceof MovementCard || chanceCard instanceof RealtiveMoveCard) && checkPlayer) {
            // Player has passed go, give them money
            passedGo();
        } else if (chanceCard instanceof SubMoneyCard && checkPlayer) {
            // Player's balance is below 0, declare bankruptcy
            bankruptcy();
        } else if (chanceCard instanceof JailCard && checkPlayer) {
            // Send player to jail
            goToJail(player);
        }

        // If player is moved by the card, determine movement result again as if they rolled dice
        if (player.getPosition() != playerLastPosition) {
            determineMovementResult();
        }
    }

    /**
     * The events that happen when a player lands on a community tile, draws a card from the community chest card deck and
     * applies effect onto the player
     * @param community The community tile the player landed on
     * @param player Player that landed on the tile
     */
    private void onCommunityChest(CommunityTile community, Player player) {
        JOptionPane.showMessageDialog(this, "You landed on a chest of fortune tile. Drawing a card now.");

        int playerLastPosition = player.getPosition(); // Used to determine if player was moved by card

        Card communityCard = community.drawCard();
        JOptionPane.showMessageDialog(this, communityCard.getCardText());
        boolean checkPlayer = communityCard.playerEffect(player); // Used to check certain variables after player has been affected

        if ((communityCard instanceof MovementCard || communityCard instanceof RealtiveMoveCard) && checkPlayer) {
            // Player has passed go, give them money
            passedGo();
        } else if (communityCard instanceof SubMoneyCard && checkPlayer) {
            // Player's balance is below 0, declare bankruptcy
            bankruptcy();
        } else if (communityCard instanceof JailCard && checkPlayer) {
            // Send player to jail
            goToJail(player);
        }

        // If player is moved by the card, determine movement result again as if they rolled dice
        if (player.getPosition() != playerLastPosition) {
            determineMovementResult();
        }
    }

    /**
     * Sends player to jail and ends their turn.
     * @param player Player that is going to jail
     */
    private void goToJail(Player player) {
        JOptionPane.showMessageDialog(this, "You were caught breaking the law, go to jail. Do not collect 200$, and your turn ends immediately.");
        player.setInJail(true);
        player.moveSpecificPosition(10);
        paintBoardPanel();
        endTurn();
    }

    /**
     * Give player free parking money that has been accumulating money from players landing on tax tiles
     * @param player Player that landed on tile
     */
    private void onParking(Player player) {
        JOptionPane.showMessageDialog(this, "You have landed on free parking. You collect $" + freeParkingMoney + " from the free parking pool.");
        player.addMoney(freeParkingMoney);
        freeParkingMoney = 0;
    }

    /**
     * Check if player meets all requirements to buy a house on the property, then either purchases a house or cancels the
     * transaction.
     * @param selectedProperty Property the player wishes to buy houses on
     */
    private void buyHouse(Property selectedProperty) {
        // Prevent more than 5 houses on one property
        if (selectedProperty.getHouseAmount() == 5) {
            JOptionPane.showMessageDialog(this, "You cannot buy more than 5 houses.");
            return;
        }

        int propertyAmount = 0;

        // Check to see if player has all properties of one color
        for (Property property: players[currentPlayer].getProperties()) {
            if (selectedProperty.getColorEnum() == property.getColorEnum()) {
                propertyAmount++;
            }
        }

        // Display and exit if player does not own all properties of one color
        if (propertyAmount < houseAmounts.get(selectedProperty.getColorEnum())) {
            JOptionPane.showMessageDialog(this, "You don't own all the properties of this color.");
            return;
        }

        // Check if at least one property is mortgaged, if true, prevent house purchase
        for (Property property: players[currentPlayer].getProperties()) {
            if (selectedProperty.getColorEnum() == property.getColorEnum()) {
                if (property.isMortgaged()) {
                    JOptionPane.showMessageDialog(this, "You cannot buy houses if one or more properties are mortgaged in a color group.");
                    return;
                }
            }
        }

        // These two variables are to make sure player builds up properties at the same rate, rather than focus one
        int highestHouseAmount = 0;
        int lowestHouseAmount = 6;

        // Check to see if player has properties that have equal house amounts
        for (Property property: players[currentPlayer].getProperties()) {
            if (selectedProperty.getColorEnum() == property.getColorEnum()) {
                if (property.getHouseAmount() > highestHouseAmount) {
                    highestHouseAmount = property.getHouseAmount();
                } else if (property.getHouseAmount() < lowestHouseAmount) {
                    lowestHouseAmount = property.getHouseAmount();
                }
            }
        }

        // If player upgrades property with most houses, but there are still properties in the color group that are not
        // that level, prevent upgrade
        if (selectedProperty.getHouseAmount() == highestHouseAmount && lowestHouseAmount < highestHouseAmount) {
            JOptionPane.showMessageDialog(this, "You need to develop the properties of one color at the same rate. " +
                    "EX: If one property has two houses and the other has one house, the property with one house must have two houses before the " +
                    "two house property can have three houses.");
            return;
        }

        // Check to see if player has enough money to buy house
        if (players[currentPlayer].getMoney() < selectedProperty.getHouseCost()) {
            JOptionPane.showMessageDialog(this, "You don't have enough money to buy this property.");
            return;
        }

        // Finally, confirm player wants to buy a house on property
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to buy a house, on " + selectedProperty.getName() + " for " + selectedProperty.getHouseCost() + "?");

        if (choice == JOptionPane.YES_OPTION) {
            // Put one house on property and subtract money from player
            JOptionPane.showMessageDialog(this, "You bought a house on this property named " + selectedProperty.getName() + " for " + selectedProperty.getHouseCost() + ".");
            selectedProperty.incrementHouseAmount();
            players[currentPlayer].subMoney(selectedProperty.getHouseCost());
        } else {
            JOptionPane.showMessageDialog(this, "You changed your mind on buying a house on this property.");
        }
    }

    /**
     * Check if player meets all requirements to sell a house on the property, then either sell a house or cancels the
     * transaction.
     * @param selectedProperty property players wishes to sell property on
     */
    private void sellHouse(Property selectedProperty) {
        // Prevent player from selling houses that don't exist
        if (selectedProperty.getHouseAmount() == 0) {
            JOptionPane.showMessageDialog(this, "There are no houses to sell.");
            return;
        }

        // These two variables are to make sure player deconstructs properties at the same rate, rather than sell all
        // houses on one property but leave others with a high amount of houses
        int highestHouseAmount = 0;
        int lowestHouseAmount = 6;

        // Check to see if player has properties that have equal house amounts
        for (Property property: players[currentPlayer].getProperties()) {
            if (selectedProperty.getColorEnum() == property.getColorEnum()) {
                if (property.getHouseAmount() > highestHouseAmount) {
                    highestHouseAmount = property.getHouseAmount();
                } else if (property.getHouseAmount() < lowestHouseAmount) {
                    lowestHouseAmount = property.getHouseAmount();
                }
            }
        }

        // Prevent player from deconstructing property further if other properties have more houses than it
        if (selectedProperty.getHouseAmount() == lowestHouseAmount && lowestHouseAmount < highestHouseAmount) {
            JOptionPane.showMessageDialog(this, "You need to bring down the properties of one color at the same rate. " +
                    "EX: If one property has three houses and the other has two house, the property with three house must have two houses before the " +
                    "two house property can have one houses.");
            return;
        }

        // Finally, confirm player wants to sell a house on the property
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to sell a house, from " + selectedProperty.getName() + " for " + selectedProperty.getHouseCost() / 2 + "?");

        if (choice == JOptionPane.YES_OPTION) {
            // Get rid of house and give player half of the houses cost
            JOptionPane.showMessageDialog(this, "You sold a house on this property named " + selectedProperty.getName() + " for " + selectedProperty.getHouseCost() / 2 + ".");
            selectedProperty.decrementHouseAmount();
            players[currentPlayer].addMoney(selectedProperty.getHouseCost() / 2);
        } else {
            JOptionPane.showMessageDialog(this, "You changed your mind on selling a house from this property.");
        }
    }

    /**
     * Allows player to mortgage a property to get mortgage value of the property in cash, with the downside
     * being rent can no longer be collected from players landing on that property
     * @param selectedProperty property player wishes to mortgage
     */
    private void mortgageProperty(Property selectedProperty) {
        // Make sure there are no houses in the color group the property belongs to
        boolean noHouses = true;
        for (Property property: players[currentPlayer].getProperties()) {
            if (selectedProperty.getColorEnum() == property.getColorEnum()) {
                if (property.getHouseAmount() > 0) {
                    noHouses = false;
                }
            }
        }

        // Prevent player from mortgaging a property if they started to buy houses for that color group
        if (!noHouses) {
            JOptionPane.showMessageDialog(this, "All properties of one color must have no houses to be able to mortgage a property of that color.");
            return;
        }

        // Confirm player wishes to mortgage property
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you wish to mortgage " + selectedProperty.getName() + " to gain " + selectedProperty.getMortgageValue() + "?");

        if (choice == JOptionPane.YES_OPTION) {
            // Give player mortgage value of the house, and prevent rent from being collected on the property
            JOptionPane.showMessageDialog(this, "You mortgaged the property " + selectedProperty.getName() + " to gain " + selectedProperty.getMortgageValue() + ".");
            players[currentPlayer].addMoney(selectedProperty.getMortgageValue());
            selectedProperty.setMortgaged(true);
        } else {
            JOptionPane.showMessageDialog(this, "You changed your mind to mortgage the property " + selectedProperty.getName() + ".");
        }

    }

    /**
     * Allows player to unmortgage a property to start collecting rent again, at the cost of the mortgage value plus
     * %10 interest the player has to pay
     * @param selectedProperty property players wishes to unmortgage
     */
    private void unmortgageProperty(Property selectedProperty) {

        // Ensure player has enough funds to unmortgage property
        if (players[currentPlayer].getMoney() < selectedProperty.getMortgageValue() + (selectedProperty .getMortgageValue() * .10)) {
            JOptionPane.showMessageDialog(this, "You lack the funds to unmortgage the property " + selectedProperty.getName() + ".");
            return;
        }

        // Confirm player wishes to unmortgage property
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you wish to unmortgage " + selectedProperty.getName() + " for " + (selectedProperty.getMortgageValue() + (selectedProperty.getMortgageValue() * .10)) + "?");

        if (choice == JOptionPane.YES_OPTION) {
            // Mortgagee property and subtract cash from player
            JOptionPane.showMessageDialog(this, "You unmortgaged the property " + selectedProperty.getName() + " for " + (selectedProperty.getMortgageValue() + (selectedProperty.getMortgageValue() * .10)) + ".");
            players[currentPlayer].subMoney((int) (selectedProperty.getMortgageValue() + (selectedProperty.getMortgageValue() * .10)));
            selectedProperty.setMortgaged(false);
        } else {
            JOptionPane.showMessageDialog(this, "You changed your mind to unmortgage the property " + selectedProperty.getName() + ".");
        }

    }

    /**
     * Sets up the tile array manually so that when the player moves, the game can figure out actions to do afterward.
     */
    private void setUpTiles() {
        tiles = new Tile[40];
        tiles[0] = new Tile(TileTypes.NONE); // None means nothing happens if the player lands on the tile
        tiles[1] = new Property(PropertyNames.MEDITERRANEAN_AVE); // Property tile, Property.name holds property info
        tiles[2] = new CommunityTile(); // Draw from community chest deck
        tiles[3] = new Property(PropertyNames.BALTIC_AVE);
        tiles[4] = new TaxTile(200); // Pay tax
        tiles[5] = new Utility(200, "RailRoad 1");
        tiles[6] = new Property(PropertyNames.ORIENTAL_AVE);
        tiles[7] = new ChanceTile(); // Draw from chance deck
        tiles[8] = new Property(PropertyNames.VERMONT_AVE);
        tiles[9] = new Property(PropertyNames.CONNECTICUT_AVE);
        tiles[10] = new Tile(TileTypes.NONE);
        tiles[11] = new Property(PropertyNames.ST_CHARLES_PL);
        tiles[12] = new Utility(150, "Electric Company");
        tiles[13] = new Property(PropertyNames.STATES_AVE);
        tiles[14] = new Property(PropertyNames.VIRGINIA_AVE);
        tiles[15] = new Utility(200, "Railroad 2");
        tiles[16] = new Property(PropertyNames.ST_JAMES_PL);
        tiles[17] = new CommunityTile();
        tiles[18] = new Property(PropertyNames.TENNESSEE_AVE);
        tiles[19] = new Property(PropertyNames.NEW_YORK_AVE);
        tiles[20] = new Tile(TileTypes.PARKING); // Player collects free parking money when landing on this tile
        tiles[21] = new Property(PropertyNames.KENTUCKY_AVE);
        tiles[22] = new ChanceTile();
        tiles[23] = new Property(PropertyNames.INDIANA_AVE);
        tiles[24] = new Property(PropertyNames.ILLINOIS_AVE);
        tiles[25] = new Utility(200, "Railroad 3");
        tiles[26] = new Property(PropertyNames.ATLANTIC_AVE);
        tiles[27] = new Property(PropertyNames.VENTNOR_AVE);
        tiles[28] = new Utility(150, "Water Works");
        tiles[29] = new Property(PropertyNames.MARVIN_GAR);
        tiles[30] = new Tile(TileTypes.GOTOJAIL); // Send player to jail
        tiles[31] = new Property(PropertyNames.PACIFIC_AVE);
        tiles[32] = new Property(PropertyNames.NORTH_CAROLINA_AVE);
        tiles[33] = new CommunityTile();
        tiles[34] = new Property(PropertyNames.PENNSYLVANIA_AVE);
        tiles[35] = new Utility(200, "Railroad 4");
        tiles[36] = new ChanceTile();
        tiles[37] = new Property(PropertyNames.PARK_PL);
        tiles[38] = new TaxTile(100);
        tiles[39] = new Property(PropertyNames.BOARDWALK);
    }

    /**
     * Set up the bankruptcy button frame to allow player to get out of bankruptcy
     */
    private void bankruptcy() {
        JOptionPane.showMessageDialog(this, "Bankruptcy! If you have any houses to sell or properties " +
                "to mortgage, do so to get balance above 0. Else you will go bankrupt and leave the game! When balance is above 0" +
                " , press the paid debts button to get back into the game.");
        paintBankruptcyButtonFrame();
    }

    /**
     * Paints the board Panel and updates the positions of players on the board when they move.
     */
    private void paintBoardPanel() {
        // Clear panels
        northPanel.removeAll();
        southPanel.removeAll();
        eastPanel.removeAll();
        westPanel.removeAll();

        // Positions player icon will be placed next to the tile they occupy
        int[][] southPanelPositions = {{700, 0},{640,0},{585,0},{535,0},{485,0},{440,0},{390,0},{340,0},{290,0},{245,0}};
        int[][] westPanelPositions = {{40, 530},{40,465},{40,420},{40, 370},{40, 320},{40, 270},{40,220},{40,170},{40,125},{40,75}};
        int[][] northPanelPositions = {{100, 40},{160,40},{210,40},{260,40},{310,40},{360,40},{410,40},{460,40},{510,40},{560,40}};
        int[][] eastPanelPositions = {{0, 30},{0, 100},{0, 150},{0,190},{0,240},{0,290},{0,340},{0,390},{0,440},{0,490}};

        int[] positionOffSets = new int[40]; // Used to offset player icons so they are not overlapping when on same tile

        // Draw all player names in game
        for (Player player : players) {
            if (player == null || player.isBankrupt()) continue; // Don't draw players who are bankrupt or don't exist

            JLabel playerLabel = new JLabel(player.getName());
            playerLabel.setPreferredSize(new Dimension(80, 20));

            int pos = player.getPosition();

            if (pos >= 0 && pos <= 9) { // Bottom (South Panel)
                playerLabel.setBounds(southPanelPositions[pos][0], southPanelPositions[pos][1] + positionOffSets[pos], 80, 20);
                positionOffSets[pos] += 10; // Increase position offset at that specific position to prevent player icons from overlapping
                southPanel.add(playerLabel);
            } else if (pos >= 10 && pos <= 19) { // Left (West Panel)
                playerLabel.setBounds(westPanelPositions[pos % 10][0], westPanelPositions[pos % 10][1] + positionOffSets[pos], 80, 20);
                positionOffSets[pos] += 10;
                westPanel.add(playerLabel);
            } else if (pos >= 20 && pos <= 29) { // Top (North Panel)
                playerLabel.setBounds(northPanelPositions[pos % 10][0], northPanelPositions[pos % 10][1] + positionOffSets[pos], 80, 20);
                positionOffSets[pos] -= 10; // TODO Reverse
                northPanel.add(playerLabel);
            } else if (pos >= 30 && pos <= 39) { // Right (East Panel)
                playerLabel.setBounds(eastPanelPositions[pos % 10][0], eastPanelPositions[pos % 10][1] + positionOffSets[pos], 80, 20);
                positionOffSets[pos] += 10;
                eastPanel.add(playerLabel);
            }
        }

        // Refresh panels
        northPanel.revalidate();
        northPanel.repaint();
        southPanel.revalidate();
        southPanel.repaint();
        eastPanel.revalidate();
        eastPanel.repaint();
        westPanel.revalidate();
        westPanel.repaint();
    }

    /**
     * Paints the player side panel on the right side of the screen, which includes the current player's name, money
     * position, and a list of the properties they own
     */
    private void paintPlayerSidePanel() {
        clearSideBarPanel();

        // Get details about the player to display to user on the side panel
        String playerName = players[currentPlayer].getName();
        int playerMoney = players[currentPlayer].getMoney();
        int playerPosition = players[currentPlayer].getPosition();
        JLabel playerNameLabel = new JLabel("Player Name: " + playerName);
        JLabel playerMoneyLabel = new JLabel("Player Money: " + playerMoney);
        JLabel playerPositionLabel = new JLabel("Player Position: " + (playerPosition + 1));

        // Get properties and utilities and put them into one list to display them all
        List<Property> properties = players[currentPlayer].getProperties();
        List<Utility> utilities = players[currentPlayer].getUtilities();

        List<Object> assets = new ArrayList<Object>();

        for (Property property : properties) {
            assets.add(property);
        }

        for (Utility utility : utilities)
            assets.add(utility);

        // Create the JList of properties
        propertiesList = new JList<>(assets.toArray());

        propertiesList.setFixedCellWidth(100); // Prevents JList from expanding to take up entire panel on the east side
        // Set a custom cell renderer for the list
        propertiesList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (renderer instanceof JLabel && value instanceof Property) {
                    JLabel label = (JLabel) renderer;
                    Property property = (Property) value;

                    // Set property name as the text
                    label.setText(property.toString());

                    // Add a colored icon to represent the property color
                    label.setIcon(new ColorIcon(property.getColor()));
                } else if (renderer instanceof JLabel && value instanceof Utility) {
                    JLabel label = (JLabel) renderer;
                    Utility utility = (Utility) value;

                    label.setText(utility.toString());

                    label.setIcon(new ColorIcon(Color.GRAY));
                }

                return renderer;
            }
        });

        propertiesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sideBarPanel.add(playerNameLabel);
        sideBarPanel.add(playerMoneyLabel);
        sideBarPanel.add(playerPositionLabel);
        sideBarPanel.add(new JScrollPane(propertiesList));

        sideBarPanel.revalidate();
        sideBarPanel.repaint();

    }

    /**
     * Remove all components in sidebar panel to draw new components on sidebar panel
     */
    private void clearSideBarPanel() {
        sideBarPanel.removeAll();
    }

    /**
     * Displays the actions that the player is able to do in a normal turn
     */
    private void paintStandardButtonFrame() {
        clearActionPanel();

        // Create buttons the player is able to do on a normal turn
        JButton rollDiceButton = rollDiceButton();
        JButton buyHousesButton = buyAndSellHouseButton();
        JButton mortgageButton = mortgagePropertyButton();
        JButton endTurnButton = endTurnButton();

        // Add buttons to JPanel
        actionPanel.add(rollDiceButton);
        actionPanel.add(buyHousesButton);
        actionPanel.add(mortgageButton);
        actionPanel.add(endTurnButton);

        // Repaint screen
        actionPanel.revalidate();
        actionPanel.repaint();
    }

    /**
     * Displays the actions that the player is able to do while they are in jail
     */
    private void paintJailButtonFrame() {
        clearActionPanel();

        // Create buttons that show the actions the player can do while in jail
        JButton rollDiceButton = rollDiceJailButton();
        JButton payFineButton = payFineButton();
        JButton buyHousesButton = buyAndSellHouseButton();
        JButton mortgageButton = mortgagePropertyButton();
        JButton endTurnButton = endTurnJailButton();

        // Add buttons to JPanel
        actionPanel.add(rollDiceButton);
        actionPanel.add(payFineButton);
        actionPanel.add(buyHousesButton);
        actionPanel.add(mortgageButton);
        actionPanel.add(endTurnButton);

        // Repaint screen
        actionPanel.revalidate();
        actionPanel.repaint();
    }

    /**
     * Creates the buttons that the player is able to do to avoid bankruptcy and remain in the game, or declare bankruptcy
     * and leave the game
     */
    private void paintBankruptcyButtonFrame() {
        clearActionPanel();

        // Set up buttons
        JButton sellHousesButton = bankruptcySellHouseButton();
        JButton mortgageButton = bankruptcyMortgagePropertyButton();
        JButton payDebtsButton = debtsPaidButton();
        JButton declareBankruptcy = declareBankruptcyButton();

        // Add buttons to panel
        actionPanel.add(sellHousesButton);
        actionPanel.add(mortgageButton);
        actionPanel.add(payDebtsButton);
        actionPanel.add(declareBankruptcy);

        actionPanel.add(declareBankruptcy);
    }

    /**
     * Creates the button that allows player to roll dice to move on the game board
     * @return JButton that allows player to roll dice to move on the game board
     */
    private JButton rollDiceButton() {
        JButton rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Roll dice if player has not already rolled dice this turn
                if (diceRolled) {
                    JOptionPane.showMessageDialog(boardPanel, "You have already rolled this turn.");
                } else {
                    rollDice();
                }
            }
        });

        return rollDiceButton;
    }

    /**
     * Creates the button that allows the player to roll dice to attempt to leave jail early
     * @return JButton that allows player to roll dice to attempt to leave jail early
     */
    private JButton rollDiceJailButton() {
        JButton rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Roll dice if player has not rolled dice this turn
                if (diceRolled) {
                    JOptionPane.showMessageDialog(boardPanel, "You have already rolled this turn.");
                } else {
                    rollJailDice();
                }
            }
        });
        
        return rollDiceButton;
    }

    /**
     * Creates a button that allow the player to pay a fine to leave jail and roll dice afterward
     * @return JButton that allows player to pay a find to leave jail
     */
    private JButton payFineButton() {
        JButton payFineButton = new JButton("Pay Fine and roll dice");
        payFineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Player pays fine and gets out of jail, and then moves
                JOptionPane.showMessageDialog(boardPanel, "You paid a fine of $50 to get out.");
                players[currentPlayer].setInJail(false);
                players[currentPlayer].setTurnsInJail(0);
                players[currentPlayer].subMoney(50);

                rollDice();
                paintStandardButtonFrame();
            }
        });
        return payFineButton;
    }

    /**
     * Creates a button that allows the player to buy and sell houses
     * @return A button that allows the player to buy and sell houses
     */
    private JButton buyAndSellHouseButton() {
        JButton button = new JButton("Buy/Sell House");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Property propertySelected = (Property) propertiesList.getSelectedValue();

                // Check if player selected a property from the list
                if (propertySelected == null) {
                    JOptionPane.showMessageDialog(boardPanel, "You must select a property from the list on the right side of the screen.");
                } else {
                    // Ask if player is buying or selling houses on property
                    int choice = JOptionPane.showOptionDialog(boardPanel, propertySelected.getName() + " has " + propertySelected.getHouseAmount() + " houses. Do you wish to buy or sell house on the property ?", "Buying and Selling Houses", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Buy", "Selling", "Cancel"}, propertySelected.getName());
                    if (choice == 0) {
                        buyHouse(propertySelected);
                    } else if (choice == 1) {
                        sellHouse(propertySelected);
                    }
                    paintPlayerSidePanel();
                }
            }
        });
        return button;
    }

    /**
     * Creates a button that allows the player to sell houses to pay off debts to avoid bankruptcy
     * @return A button that allow the player to sell houses during bankruptcy
     */
    private JButton bankruptcySellHouseButton() {
        JButton button = new JButton("Sell House");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Property propertySelected = (Property) propertiesList.getSelectedValue();

                // Check if player selected a property from the list
                if (propertySelected == null) {
                    JOptionPane.showMessageDialog(boardPanel, "You must select a property from the list on the right side of the screen.");
                } else {
                    // Ask if player is buying or selling houses on property
                    int choice = JOptionPane.showOptionDialog(boardPanel, propertySelected.getName() + " has " + propertySelected.getHouseAmount() + " houses. Do you wish to buy or sell house on the property ?", "Buying and Selling Houses", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Buy", "Selling", "Cancel"}, propertySelected.getName());
                    if (choice == 0) {
                        buyHouse(propertySelected);
                    } else if (choice == 1) {
                        sellHouse(propertySelected);
                    }
                    paintPlayerSidePanel();
                }
            }
        });

        return button;
    }

    /**
     * Creates the button that allows the player to mortgage property they own
     * @return JButton that allows player to mortgage properties
     */
    private JButton mortgagePropertyButton() {
        JButton mortgageButton = new JButton("Mortgage Property");
        mortgageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Property propertySelected = (Property) propertiesList.getSelectedValue();

                // Check if player selected a property from list
                if (propertySelected == null) {
                    JOptionPane.showMessageDialog(boardPanel, "You must select a property from the list on the right side of the screen.");
                } else {
                    // Check if property is mortgaged or not, and ask if they wish to change it
                    if (propertySelected.isMortgaged()) {
                        JOptionPane.showConfirmDialog(boardPanel, propertySelected.getName() + "is mortgaged. Do you wish to unmortgage the property for " + (propertySelected.getMortgageValue() + (propertySelected.getMortgageValue() * .10)) + " to regain rent collection?");
                        unmortgageProperty(propertySelected);
                    } else {
                        JOptionPane.showConfirmDialog(boardPanel, propertySelected.getName() + "is unmortgaged. Do you wish to mortgage the property to gain " + propertySelected.getMortgageValue() + " at the cost for losing rent collection?");
                        mortgageProperty(propertySelected);
                    }
                    paintPlayerSidePanel();
                }
            }
        });
        return mortgageButton;
    }

    /**
     * Creates a button that allows player to mortgage properties to avoid bankruptcy
     * @return JButton that allows player to mortgage properties while bankrupt
     */
    private JButton bankruptcyMortgagePropertyButton() {
        JButton button = new JButton("Mortgage Property");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Property propertySelected = (Property) propertiesList.getSelectedValue();

                // Check if player selected a property from list
                if (propertySelected == null) {
                    JOptionPane.showMessageDialog(boardPanel, "You must select a property from the list on the right side of the screen.");
                } else {
                    // Check if property is mortgaged or not, only allow player to mortgage properties to pay debt
                    if (propertySelected.isMortgaged()) {
                        JOptionPane.showConfirmDialog(boardPanel, propertySelected.getName() + "is mortgaged. You can't unmortgage property until debts have been paid off.");
                    } else {
                        JOptionPane.showConfirmDialog(boardPanel, propertySelected.getName() + "is unmortgaged. Do you wish to mortgage the property to gain " + propertySelected.getMortgageValue() + " to pay your debts?");
                        mortgageProperty(propertySelected);
                    }
                    paintPlayerSidePanel();
                }
            }
        });

        return button;
    }

    /**
     * Creates the end turn button that is shown in normal circumstances
     * @return A JButton that ends the player's turn
     */
    private JButton endTurnButton() {
        JButton endTurnButton = new JButton("End Turn");
        endTurnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Player must roll dice before ending turn
                if (!diceRolled) {
                    JOptionPane.showMessageDialog(boardPanel, "You must roll the dice before you end the turn.");
                } else {
                    endTurn();
                }
            }
        });

        return endTurnButton;
    }

    /**
     * Creates the end turn button that shows if the player is in jail
     * @return A JButton that ends the player's turn while in jail
     */
    private JButton endTurnJailButton() {
        JButton endTurnButton = new JButton("End Turn");
        endTurnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Player can only remain in jail for 3 turns, after that they must pay $50 dollar fine and leave
                if (players[currentPlayer].getTurnsInJail() == 3) {
                    JOptionPane.showMessageDialog(boardPanel, "You must pay fine and roll dice before you end the turn, or use other methods if available.");
                } else {
                    endTurn();
                }
            }
        });

        return endTurnButton;
    }

    /**
     * Creates the debts paid button that allows the player back into the game if they have paid off their debts, avoiding
     * bankruptcy.
     * @return Button that allows player to get back into the game
     */
    private JButton debtsPaidButton() {
        JButton debtsPaidButton = new JButton("Debts Paid");
        debtsPaidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if player has paid off debts, if true allow them to play again
                if (players[currentPlayer].getMoney() >= 0) {
                    JOptionPane.showMessageDialog(boardPanel, "Debts have been paid off. " + players[currentPlayer].getName() + " is back in the game!");
                    paintStandardButtonFrame();
                } else {
                    JOptionPane.showMessageDialog(boardPanel, "Debts still have not been paid off! Keeping selling houses or mortgaging properties to avoid bankrupcty.");
                }
            }
        });

        return debtsPaidButton;
    }

    /**
     * Creates a button that allows player to declare bankrupt they are unable to pay off their debts, it takes the player
     * out of the game and makes all properties unowned and without houses
     * @return Button that allows player to declare bankruptcy
     */
    private JButton declareBankruptcyButton() {
        JButton button = new JButton("Declare Bankruptcy");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(boardPanel, "Are you sure you want to declare bankruptcy?", "Declaring Bankruptcy", JOptionPane.YES_NO_OPTION);

                // Ensure player really wants to declare bankruptcy
                if (result == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(boardPanel, players[currentPlayer] + " has declared bankruptcy. All their properties will be made unowned and undeveloped." +
                            " They will no longer be able to play the game.");

                    players[currentPlayer].setBankrupt(true);

                    // Reset every property player owned back to default state
                    for (Property property : players[currentPlayer].getProperties()) {
                        property.setOwner(null);
                        property.setMortgaged(false);

                        while (property.getHouseAmount() > 0) {
                            property.decrementHouseAmount();
                        }
                    }

                    // Reset every utility player owned back to default state
                    for (Utility utility : players[currentPlayer].getUtilities()) {
                        utility.setOwner(null);
                        utility.setMortgaged(false);
                    }

                    boolean winnerFound = checkForWinner();

                    // End game if only one player remains, else continue onto next player
                    if (winnerFound) {
                        //TODO NEEDS TO BE FIXED AS JPANEL
//                        endGame();
                    } else {
                        endTurn();
                        paintBoardPanel();
                    }
                } else {
                    JOptionPane.showMessageDialog(boardPanel, "You have changed your mind about declaring bankruptcy.");
                }
            }
        });

        return button;
    }

    /**
     * Checks to see if there is only one player still remaining in game, if true, then declare them the winner
     * @return If only one player is still in game
     */
    private boolean checkForWinner() {
        int amountOfPlayersIn = 0;
        for (int i = 0; i <= amountOfPlayers; i++) { // See if only one player is bankrupt
            if (!players[i].isBankrupt())
                amountOfPlayersIn++;
        }

        return amountOfPlayersIn == 1;
    }

    /**
     * Announces the winner and allows the players to rest or exit the game
     */
    private void endGame() {
        // Find winner
        Player winner = null;
        // See if only one player is bankrupt, should be first player found since this is called after check for winner
        for (int i = 0; i < amountOfPlayers; i++)
            if (!players[i].isBankrupt())
                winner = players[i];

        assert winner != null; // Winner should not be null, if it is then something is wrong
        JOptionPane.showMessageDialog(boardPanel, winner.getName() + " has won the game!");
        //TODO THIS NEEDSS TO BE FIXED AS JPANEL
        //this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); // Quit program
    }

    /**
     * Clears action panel so that new buttons can be drawn there
     */
    private void clearActionPanel() {
        actionPanel.removeAll();
    }


}
