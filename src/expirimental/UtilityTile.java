package expirimental;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UtilityTile extends TileAbstract {
    private JLabel ownerIconL;
    private JLabel nameLabel;
    private JLabel iconLabel;
    private final Icon utilityIcon;
    private final String utilityName;
    private JPanel top;

    public UtilityTile(String name, String spritePath, int spriteIndex) {
        super();
        this.utilityName = name;
        this.utilityIcon = extractSprite(spritePath, spriteIndex);
        paintTile();
    }

    @Override
    protected JPanel createTop() {
        top = new JPanel();
        top.setLayout(new BorderLayout());
        //build spot for owner Icon
        ownerIconL = new JLabel( "", SwingConstants.CENTER);
        ownerIconL.setIcon(new ColorIcon(super.bgColor, 50));
        ownerIconL.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        iconLabel = new JLabel("", SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(20,20));
        iconLabel.setIcon(utilityIcon);

        //build spot to show the property Name
        nameLabel = new JLabel(utilityName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.WHITE);

        top.setBackground(new Color(100, 255, 255));
        top.add(ownerIconL, BorderLayout.NORTH);
        top.add(iconLabel, BorderLayout.CENTER);
        top.add(nameLabel, BorderLayout.SOUTH);

        return top;
    }

    /**
     * Set the player icon as the property owner.
     */
    public void setOwnerIcon(Icon icon) {
        ownerIconL.setIcon(icon);
    }

    public ImageIcon extractSprite(String spriteSheetPath, int iconIndex) {
        final int SPRITE_WIDTH = 40; // Each sprite's width (160px / 4 sprites per row)
        final int SPRITE_HEIGHT = 40; // Each sprite's height (120px / 3 sprites per column)
        final int SPRITES_PER_ROW = 4; // Number of sprites in each row

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
            Image scaledSprite = sprite.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledSprite);

        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if the sprite cannot be extracted
        }
    }
}
