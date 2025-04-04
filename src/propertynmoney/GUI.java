package propertynmoney;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Constructs and displays the GUI **TODO**
 * @author Nevin Fullerton and Frank Pope
 */

public class GUI extends JFrame {

    private final JPanel boardPanel;
    private final JPanel sideBarPanel;
    private final JPanel actionPanel;
    private final Bank theBank = new Bank();

    private Player[] players;
    private int currentPlayer;
    private int amountOfPlayers; // Amount of players - 1
    private boolean diceRolled;
    private int doubleAmount;

    private Tile[] tiles;

    // Panels that hold the icons of each player, to show their location on the board
    private JPanel northPanel;
    private JPanel northPanelHolder;
    private JPanel southPanel;
    private JPanel southPanelHolder;
    private JPanel eastPanel;
    private JPanel eastPanelHolder;
    private JPanel westPanel;
    private JPanel westPanelHolder;

    private final int IMAGEWIDTH;

    /**
     * @author Nevin Fullerton
     */
    GUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Property N Money");
        this.setSize(1000, 900);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        final ImageIcon gameBoard = new ImageIcon("src/GameBoard_Resized.png");
        players = new Player[8];
        players[0] = new Player(1500, "Nevin"); // Testing ** Remove when done
        players[1] = new Player(1500, "Frank");
        players[2] = new Player(1500, "Nathan");
        players[0].movePosition(1); // Testing ** Remove when done
        players[1].movePosition(9);

        currentPlayer = 0;
        amountOfPlayers = 2;

        IMAGEWIDTH = gameBoard.getIconWidth(); // Width same as

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
     * Produces two different integer values, each from 1-6, and moves the current player by the sum of the dice.
     * It will then disable rolling for the current player, and allow them to end turn and other actions If the two
     * integers have equals values, allow player to roll dice again, but if the player has three doubles in a row,
     * put them in jail for speeding.
     */
    private void rollDice() {
        Random diceRand = new Random();
        int dice1 = diceRand.nextInt(1,6);
        int dice2 = diceRand.nextInt(1,6);
        players[currentPlayer].movePosition(dice1 + dice2);
        diceRolled = true;
        // TODO Doubles
        determineMovementResult();
        paintBoardPanel();
        paintPlayerSidePanel();
    }

    /**
     * Will end the turn of the current player and pass onto the next player that is not eligible, making them the
     * current player. The side panel is then updated to show the new current player's money, properties, name,
     * and position.
     */
    private void endTurn() {
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
            if (property.isOwned()) {
                JOptionPane.showMessageDialog(this, "You must pay " + property.getRentValue() + " to stay here.");
                player.subMoney(property.getRentValue());
            } else {
                if (property.getBuyValue() > player.getMoney()) {
                    JOptionPane.showMessageDialog(this, "You don't have enough money to buy this property.");
                } else {
                    int result = JOptionPane.showConfirmDialog(this, "Do you wish to buy property for " + property.getBuyValue() + " ?");
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
        } else if (tile.getTileType() == TileTypes.UTILITY) {
            Utility utility = (Utility) tile;
            if (utility.isOwned()) {
                JOptionPane.showMessageDialog(this, "You must pay " + utility.getRentValue() + " to stay here.");
                player.subMoney(utility.getRentValue());
            } else {
                if (utility.getBuyValue() > player.getMoney()) {
                    JOptionPane.showMessageDialog(this, "You don't have enough money to buy this utility.");
                } else {
                    int result = JOptionPane.showConfirmDialog(this, "Do you wish to buy the utility for " + utility.getBuyValue() + " ?");
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

        paintPlayerSidePanel();
    }

    /**
     *
     */
    private void buyProperty() {

    }

    /**
     * Sets up the tile array manually so that when the player moves, the game can figure out actions to do afterward.
     */
    private void setUpTiles() {
        tiles = new Tile[40];
        tiles[0] = new Tile(TileTypes.PARKING);
        tiles[1] = new Property(60, PropertyNames.MEDITERRANEAN_AVE, PropertyColors.BROWN);
        tiles[2] = new Tile(TileTypes.COMMUNITYCHEST); // Placeholder
        tiles[3] = new Property(60, PropertyNames.BALTIC_AVE, PropertyColors.BROWN);
        tiles[4] = new Tile(TileTypes.TAX); // Placeholder
        tiles[5] = new Utility(200, "RailRoad 1");
        tiles[6] = new Property(100, PropertyNames.ORIENTAL_AVE, PropertyColors.CYAN);
        tiles[7] = new Tile(TileTypes.CHANCE); // Placeholder
        tiles[8] = new Property(100, PropertyNames.VERMONT_AVE, PropertyColors.CYAN);
        tiles[9] = new Property(120, PropertyNames.CONNECTICUT_AVE, PropertyColors.CYAN);
        tiles[10] = new Tile(TileTypes.PARKING);
        tiles[11] = new Property(140, PropertyNames.ST_CHARLES_PL, PropertyColors.MAGENTA);
        tiles[12] = new Utility(150, "Electric Company");
        tiles[13] = new Property(140, PropertyNames.STATES_AVE, PropertyColors.MAGENTA);
        tiles[14] = new Property(160, PropertyNames.VIRGINIA_AVE, PropertyColors.MAGENTA);
        tiles[15] = new Utility(200, "Railroad 2");
        tiles[16] = new Property(180, PropertyNames.ST_JAMES_PL, PropertyColors.ORANGE);
        tiles[17] = new Tile(TileTypes.COMMUNITYCHEST); // Placeholder
        tiles[18] = new Property(180, PropertyNames.TENNESSEE_AVE, PropertyColors.ORANGE);
        tiles[19] = new Property(200, PropertyNames.NEW_YORK_AVE, PropertyColors.ORANGE);
        tiles[20] = new Tile(TileTypes.PARKING);
        tiles[21] = new Property(220, PropertyNames.KENTUCKY_AVE, PropertyColors.RED);
        tiles[22] = new Tile(TileTypes.CHANCE); // Placeholder
        tiles[23] = new Property(220, PropertyNames.INDIANA_AVE, PropertyColors.RED);
        tiles[24] = new Property(240, PropertyNames.ILLINOIS_AVE, PropertyColors.RED);
        tiles[25] = new Utility(200, "Railroad 3");
        tiles[26] = new Property(260, PropertyNames.ATLANTIC_AVE, PropertyColors.YELLOW);
        tiles[27] = new Property(260, PropertyNames.VENTNOR_AVE, PropertyColors.YELLOW);
        tiles[28] = new Utility(150, "Water Works");
        tiles[29] = new Property(280, PropertyNames.MARVIN_GAR, PropertyColors.YELLOW);
        tiles[30] = new Tile(TileTypes.GOTOJAIL); // Placeholder
        tiles[31] = new Property(300, PropertyNames.PACIFIC_AVE, PropertyColors.GREEN);
        tiles[32] = new Property(300, PropertyNames.NORTH_CAROLINA_AVE, PropertyColors.GREEN);
        tiles[33] = new Tile(TileTypes.COMMUNITYCHEST); // Placeholder
        tiles[34] = new Property(320, PropertyNames.PENNSYLVANIA_AVE, PropertyColors.GREEN);
        tiles[35] = new Utility(200, "Railroad 4");
        tiles[36] = new Tile(TileTypes.CHANCE); // Placeholder
        tiles[37] = new Property(350, PropertyNames.PARK_PL, PropertyColors.BLUE);
        tiles[38] = new Tile(TileTypes.TAX); // Placeholder
        tiles[39] = new Property(400, PropertyNames.BOARDWALK, PropertyColors.BLUE);
    }

    /**
     * Paints the board Panel and updates the positions of players on the board when they move
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
                northPanelConstraints.insets = new Insets(0, (int) (IMAGEWIDTH * (0.153) + 80), 0,  0); // 0.153
            } else {
                northPanelConstraints.insets = new Insets(0, (int) (IMAGEWIDTH * (0.08 / 2)), 0, (int) (IMAGEWIDTH * (0.08 / 2))); // 0.0918
            }

            northPanel.add(Box.createHorizontalBox(), northPanelConstraints); // Empty space
        }

        // South Panel Filler
        for (int i = 0; i < 10; i++) {
            GridBagConstraints southPanelConstraints = new GridBagConstraints();
            southPanelConstraints.gridx = i;

            //
            if (i == 0) {
                southPanelConstraints.insets = new Insets(0, 0, 0,  (int) (IMAGEWIDTH * (0.153) + 125)); // 0.153
            } else if (i == 9) {
                southPanelConstraints.insets = new Insets(0, 0, 0, 0); // 0.0918
            } else {
                southPanelConstraints.insets = new Insets(0, (int) (IMAGEWIDTH * (0.06 / 2)), 0, (int) (IMAGEWIDTH * (0.06 / 2))); // 0.0918
            }

            southPanel.add(Box.createHorizontalBox(), southPanelConstraints); // Empty space
        }

        // West Panel Filler
        for (int i = 0; i < 10; i++) {
            GridBagConstraints westPanelConstraints = new GridBagConstraints();
            westPanelConstraints.gridy = i;

            if (i == 9) {
                westPanelConstraints.insets = new Insets(0, 0, (int) (IMAGEWIDTH * (0.153)) + 40,  0); // 0.153
            } else {
                westPanelConstraints.insets = new Insets((int) (IMAGEWIDTH * (0.08 / 2)), 0, (int) (IMAGEWIDTH * (0.08 / 2)), 0); // 0.0918
            }

            eastPanel.add(Box.createHorizontalGlue(), westPanelConstraints);
            westPanel.add(Box.createHorizontalGlue(), westPanelConstraints);
        }

        // East Panel Filler
        for (int i = 0; i < 10; i++) {
            GridBagConstraints eastPanelConstraints = new GridBagConstraints();
            eastPanelConstraints.gridy = i;

            if (i == 0) {
                eastPanelConstraints.insets = new Insets((int) (IMAGEWIDTH * (0.153) + 40), 0, 0,  0); // 0.153
            } else {
                eastPanelConstraints.insets = new Insets((int) (IMAGEWIDTH * (0.08 / 2)), 0, (int) (IMAGEWIDTH * (0.08 / 2)), 0); // 0.0918
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

        String playerName = players[currentPlayer].getName();
        int playerMoney = players[currentPlayer].getMoney();
        int playerPosition = players[currentPlayer].getPosition();
        JLabel playerNameLabel = new JLabel("Player Name: " + playerName);
        JLabel playerMoneyLabel = new JLabel("Player Money: " + playerMoney);
        JLabel playerPositionLabel = new JLabel("Player Position: " + playerPosition);

        List<Property> properties = new ArrayList<Property>();
        List<Utility> utilities = new ArrayList<Utility>();

        properties = players[currentPlayer].getProperties();
        utilities = players[currentPlayer].getUtilities();

        List<Object> assets = new ArrayList<Object>();

        for (Property property : properties)
            assets.add(property.getName());

        for (Utility utility : utilities)
            assets.add(utility.getName());

        // Create the JList of properties
        JList<Object> propertiesList = new JList<>(assets.toArray());

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

        JButton buyHousesButton = new JButton("Buy Houses");
        JButton mortgageButton = new JButton("Mortgage");
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
