package propertynmoney;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private final JPanel boardPanel;
    private final JPanel sideBarPanel;
    private final JPanel actionPanel;

    GUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Property N Money");
        this.setSize(1000, 900);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());
        final ImageIcon gameBoard = new ImageIcon("src/GameBoard.png");

        // Construct Board Panel
        boardPanel = new JPanel();
        JLabel boardLabel = new JLabel("Board");
        boardLabel.setIcon(gameBoard);
        boardLabel.setBounds(0, 0, gameBoard.getIconWidth(), gameBoard.getIconHeight());
        System.out.println(gameBoard.getIconWidth());
        boardLabel.setVisible(true);
        boardPanel.add(boardLabel);

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

        this.setVisible(true);
    }

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

    private void clearSideBarPanel() {
        sideBarPanel.removeAll();
    }

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

    private void clearActionPanel() {
        actionPanel.removeAll();
    }

    private void paintPlayerSidePanel() {
        clearSideBarPanel();

        JLabel playerNameLabel = new JLabel("Player Name:");
        JLabel playerMoneyLabel = new JLabel("Player Money:");

        // Example properties (replace with your actual property list)
        Property[] properties = {
                new Property(100, Property.Name.MEDITERRANEAN_AVE, Property.PropertyColor.BROWN),
                new Property(200, Property.Name.BALTIC_AVE, Property.PropertyColor.CYAN),
                new Property(300, Property.Name.ORIENTAL_AVE, Property.PropertyColor.MAGENTA),
                new Property(400, Property.Name.VERMONT_AVE, Property.PropertyColor.ORANGE),
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
