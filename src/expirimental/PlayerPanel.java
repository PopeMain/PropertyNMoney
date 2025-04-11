package expirimental;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Frank Pope
 * <p>
 * PlayerPanel is a class for saving player information in a visual system to aid in game play.
 * The player's name, money and chosen icon will appear in the top part of their panel. Then in a
 * JList below that the purchased and earned properties will be rendered with an appropriate icon or color
 * </p>
 */
public class PlayerPanel extends JPanel {
    private JLabel playerNameL;
    private JLabel playerMoneyL;
    private JLabel playerIconL;
    private DefaultListModel<PropertyNames> propertyModel;
    private JList<PropertyNames> propertyList;

    /**
     * TODO MAKE MORE TECHNICAL
     * get's the important stuff for the playerPanel, then builds the Graphical interface.
     * @param playerName submitted name for the player of this panel
     * @param startingMoney the inital amount of money players will start with
     * @param iconIndex the indexed location for the picture the player wants as their icon from a spritesheet
     * @param spriteSheetPath A string that points to the file location for the sprite sheet
     * @param utilityIcon The special rendering options for owned properties.
     */
    public PlayerPanel(String playerName, int startingMoney, int iconIndex, String spriteSheetPath, Icon utilityIcon) {
        setLayout(new BorderLayout(10, 10)); // Layout with gaps
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional border for separation

        JPanel playerInfoP = new JPanel();
        playerInfoP.setLayout(new BorderLayout());
        playerInfoP.setBorder(BorderFactory.createEmptyBorder(10,25,10,25));
        // Player Name at the top
        playerNameL = new JLabel(playerName, SwingConstants.CENTER);
        playerNameL.setFont(new Font("SansSerif", Font.BOLD, 10));
        playerNameL.setHorizontalAlignment(SwingConstants.CENTER);
        playerInfoP.add(playerNameL, BorderLayout.NORTH);

        // Center panel for money and icon
        playerMoneyL = new JLabel("Money: $" + startingMoney, SwingConstants.CENTER);
        playerMoneyL.setFont(new Font("SansSerif", Font.PLAIN, 10));
        playerMoneyL.setHorizontalAlignment(SwingConstants.CENTER);
        playerInfoP.add(playerMoneyL,BorderLayout.CENTER);


        // Player icon from the sprite sheet
        playerIconL = new JLabel("", SwingConstants.CENTER);
        playerIconL.setIcon(extractSprite(spriteSheetPath, iconIndex));
        playerIconL.setHorizontalAlignment(SwingConstants.CENTER);
        playerInfoP.add(playerIconL, BorderLayout.SOUTH);

        // Add playerInfoPanel to the top of the main panel
        add(playerInfoP, BorderLayout.NORTH);


        // Property list at the bottom
        propertyModel = new DefaultListModel<>();
        propertyList = new JList<>(propertyModel); // Property list uses the DefaultListModel
        propertyList.setCellRenderer(new PropertyListRenderer(utilityIcon)); // Set custom renderer
        JScrollPane propertyScrollPane = new JScrollPane(propertyList);
        add(propertyScrollPane, BorderLayout.CENTER);
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

    public void addUtility(String name, Icon utilityIcon) {
        propertyList.setCellRenderer(new PropertyListRenderer(utilityIcon));
    }

    /**
     * Extract a specific icon from the sprite sheet.
     *
     * @param spriteSheetPath Path to the sprite sheet image.
     * @param iconIndex       The index of the icon left to right top to bottom
     * @return An ImageIcon containing the chosen ImageIcon.
     */
    private ImageIcon extractSprite(String spriteSheetPath, int iconIndex) {
        final int SPRITE_WIDTH = 300; // Each sprite's width (900px / 3 sprites per row) = 300px
        final int SPRITE_HEIGHT = 237; // Each sprite's height (711px / 3 sprites per column) = 237px
        final int SPRITES_PER_ROW = 3; // Number of sprites in each row

        try {
            // Load the sprite sheet
            BufferedImage spriteSheet = ImageIO.read(new File(spriteSheetPath));

            // Calculate the row and column of the icon
            int row = iconIndex / SPRITES_PER_ROW;
            int col = iconIndex % SPRITES_PER_ROW;

            // get the specific sprite
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
            //TODO MAKE THIS MORE SPECIFIC
            e.printStackTrace();
            return null; // Return null if the sprite cannot be extracted
        }
    }

}
