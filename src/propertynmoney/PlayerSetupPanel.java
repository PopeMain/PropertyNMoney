package propertynmoney;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerSetupPanel extends JPanel {
    private final int PLAYER_COUNT = 4; // Number of players
    private final int DEFAULT_STARTING_MONEY = 1500; // Default starting money
    private final ImageIcon[] icons; // Array of icons for the selector
    private Player[] players;
    private JPanel[] playerPanels;
    private JPanel setupPanel;

    // UI components for each player
    private JTextField[] nameFields;
    private JSpinner[] moneySpinners;
    private int[] iconIndices;
    private JRadioButton[] radioButtons;

    public PlayerSetupPanel(StartGame frame) {
        // Load the sprite sheet and create an array of icons (Example Array)
        icons = loadPlayerIcons();


        // Set the main Layout
        setLayout(new GridLayout(2, 1, 10, 10));

        // Set the layout with 4 columns
        setupPanel = new JPanel();
        setupPanel.setLayout(new GridLayout(1, PLAYER_COUNT, 10, 10)); // Row per player, spacing=10px

        // Initialize arrays for UI components
        nameFields = new JTextField[PLAYER_COUNT];
        moneySpinners = new JSpinner[PLAYER_COUNT];
        iconIndices = new int[PLAYER_COUNT];
        radioButtons = new JRadioButton[PLAYER_COUNT];

        // Create a column for each player
        playerPanels = new JPanel[PLAYER_COUNT];
        for (int i = 0; i < PLAYER_COUNT; i++) {
            playerPanels[i] = createPlayerSetupColumn(i);
            setupPanel.add(playerPanels[i]);
        }
        add(setupPanel);
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            // Switch to Game Panel
            createPlayers();
            frame.playGame(players);
        });

        add(startButton);
    }

    private JPanel createPlayerSetupColumn(int playerNumber) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(3, 1, 5, 5)); // 3 Rows for Name, Money, Icon Selector
        playerPanel.setBorder(BorderFactory.createTitledBorder("Player " + (playerNumber + 1))); // Panel title for clarity

        // Row 1: Player Name Input
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(10);
        // Store reference to the name field
        nameFields[playerNumber] = nameField;
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        // Row 2: Starting Money Input
        JPanel moneyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel moneyLabel = new JLabel("Starting Money:");
        JSpinner moneySpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_STARTING_MONEY, 100, 1000000, 100));
        // Store reference to the money spinner
        moneySpinners[playerNumber] = moneySpinner;
        moneyPanel.add(moneyLabel);
        moneyPanel.add(moneySpinner);

        // Row 3: Player On switch

        // Row 4: Icon Selector
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel iconLabel = new JLabel("Icon:");
        JLabel iconDisplay = new JLabel(icons[0]); // Display the first icon by default
        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        // Initialize the icon index for this player
        iconIndices[playerNumber] = 0;

        // Add functionality to the selector buttons
        previousButton.addActionListener(e -> {
            iconIndices[playerNumber] = (iconIndices[playerNumber] - 1 + icons.length) % icons.length; // Decrement, wrap around
            iconDisplay.setIcon(icons[iconIndices[playerNumber]]);
        });
        nextButton.addActionListener(e -> {
            iconIndices[playerNumber] = (iconIndices[playerNumber] + 1) % icons.length; // Increment, wrap around
            iconDisplay.setIcon(icons[iconIndices[playerNumber]]);
        });

        iconPanel.add(iconLabel);
        iconPanel.add(iconDisplay);
        iconPanel.add(previousButton);
        iconPanel.add(nextButton);

        // Add rows to the playerPanel
        playerPanel.add(namePanel);
        playerPanel.add(moneyPanel);
        playerPanel.add(iconPanel);

        return playerPanel;
    }

    private void createPlayers() {
        players = new Player[PLAYER_COUNT];
        for(int i = 0; i < PLAYER_COUNT; i++) {
            players[i] = new Player((Integer) moneySpinners[i].getValue(), nameFields[i].getText(), iconIndices[i]);
        }
    }

    private ImageIcon[] loadPlayerIcons() {
        // Simulated icons for demonstration (replace with actual sprites)
        ImageIcon[] icons = new ImageIcon[9];
        for (int i = 0; i < icons.length; i++) {
            icons[i] = extractSprite("src/ppSprite.png", i);
        }
        return icons;
    }

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
