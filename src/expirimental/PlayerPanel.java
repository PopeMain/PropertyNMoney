package expirimental;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PlayerPanel extends JPanel {
    private JLabel playerNameL;
    private JLabel playerMoneyL;
    private JLabel playerIconL;
    private DefaultListModel<PropertyNames> propertyModel;
    private JList<PropertyNames> propertyList;

    public PlayerPanel(String playerName, int startingMoney, int iconIndex, String spriteSheetPath, Icon utilityIcon) {
        setLayout(new BorderLayout(10, 10)); // Layout with gaps
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional border for separation

        // Player Name at the top
        playerNameL = new JLabel(playerName, SwingConstants.CENTER);
        playerNameL.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(playerNameL, BorderLayout.NORTH);

        // Center panel for money and icon
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        playerMoneyL = new JLabel("Money: $" + startingMoney, SwingConstants.CENTER);
        playerMoneyL.setFont(new Font("SansSerif", Font.PLAIN, 14));
        centerPanel.add(playerMoneyL);

        // Player icon from the sprite sheet
        playerIconL = new JLabel("", SwingConstants.CENTER);
        playerIconL.setIcon(extractSprite(spriteSheetPath, iconIndex));
        centerPanel.add(playerIconL);

        add(centerPanel, BorderLayout.CENTER);

        // Property list at the bottom
        propertyModel = new DefaultListModel<>();
        propertyList = new JList<>(propertyModel); // Property list uses the DefaultListModel
        propertyList.setCellRenderer(new PropertyListRenderer(utilityIcon)); // Set custom renderer
        JScrollPane propertyScrollPane = new JScrollPane(propertyList);
        add(propertyScrollPane, BorderLayout.SOUTH);
    }

    /**
     * Update the player's money display.
     */
    public void updateMoney(int newAmount) {
        playerMoneyL.setText("Money: $" + newAmount);
    }

    /**
     * Add a property to the player's property list.
     */
    public void addProperty(PropertyNames property) {
        propertyModel.addElement(property);
    }

    /**
     * Remove a property from the player's property list.
     */
    public void removeProperty(PropertyNames property) {
        propertyModel.removeElement(property);
    }

    public Icon getPlayerIconL() {
        return playerIconL.getIcon();
    }

    /**
     * Extract a specific icon from the sprite sheet.
     *
     * @param spriteSheetPath Path to the sprite sheet image.
     * @param iconIndex       The index of the icon (row-wise, 0-8).
     * @return An ImageIcon containing the extracted icon.
     */
    private ImageIcon extractSprite(String spriteSheetPath, int iconIndex) {
        final int SPRITE_WIDTH = 300; // Each sprite's width (900px / 3 sprites per row)
        final int SPRITE_HEIGHT = 237; // Each sprite's height (711px / 3 sprites per column)
        final int SPRITES_PER_ROW = 3; // Number of sprites in each row

        try {
            // Load the sprite sheet
            BufferedImage spriteSheet = ImageIO.read(new File(spriteSheetPath));

            // Calculate the row and column of the icon
            int row = iconIndex / SPRITES_PER_ROW;
            int col = iconIndex % SPRITES_PER_ROW;

            // Extract the specific sprite
            BufferedImage sprite = spriteSheet.getSubimage(
                    col * SPRITE_WIDTH,
                    row * SPRITE_HEIGHT,
                    SPRITE_WIDTH,
                    SPRITE_HEIGHT
            );

            // Scale the sprite to a smaller size for display
            Image scaledSprite = sprite.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledSprite);

        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if the sprite cannot be extracted
        }
    }

}
