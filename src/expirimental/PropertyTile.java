package expirimental;

import javax.swing.*;
import java.awt.*;

public class PropertyTile extends TileAbstract {
    private final Color propertyColor;
    private final String propertyName;
    private final int maxHouses = 4;
    private int houseCount = 0;
    private JLabel ownerIconL;
    private JPanel housePanel;
    private JPanel top;
    private JLabel nameLabel;

    public PropertyTile(PropertyNames name) {
        super();
        propertyColor = name.getColor();
        propertyName = name.getDisplayName();
        paintTile();
    }


    public JPanel createTop() {
        top = new JPanel();
        top.setLayout(new BorderLayout());
        //build spot for owner Icon
        ownerIconL = new JLabel("", SwingConstants.CENTER);
        ownerIconL.setPreferredSize(new Dimension(50, 50)); // Size for the owner icon
        ownerIconL.setBorder(BorderFactory.createLineBorder(Color.GRAY));


        //build spot for houses
        housePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        housePanel.setOpaque(false);
        updateHousePanel();

        //build spot to show the property Name
        nameLabel = new JLabel(propertyName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.WHITE);

        top.setBackground(propertyColor);
        top.add(ownerIconL, BorderLayout.NORTH);
        top.add(housePanel, BorderLayout.CENTER);
        top.add(nameLabel, BorderLayout.SOUTH);

        return top;
    }

    /**
     * Update the house panel to display the correct number of houses.
     */
    private void updateHousePanel() {
        housePanel.removeAll(); // Clear current houses
        for (int i = 0; i < houseCount; i++) {
            JLabel house = new JLabel("");
            house.setIcon(extractSprite("src/house.png", 0));
            housePanel.add(house);
        }
        revalidate();
        repaint();
    }

    /**
     * Add a house to the property.
     */
    public void addHouse() {
        if (houseCount < maxHouses) {
            houseCount++;
            updateHousePanel();
        } else {
            //TODO Update from system print.
            System.out.println("Max houses reached!");
        }
    }

    /**
     * Remove a house from the property.
     */
    public void removeHouse() {
        if (houseCount > 0) {
            houseCount--;
            updateHousePanel();
        } else {
            //TODO Update from system print
            System.out.println("No houses to remove!");
        }
    }

    /**
     * Set the player icon as the property owner.
     */
    public void setOwnerIcon(Icon icon) {
        ownerIconL.setIcon(icon);
    }

    public Color getPropertyColor() {
        return propertyColor;
    }

    public String getPropertyName() {
        return propertyName;
    }

}
