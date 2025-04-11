package expirimental;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract class TileAbstract extends JPanel {
    protected JPanel topPanel;
    protected JPanel bottomPanel;
    private JPanel playerBox;
    private Icon[] players;
    public final Color bgColor = new Color(100, 255, 255);

    public TileAbstract() {
    }
    /**
     * Fill the top panel with class specific render
     */
    protected abstract JPanel createTop();

    public JPanel createBottom() {
        playerBox = new JPanel(new GridLayout(2,2));
        playerBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerBox.setBackground(bgColor);

        //placeholders for player icons
        for (int i = 0; i < 4; i++) {
            JLabel playerIcon = new JLabel("", SwingConstants.CENTER);
            playerIcon.setIcon(players[i]);
            playerIcon.setHorizontalAlignment(SwingConstants.CENTER);
            playerIcon.setVerticalAlignment(SwingConstants.CENTER);
            playerBox.add(playerIcon);
        }
        return playerBox;
    }

    public void paintTile() {
        setLayout(new GridBagLayout());
        setSize(60, 60);
        GridBagConstraints c = new GridBagConstraints();

        //Initialize Player Array
        players = new Icon[4];
        for (int i = 0; i < 4; i++) {
            players[i] = new ColorIcon(bgColor, 50);
        }

        //Initialize panels
        topPanel = createTop();
        bottomPanel = createBottom();

        //top Panel Values
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        add(topPanel, c);

        //Bottom Panel values
        c.gridy = 1;
        c.weighty = 0.5;
        add(bottomPanel, c);
    }

    public void updatePlayerIcons(){
        playerBox.removeAll();
        for (int i = 0; i < 4; i++) {
            JLabel playerIcon = new JLabel("", SwingConstants.CENTER);
            playerIcon.setIcon(players[i]);
            playerIcon.setHorizontalAlignment(SwingConstants.CENTER);
            playerIcon.setVerticalAlignment(SwingConstants.CENTER);
            playerBox.add(playerIcon);
        }
        revalidate();
        repaint();
    }

    public void addPlayer(Icon playerIcon, int index){
        players[index] = playerIcon;
        updatePlayerIcons();
    }

    public void removePlayer(int index){
        players[index] = new ColorIcon(bgColor, 50);
        updatePlayerIcons();
    }

    public ImageIcon extractSprite(String spriteSheetPath, int iconIndex) {
        final int SPRITE_WIDTH = 25; // Each sprite's width (900px / 3 sprites per row)
        final int SPRITE_HEIGHT = 25; // Each sprite's height (711px / 3 sprites per column)
        final int SPRITES_PER_ROW = 1; // Number of sprites in each row

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
