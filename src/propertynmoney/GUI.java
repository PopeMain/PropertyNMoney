package propertynmoney;

import javax.swing.*;
import java.awt.*;

/**
 * Constructs and displays the GUI **TODO**
 * @author Nevin Fullerton and Frank Pope
 */

public class GUI extends JFrame {

    private final JPanel boardPanel;
    private final JPanel sideBarPanel;
    private final JPanel actionPanel;

    private Player[] players;
    private Player currentPlayer;

    /**
     * @author Nevin Fullerton
     */
    GUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Property N Money");
        this.setSize(1000, 900);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        final ImageIcon gameBoard = new ImageIcon("src/GameBoard.png");
        players = new Player[8];
        players[0] = new Player(1500, "Nevin"); // Testing ** Remove when done
        players[1] = new Player(1500, "Frank");
        players[0].movePosition(5); // Testing ** Remove when done

        // Construct Board Panel
        boardPanel = new JPanel();
        JLabel boardLabel = new JLabel();
        boardPanel.setLayout(new BorderLayout());
        boardLabel.setIcon(gameBoard);
        boardLabel.setBounds(0, 0, gameBoard.getIconWidth(), gameBoard.getIconHeight());
        System.out.println(gameBoard.getIconWidth());
        boardLabel.setVisible(true);
        boardPanel.add(boardLabel, BorderLayout.CENTER);
        paintBoardPanel();

        this.add(boardPanel, BorderLayout.CENTER);

        // Construct Side Bar Panel
        sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        paintPlayerSidePanel();
        repaintPlayerSidePanel();

        this.add(sideBarPanel, BorderLayout.EAST);

        // Construct Action Panel
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
        paintStandardButtonFrame();

        this.add(actionPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    /**
     * Paints the board Panel and updates the positions of players on the board when they move
     * @author: Nevin Fullerton
     * @return: Void
     */
    private void paintBoardPanel() {
        // Make these global variables???
        JPanel northPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel eastPanel = new JPanel();

        JPanel eastPanelHolder = new JPanel();
        eastPanelHolder.setLayout(new BorderLayout());
        eastPanelHolder.add(eastPanel, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 100;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;


        southPanel.setLayout(new GridBagLayout());
        eastPanel.setLayout(new GridBagLayout());


        boardPanel.add(eastPanelHolder, BorderLayout.EAST);

        for (Player player : players) {
            if (player == null) {
                break;
            }
            else if (player.getPosition() < 10) { // TESTING
                JLabel playerLabel = new JLabel(player.getName());
                System.out.println(player.getPosition());
                gbc.gridy = player.getPosition();
                playerLabel.setPreferredSize(new Dimension(100, 50));
                eastPanel.add(playerLabel, gbc);
                eastPanel.add(new JLabel("Test"), gbc);

            }
        }
    }

    /**
     * @author Nevin Fullerton
     */
    private void paintPlayerSidePanel() {
        clearSideBarPanel();

        JLabel playerNameLabel = new JLabel("Player Name:");
        JLabel playerMoneyLabel = new JLabel("Player Money:");
        //JButton propertiesButton = new JButton("Properties");
        //JList propertiesList = new JList();

        sideBarPanel.add(playerNameLabel);
        sideBarPanel.add(playerMoneyLabel);
        //We need to add the properties list to the player panel
        //sideBarPanel.add(propertiesList);

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

    private void repaintPlayerSidePanel() {
        clearSideBarPanel();

        JLabel playerNameLabel = new JLabel("Player Name:");
        JLabel playerMoneyLabel = new JLabel("Player Money:");

        // Example properties (replace with your actual property list)
        Property[] properties = {
                new Property(100, PropertyNames.MEDITERRANEAN_AVE, PropertyColors.BROWN),
                new Property(200, PropertyNames.BALTIC_AVE, PropertyColors.CYAN),
                new Property(300, PropertyNames.ORIENTAL_AVE, PropertyColors.MAGENTA),
                new Property(400, PropertyNames.VERMONT_AVE, PropertyColors.ORANGE),
        };

        // Create the JList of properties
        JList<Property> propertiesList = new JList<>(properties);

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

        // Add the components to the sidebar
        sideBarPanel.add(playerNameLabel);
        sideBarPanel.add(playerMoneyLabel);
        sideBarPanel.add(new JScrollPane(propertiesList)); // Add the JList inside a scroll pane
    }
    public static void main(String[] args) {
        new GUI();
    }
}
