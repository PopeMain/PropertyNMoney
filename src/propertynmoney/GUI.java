package propertynmoney;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private Player currentPlayer;

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

        currentPlayer = players[0];

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
     * @author Nevin Fullerton
     * @return void
     */
    private void rollDice() {
        Random diceRand = new Random();
        int dice1 = diceRand.nextInt(1,6);
        int dice2 = diceRand.nextInt(1,6);
        currentPlayer.movePosition(dice1 + dice2);
        // TODO Doubles
        paintBoardPanel();
    }

    /**
     * Paints the board Panel and updates the positions of players on the board when they move
     * @author: Nevin Fullerton
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
     * @author Nevin Fullerton
     */
    private void paintPlayerSidePanel() {
        clearSideBarPanel();

        JLabel playerNameLabel = new JLabel("Player Name:");
        JLabel playerMoneyLabel = new JLabel("Player Money:");
        JLabel playerPositionLabel = new JLabel("Player Position:");

        List<Property> properties = new ArrayList<Property>();

        //UPDATE to Current Player
        //...........VVVVVVV......
        properties = theBank.getProperties();
        // Create the JList of properties
        JList<Object> propertiesList = new JList<>(properties.toArray());

        // Set a custom cell renderer for the list
        propertiesList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (renderer instanceof JLabel && value instanceof Property) {
                    JLabel label = (JLabel) renderer;
                    Property property = (Property) value;

                    // Set property name as the text
                    label.setText(property.getName());

                    // Add a colored icon to represent the property color
                    label.setIcon(new ColorIcon(property.getColor()));
                }

                return renderer;
            }
        });

        sideBarPanel.add(playerNameLabel);
        sideBarPanel.add(playerMoneyLabel);
        sideBarPanel.add(playerPositionLabel);
        sideBarPanel.add(new JScrollPane(propertiesList));

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
                rollDice();
            }
        });

        JButton buyHousesButton = new JButton("Buy Houses");
        JButton mortgageButton = new JButton("Mortgage");
        JButton endTurnButton = new JButton("End Turn");

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
