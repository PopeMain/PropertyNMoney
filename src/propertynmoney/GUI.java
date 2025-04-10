package propertynmoney;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Constructs, displays GUI, and manages the game. The GUI is separated into three parts, boardPanel, sideBarPanel, and
 * actionPanel. Board Panel displays the game board and the locations of all players still in the game, sideBarPanel
 * displays the current player's name, money amount, and a list of owned properties, actionPanel displays buttons of that
 * the current player is able to do. Any information that needs to be displayed to the player are displayed using
 * JOptionPanes. At the start of the game, a box will appear which will ask the user or users how many players are in
 * the game, then it will ask how many players are computer controlled, then the program will ask the name of each player
 * one by one. Finally, the program will start up the game, giving each player $1500 and starting their position at the
 * start. Starting with the first player, the player will have multiple buttons on the bottom to click on, rolling dice
 * which will roll dice and move the current player by the sum of the dice, buy houses which will allow the player to buy
 * houses on a property where they own all properties of one color by selecting the property in the side panel and clicking
 * the button, the mortgage button allows the current player to select any property they own and get half of its value
 * in to cash with the cost of having the property unable to collect rent until the mortgage is paid back, and the end
 * turn button allows the player to end their turn after they have rolled the dice and allows the program to change the
 * next player to the next player. Upon rolling dice, the program will move the player by the sum of the dice and will
 * take the players position and check which tile they landed on in the tiles array. If property, the program will check
 * if the property is owned, if false allow the user to purchase the property for the value of the house, if user purchases
 * give the player the property and subtract the money from their account, else if the property is owned, the player must
 * pay rent to the owner. If the player lands on a tax tile, they must pay the value of the tax. If the tile is a utility,
 * the process is the same as property. If the player lands on a chance or community chest tile, pull a random card and
 * put the effect on the player or players, which can include giving money, paying money, moving player, going to jail.
 * If the player lands on the go-to jail tile, the player is put into jail. If the player lands or passes the GO tile,
 * give the player $200.
 * @author Nevin Fullerton and Frank Pope
 */

public class GUI extends JFrame {

    private final JPanel boardPanel;
    private final JPanel sideBarPanel;
    private final JPanel actionPanel;
    private JList<Object> propertiesList;



    private Player[] players; // Holds the players in the game
    private int currentPlayer; // The index of the current player in the array of players
    private int amountOfPlayers; // Amount of players - 1
    private boolean diceRolled; // If dice have been rolled this turn
    private int doubleAmount; // Used to detect if player is speeding

    private Tile[] tiles; // When the player moves, the position will be used as an index to determine what should happen to player

    // Panels that hold the icons of each player, to show their location on the board
    private JPanel northPanel;
    private JPanel northPanelHolder;
    private JPanel southPanel;
    private JPanel southPanelHolder;
    private JPanel eastPanel;
    private JPanel eastPanelHolder;
    private JPanel westPanel;
    private JPanel westPanelHolder;

    private final Random diceRand; // Random number generation for dice rolls

    private final int IMAGE_WIDTH;

    private Map<PropertyColors, Integer> houseAmounts = new HashMap<PropertyColors, Integer>();

    /**
     *
     */
    GUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Property N Money");
        this.setSize(1000, 900);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        diceRand = new Random();
        final ImageIcon gameBoard = new ImageIcon("src/GameBoard_Resized.png");
        players = new Player[8];
        players[0] = new Player(1500, "Nevin"); // Testing ** Remove when done
        players[1] = new Player(1500, "Frank");
        players[2] = new Player(1500, "Nathan");
        players[0].moveSpecificPosition(29);

        currentPlayer = 0;
        amountOfPlayers = 2;

        houseAmounts.put(PropertyColors.BROWN, 2);
        houseAmounts.put(PropertyColors.CYAN, 3);
        houseAmounts.put(PropertyColors.MAGENTA, 3);
        houseAmounts.put(PropertyColors.ORANGE, 3);
        houseAmounts.put(PropertyColors.RED, 3);
        houseAmounts.put(PropertyColors.YELLOW, 3);
        houseAmounts.put(PropertyColors.GREEN, 3);
        houseAmounts.put(PropertyColors.BLUE, 2);

        IMAGE_WIDTH = gameBoard.getIconWidth(); // Width same as

        // Construct player holders
        northPanel = new JPanel();
        northPanel.setLayout(new GridBagLayout());
        northPanelHolder = new JPanel();
        northPanelHolder.setLayout(new BorderLayout());
        northPanelHolder.add(northPanel, BorderLayout.WEST);
        southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());
        southPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        southPanelHolder = new JPanel();
        southPanelHolder.setLayout(new BorderLayout());
        southPanelHolder.add(southPanel, BorderLayout.EAST);
        eastPanel = new JPanel();
        eastPanel.setLayout(new GridBagLayout());
        eastPanelHolder = new JPanel();
        eastPanelHolder.setLayout(new BorderLayout());
        eastPanelHolder.add(eastPanel, BorderLayout.NORTH);
        westPanel = new JPanel();
        westPanel.setLayout(new GridBagLayout());
        westPanelHolder = new JPanel();
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

        boardPanel.add(northPanelHolder, BorderLayout.NORTH);
        boardPanel.add(southPanelHolder, BorderLayout.SOUTH);
        boardPanel.add(eastPanelHolder, BorderLayout.EAST);
        boardPanel.add(westPanelHolder, BorderLayout.WEST);

        setUpTiles();
        paintBoardPanel();

        this.add(boardPanel, BorderLayout.CENTER);

        // Construct Side Bar Panel
        sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        paintPlayerSidePanel();

        this.add(sideBarPanel, BorderLayout.EAST);

        // Construct Action Panel
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
        paintStandardButtonFrame();

        this.add(actionPanel, BorderLayout.SOUTH);

        this.setSize(950, 800);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Rolls two dice and moves the player by the sum of the dice. If the dice have equal value, the player can roll again,
     * but if they roll three doubles in a row, they go to jail for speeding.
     */
    private void rollDice() {
        int dice1 = diceRand.nextInt(1,6);
        int dice2 = diceRand.nextInt(1,6);

//        boolean passedGo = players[currentPlayer].movePosition(dice1 + dice2);

        boolean passedGo = players[currentPlayer].movePosition(1);


        if (passedGo) {
            JOptionPane.showMessageDialog(this, "You passed go! Collect $200.");
            players[currentPlayer].addMoney(200);
        }

        if (dice1 == dice2) {
            if (doubleAmount == 3) {
                JOptionPane.showMessageDialog(this, "Oops! You were caught speeding! You must go to jail.");
                goToJail(players[currentPlayer]);
            } else {
                JOptionPane.showMessageDialog(this, "You rolled doubles! You get to roll again after you land on tile.");
                doubleAmount++;
            }
        } else {
            diceRolled = true;
        }

        JOptionPane.showMessageDialog(this, "You rolled a " + dice1 + ", and a " + dice2);
        paintBoardPanel();
        paintPlayerSidePanel();
        determineMovementResult();

    }

    /**
     * Rolls two dice, if they are of equal value, the player gets out of jail and moves by the sum of the dice, else
     * player remains in jail.
     */
    private void rollJailDice() {
        Random diceRand = new Random();
        int dice1 = diceRand.nextInt(1,6);
        int dice2 = diceRand.nextInt(1,6);
        diceRolled = true;

        JOptionPane.showMessageDialog(this, "You rolled a " + dice1 + ", and a " + dice2);

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
     * Will end the turn of the current player and pass onto the next eligible player, making them the
     * current player. The side panel is then updated to show the new current player's money, properties, name,
     * and position.
     */
    private void endTurn() {
        if (players[currentPlayer].isInJail()) {
            players[currentPlayer].setTurnsInJail(players[currentPlayer].getTurnsInJail() + 1);
        }

        doubleAmount = 0; // Reset double amount for next player

        boolean nextPlayerFound = false;
        while (!nextPlayerFound) {
            if (currentPlayer == amountOfPlayers) {
                currentPlayer = 0;
            } else {
                currentPlayer++;
            }

            if (!players[currentPlayer].isBankrupt()) {
                nextPlayerFound = true;
            }
        }

        if (players[currentPlayer].isInJail()) {
            paintJailButtonFrame();
        } else {
            paintStandardButtonFrame();
        }

        diceRolled = false;
        paintPlayerSidePanel();
    }

    /**
     * Will determine which position the player is on, and will react accordingly to what type of tile the player is on,
     * if property or utility either allow player to buy or make them pay rent to owner, if chance card
     */
    private void determineMovementResult() {
        Player player = players[currentPlayer];
        Tile tile = tiles[player.getPosition()];

        if (tile.getTileType() == TileTypes.PROPERTY) {
            Property property = (Property) tile;
            onProperty(property, player);
        } else if (tile.getTileType() == TileTypes.UTILITY) {
            Utility utility = (Utility) tile;
            onUtility(utility, player);
        } else if (tile.getTileType() == TileTypes.TAX) {
            TaxTile tax = (TaxTile) tile;
            JOptionPane.showMessageDialog(this, "You must pay a tax of " + tax.getTaxAmount() + ".");
            player.subMoney(tax.getTaxAmount());
        }else if (tile.getTileType() == TileTypes.CHANCE) {
            ChanceTile chance = (ChanceTile) tile;
            onChance(chance, player);
        } else if (tile.getTileType() == TileTypes.COMMUNITYCHEST) {
            CommunityTile community = (CommunityTile) tile;
            onCommunityChest(community, player);
        } else if (tile.getTileType() == TileTypes.GOTOJAIL) {
            goToJail(player);
        }

        paintPlayerSidePanel();
        paintBoardPanel();
    }

    /**
     * The events that happen when the player lands on a property tile, either the option to buy the property or paying rent to the
     * owner.
     * @param property
     * @param player
     */
    private void onProperty(Property property, Player player) {
        // Make player pay rent to owner if they land on owned unmortgaged property, else give them the ability to buy property if
        if (property.isOwned()) {
            // Don't pay rent if property is mortgaged
            if (property.isMortgaged()) {
                JOptionPane.showMessageDialog(this, "You landed on an owned property, but it is mortgaged. So you do not have to pay rent");
            } else {
                JOptionPane.showMessageDialog(this, "You must pay " + property.getRentValue(property.getHouseAmount()) + " to " + property.getOwner().toString() + "in order stay here.");
                player.subMoney(property.getRentValue(property.getHouseAmount()));
                property.getOwner().addMoney(property.getRentValue(property.getHouseAmount())); // Pay rent to owner
            }

        } else {
            if (property.getBuyValue() > player.getMoney()) {
                JOptionPane.showMessageDialog(this, "You don't have enough money to buy this property.");
            } else {
                int result = JOptionPane.showConfirmDialog(this, "Do you wish to buy property for " + property.getBuyValue() + " ?", "Buying Property", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
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
     * @param utility
     * @param player
     */
    private void onUtility(Utility utility, Player player) {
        if (utility.isOwned()) {
            JOptionPane.showMessageDialog(this, "You must pay " + utility.getRentValue() + " to stay here.");
            player.subMoney(utility.getRentValue());
        } else {
            if (utility.getBuyValue() > player.getMoney()) {
                JOptionPane.showMessageDialog(this, "You don't have enough money to buy this utility.");
            } else {
                int result = JOptionPane.showConfirmDialog(this, "Do you wish to buy the utility for " + utility.getBuyValue() + " ?", "Buying Utility", JOptionPane.YES_NO_OPTION);
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
     *
     * @param chance
     * @param player
     */
    private void onChance(ChanceTile chance, Player player) {
        int playerLastPosition = player.getPosition(); // Used to determine if player was moved by card

        Card chanceCard = chance.drawCard();
        JOptionPane.showMessageDialog(this, chanceCard.getCardText());
        chanceCard.playerEffect(player);

        // If player is moved by the card, determine movement result again as if they rolled dice
        if (player.getPosition() != playerLastPosition) {
            determineMovementResult();
        }
    }

    /**
     *
     * @param community
     * @param player
     */
    private void onCommunityChest(CommunityTile community, Player player) {
        int playerLastPosition = player.getPosition(); // Used to determine if player was moved by card

        Card communityCard = community.drawCard();
        JOptionPane.showMessageDialog(this, communityCard.getCardText());
        communityCard.playerEffect(player);

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
     * Check if player meets all requirements to buy a house on the property, then either purchases a house or cancels the
     * transaction.
     * @param selectedProperty
     */
    private void buyHouse(Property selectedProperty) {
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

        // Display and exit if player does not own all properties
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
     * @param selectedProperty
     */
    private void sellHouse(Property selectedProperty) {
        if (selectedProperty.getHouseAmount() == 0) {
            JOptionPane.showMessageDialog(this, "There are no houses to sell.");
            return;
        }

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

        if (selectedProperty.getHouseAmount() == lowestHouseAmount && lowestHouseAmount < highestHouseAmount) {
            JOptionPane.showMessageDialog(this, "You need to bring down the properties of one color at the same rate. " +
                    "EX: If one property has three houses and the other has two house, the property with three house must have two houses before the " +
                    "two house property can have one houses.");
            return;
        }

        // Finally, confirm player wants to buy a house on property
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to sell a house, from " + selectedProperty.getName() + " for " + selectedProperty.getHouseCost() / 2 + "?");

        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "You sold a house on this property named " + selectedProperty.getName() + " for " + selectedProperty.getHouseCost() / 2 + ".");
            selectedProperty.decrementHouseAmount();
            players[currentPlayer].addMoney(selectedProperty.getHouseCost() / 2);
        } else {
            JOptionPane.showMessageDialog(this, "You changed your mind on selling a house from this property.");
        }
    }

    /**
     *
     * @param selectedProperty
     */
    private void mortgageProperty(Property selectedProperty) {
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
            JOptionPane.showMessageDialog(this, "You mortgaged the property " + selectedProperty.getName() + " to gain " + selectedProperty.getMortgageValue() + ".");
            players[currentPlayer].addMoney(selectedProperty.getMortgageValue());
            selectedProperty.setMortgaged(true);
        } else {
            JOptionPane.showMessageDialog(this, "You changed your mind to mortgage the property " + selectedProperty.getName() + ".");
        }

    }

    /**
     *
     * @param selectedProperty
     */
    private void unmortgageProperty(Property selectedProperty) {

        if (players[currentPlayer].getMoney() < selectedProperty.getMortgageValue() + (selectedProperty .getMortgageValue() * .10)) {
            JOptionPane.showMessageDialog(this, "You lack the funds to unmortgage the property " + selectedProperty.getName() + ".");
            return;
        }

        // Confirm player wishes to mortgage property
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you wish to unmortgage " + selectedProperty.getName() + " for " + (selectedProperty.getMortgageValue() + (selectedProperty.getMortgageValue() * .10)) + "?");

        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "You unmortgaged the property " + selectedProperty.getName() + " for " + (selectedProperty.getMortgageValue() + (selectedProperty.getMortgageValue() * .10)) + ".");
            players[currentPlayer].subMoney((int) (selectedProperty.getMortgageValue() + (selectedProperty.getMortgageValue() * .10)));
            selectedProperty.setMortgaged(false);
        } else {
            JOptionPane.showMessageDialog(this, "You changed your mind to unmortgage the property " + selectedProperty.getName() + ".");
        }

    }

    /**
     * Sets up the tile array manually so that when the player moves, the game can figure out actions to do afterward.
     * Includes the information of each property, value, name, color,
     */
    private void setUpTiles() {
        tiles = new Tile[40];
        tiles[0] = new Tile(TileTypes.PARKING);
        tiles[1] = new Property(PropertyNames.MEDITERRANEAN_AVE);
        tiles[2] = new CommunityTile();
        tiles[3] = new Property(PropertyNames.BALTIC_AVE);
        tiles[4] = new TaxTile(200);
        tiles[5] = new Utility(200, "RailRoad 1");
        tiles[6] = new Property(PropertyNames.ORIENTAL_AVE);
        tiles[7] = new ChanceTile();
        tiles[8] = new Property(PropertyNames.VERMONT_AVE);
        tiles[9] = new Property(PropertyNames.CONNECTICUT_AVE);
        tiles[10] = new Tile(TileTypes.PARKING);
        tiles[11] = new Property(PropertyNames.ST_CHARLES_PL);
        tiles[12] = new Utility(150, "Electric Company");
        tiles[13] = new Property(PropertyNames.STATES_AVE);
        tiles[14] = new Property(PropertyNames.VIRGINIA_AVE);
        tiles[15] = new Utility(200, "Railroad 2");
        tiles[16] = new Property(PropertyNames.ST_JAMES_PL);
        tiles[17] = new CommunityTile();
        tiles[18] = new Property(PropertyNames.TENNESSEE_AVE);
        tiles[19] = new Property(PropertyNames.NEW_YORK_AVE);
        tiles[20] = new Tile(TileTypes.PARKING);
        tiles[21] = new Property(PropertyNames.KENTUCKY_AVE);
        tiles[22] = new ChanceTile();
        tiles[23] = new Property(PropertyNames.INDIANA_AVE);
        tiles[24] = new Property(PropertyNames.ILLINOIS_AVE);
        tiles[25] = new Utility(200, "Railroad 3");
        tiles[26] = new Property(PropertyNames.ATLANTIC_AVE);
        tiles[27] = new Property(PropertyNames.VENTNOR_AVE);
        tiles[28] = new Utility(150, "Water Works");
        tiles[29] = new Property(PropertyNames.MARVIN_GAR);
        tiles[30] = new Tile(TileTypes.GOTOJAIL);
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

    private void bankRuptcy() {

    }

    /**
     * Paints the board Panel and updates the positions of players on the board when they move.
     */
    private void paintBoardPanel() {
        northPanel.removeAll();
        southPanel.removeAll();
        eastPanel.removeAll();
        westPanel.removeAll();

        // North Panel Filler - Needed so that there is always space between grid positions
        for (int i = 0; i < 10; i++) {
            GridBagConstraints northPanelConstraints = new GridBagConstraints();
            northPanelConstraints.gridx = i;

            //Set spacing between grid spaces
            if (i == 0) {
                northPanelConstraints.insets = new Insets(0, (int) (IMAGE_WIDTH * (0.153) + 80), 0,  0); // 0.153
            } else {
                northPanelConstraints.insets = new Insets(0, (int) (IMAGE_WIDTH * (0.08 / 2)), 0, (int) (IMAGE_WIDTH * (0.08 / 2))); // 0.0918
            }

            northPanel.add(Box.createHorizontalBox(), northPanelConstraints); // Empty space
        }

        // South Panel Filler
        for (int i = 0; i < 10; i++) {
            GridBagConstraints southPanelConstraints = new GridBagConstraints();
            southPanelConstraints.gridx = i;

            //
            if (i == 0) {
                southPanelConstraints.insets = new Insets(0, 0, 0,  (int) (IMAGE_WIDTH * (0.153) + 125)); // 0.153
            } else if (i == 9) {
                southPanelConstraints.insets = new Insets(0, 0, 0, 0); // 0.0918
            } else {
                southPanelConstraints.insets = new Insets(0, (int) (IMAGE_WIDTH * (0.06 / 2)), 0, (int) (IMAGE_WIDTH * (0.06 / 2))); // 0.0918
            }

            southPanel.add(Box.createHorizontalBox(), southPanelConstraints); // Empty space
        }

        // West Panel Filler
        for (int i = 0; i < 10; i++) {
            GridBagConstraints westPanelConstraints = new GridBagConstraints();
            westPanelConstraints.gridy = i;

            if (i == 9) {
                westPanelConstraints.insets = new Insets(0, 0, (int) (IMAGE_WIDTH * (0.153)) + 40,  0); // 0.153
            } else {
                westPanelConstraints.insets = new Insets((int) (IMAGE_WIDTH * (0.08 / 2)), 0, (int) (IMAGE_WIDTH * (0.08 / 2)), 0); // 0.0918
            }

            eastPanel.add(Box.createHorizontalGlue(), westPanelConstraints);
            westPanel.add(Box.createHorizontalGlue(), westPanelConstraints);
        }

        // East Panel Filler
        for (int i = 0; i < 10; i++) {
            GridBagConstraints eastPanelConstraints = new GridBagConstraints();
            eastPanelConstraints.gridy = i;

            if (i == 0) {
                eastPanelConstraints.insets = new Insets((int) (IMAGE_WIDTH * (0.153) + 40), 0, 0,  0); // 0.153
            } else {
                eastPanelConstraints.insets = new Insets((int) (IMAGE_WIDTH * (0.08 / 2)), 0, (int) (IMAGE_WIDTH * (0.08 / 2)), 0); // 0.0918
            }

            eastPanel.add(Box.createHorizontalGlue(), eastPanelConstraints);
        }

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 11;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 80, 0, 0); // Forces left side to always be 80 width
        westPanel.add(Box.createHorizontalGlue(), gbc);
        gbc.insets = new Insets(0, 0, 0, 0);

        for (Player player : players) {
            if (player == null) break; // TODO Make this take account of player out
            JLabel playerLabel = new JLabel(player.getName());
            playerLabel.setPreferredSize(new Dimension(80, 20));

            gbc.gridx = 0;
            gbc.gridy = 0;

            int pos = player.getPosition();
            if (pos >= 0 && pos <= 9) { // Bottom (South Panel)
                gbc.gridx = pos;
                southPanel.add(playerLabel, gbc);
            } else if (pos >= 10 && pos <= 19) { // Left (West Panel)
                gbc.gridy = (19 - pos) % 10;
                westPanel.add(playerLabel, gbc);
            } else if (pos >= 20 && pos <= 29) { // Top (North Panel)
                gbc.gridx = pos % 10;
                northPanel.add(playerLabel, gbc);
            } else if (pos >= 30 && pos <= 39) { // Right (East Panel)
                gbc.gridy = pos % 10;
                eastPanel.add(playerLabel, gbc);
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
     * position, and a list of
     */
    private void paintPlayerSidePanel() {
        clearSideBarPanel();

        // Get details about the player to display to user on the side panel
        String playerName = players[currentPlayer].getName();
        int playerMoney = players[currentPlayer].getMoney();
        int playerPosition = players[currentPlayer].getPosition();
        JLabel playerNameLabel = new JLabel("Player Name: " + playerName);
        JLabel playerMoneyLabel = new JLabel("Player Money: " + playerMoney);
        JLabel playerPositionLabel = new JLabel("Player Position: " + playerPosition);

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
     * @author Nevin Fullerton
     */
    private void clearSideBarPanel() {
        sideBarPanel.removeAll();
    }

    /**
     * @author Nevin Fullerton
     */
    private void paintStandardButtonFrame() {
        clearActionPanel();

        JButton rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (diceRolled) {
                    JOptionPane.showMessageDialog(boardPanel, "You have already rolled this turn.");
                } else {
                    rollDice();
                }
            }
        });

        JButton buyHousesButton = buyAndSellHouseButton();
        JButton mortgageButton = mortgagePropertyButton();
        JButton endTurnButton = new JButton("End Turn");
        endTurnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!diceRolled) {
                    JOptionPane.showMessageDialog(boardPanel, "You must roll the dice before you end the turn.");
                } else {
                    endTurn();
                }
            }

        });

        actionPanel.add(rollDiceButton);
        actionPanel.add(buyHousesButton);
        actionPanel.add(mortgageButton);
        actionPanel.add(endTurnButton);

        actionPanel.revalidate();
        actionPanel.repaint();
    }

    /**
     * Displays the button the player is able to press if they are in jail
     */
    private void paintJailButtonFrame() {
        clearActionPanel();

        JButton rollDiceButton = new JButton("Roll Doubles to get out");
        rollDiceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (diceRolled) {
                    JOptionPane.showMessageDialog(boardPanel, "You have already rolled this turn.");
                } else {
                    rollJailDice();
                }
            }
        });

        JButton payFineButton = new JButton("Pay Fine and roll dice");
        payFineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(boardPanel, "You paid a fine of $50 to get out.");
                players[currentPlayer].setInJail(false);
                players[currentPlayer].setTurnsInJail(0);
                players[currentPlayer].subMoney(50);

                rollDice();
                paintStandardButtonFrame();
            }
        });

        JButton buyHousesButton = buyAndSellHouseButton();
        JButton mortgageButton = mortgagePropertyButton();
        JButton endTurnButton = new JButton("End Turn");
        endTurnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (players[currentPlayer].getTurnsInJail() == 3) {
                    JOptionPane.showMessageDialog(boardPanel, "You must pay fine and roll dice before you end the turn, or use other methods if available.");
                } else {
                    endTurn();
                }
            }

        });

        actionPanel.add(rollDiceButton);
        actionPanel.add(payFineButton);
        actionPanel.add(buyHousesButton);
        actionPanel.add(mortgageButton);
        actionPanel.add(endTurnButton);

        actionPanel.revalidate();
        actionPanel.repaint();
    }

    /**
     *
     * @return
     */
    private JButton buyAndSellHouseButton() {
        JButton button = new JButton("Buy/Sell House");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Property propertySelected = (Property) propertiesList.getSelectedValue();

                if (propertySelected == null) {
                    JOptionPane.showMessageDialog(boardPanel, "You must select a property from the list on the right side of the screen.");
                } else {
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
     *
     * @return
     */
    private JButton mortgagePropertyButton() {
        JButton mortgageButton = new JButton("Mortgage Property");
        mortgageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Property propertySelected = (Property) propertiesList.getSelectedValue();

                if (propertySelected == null) {
                    JOptionPane.showMessageDialog(boardPanel, "You must select a property from the list on the right side of the screen.");
                } else {
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
        /*
        Player selects property then clicks button
        Checks if any property of the same color has houses
        if not allow player to mortgage property for half of buy value
        mark property as mortgaged
        do same thing backwards, but make player pay 10% interest to buy back
         */

        return mortgageButton;
    }

    /**
     * @author Nevin Fullerton
     */
    private void clearActionPanel() {
        actionPanel.removeAll();
    }

    public static void main(String[] args) {
        new GUI();
    }
}
