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
    private int[] playerIconIndex;
    private JCheckBox[] playerCheckBoxes;
    private boolean[] iconTaken;

    public PlayerSetupPanel(StartGame frame) {
        // Load the sprite sheet and create an array of icons (Example Array)
        icons = loadPlayerIcons();
        iconTaken = new boolean[icons.length];

        // Set the main Layout
        setLayout(new BorderLayout(10, 10));

        // Set the layout with 4 columns
        setupPanel = new JPanel();
        setupPanel.setLayout(new GridLayout(1, PLAYER_COUNT, 10, 10)); // Row per player, spacing=10px

        // Initialize arrays for UI components
        nameFields = new JTextField[PLAYER_COUNT];
        moneySpinners = new JSpinner[PLAYER_COUNT];
        playerIconIndex = new int[PLAYER_COUNT];
        playerCheckBoxes = new JCheckBox[PLAYER_COUNT];

        // Create a column for each player
        playerPanels = new JPanel[PLAYER_COUNT];
        for (int i = 0; i < PLAYER_COUNT; i++) {
            //Spaces Default Icons set evenly across all players
            playerIconIndex[i] = (i * (icons.length / PLAYER_COUNT)) % icons.length;
            //Make those chosen Icons selected
            iconTaken[playerIconIndex[i]] = true;
            //Finish setup for the rest of the player columns
            playerPanels[i] = createPlayerSetupColumn(i, playerIconIndex[i]);
            setupPanel.add(playerPanels[i]);
        }

        add(setupPanel, BorderLayout.CENTER);
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            if (validatePlayers()) {
                // Switch to Game Panel
                createPlayers();
                frame.playGame(players);
            }
        });

        add(startButton, BorderLayout.SOUTH);
    }

    private JPanel createPlayerSetupColumn(int playerNumber, int iconIndex) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(4, 1, 5, 5)); // 3 Rows for Name, Playing, Money, Icon Selector
        playerPanel.setBorder(BorderFactory.createTitledBorder("Player " + (playerNumber + 1))); // Panel title for clarity

        // Row 1: Player Name Input
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(10);
        // Store reference to the name field
        nameFields[playerNumber] = nameField;
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        // Row 2: Player selectors
        JPanel checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        playerCheckBoxes[playerNumber] = new JCheckBox("ADD TO GAME");
        if (playerNumber == 0) {
            playerCheckBoxes[playerNumber].setSelected(true); // First player always active
        } else if (playerNumber > 1) {
            playerCheckBoxes[playerNumber].setEnabled(false); // Initially disabled for others
        }

        playerCheckBoxes[playerNumber].addItemListener(e -> {
            updateCheckBoxStates();
        });
        checkBoxPanel.add(playerCheckBoxes[playerNumber]);

        // Row 3: Starting Money Input
        JPanel moneyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel moneyLabel = new JLabel("Starting Money:");
        JSpinner moneySpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_STARTING_MONEY, 100, 1000000, 100));
        // Store reference to the money spinner
        moneySpinners[playerNumber] = moneySpinner;
        moneyPanel.add(moneyLabel);
        moneyPanel.add(moneySpinner);

        // Row 4: Icon Selector
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel iconLabel = new JLabel("Icon:");
        JLabel iconDisplay = new JLabel(icons[iconIndex]); // Display the first icon by default
        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        // Initialize the icon index for this player
        playerIconIndex[playerNumber] = iconIndex;

        // Add functionality to the selector buttons
        previousButton.addActionListener(e -> {
            int newIndex = findPreviousAvailableIcon(playerIconIndex[playerNumber]);
            if (newIndex != playerIconIndex[playerNumber]) {
                iconTaken[playerIconIndex[playerNumber]] = false; // Free up the old icon
                playerIconIndex[playerNumber] = newIndex;
                iconTaken[newIndex] = true; // Mark the new icon as taken
                iconDisplay.setIcon(icons[newIndex]);
            }
        });

        nextButton.addActionListener(e -> {
            int newIndex = findNextAvailableIcon(playerIconIndex[playerNumber]);
            if (newIndex != playerIconIndex[playerNumber]) {
                iconTaken[playerIconIndex[playerNumber]] = false; // Free up the old icon
                playerIconIndex[playerNumber] = newIndex;
                iconTaken[newIndex] = true; // Mark the new icon as taken
                iconDisplay.setIcon(icons[newIndex]);
            }
        });


        iconPanel.add(iconLabel);
        iconPanel.add(iconDisplay);
        iconPanel.add(previousButton);
        iconPanel.add(nextButton);

        // Add rows to the playerPanel
        playerPanel.add(namePanel);
        if(playerNumber > 0) {
            playerPanel.add(checkBoxPanel);
        } else {
            playerPanel.add(new JPanel());
        }
        playerPanel.add(moneyPanel);
        playerPanel.add(iconPanel);

        return playerPanel;
    }

    private void updateCheckBoxStates() {
        // Other players
        for (int i = 1; i < PLAYER_COUNT; i++) {
            // Can only be enabled if previous player is selected
            boolean previousSelected = playerCheckBoxes[i-1].isSelected();
            playerCheckBoxes[i].setEnabled(previousSelected);

            // If previous player is deselected, deselect this one too
            if (!previousSelected) {
                playerCheckBoxes[i].setSelected(false);
            }
        }
    }



    private void createPlayers() {
        // Count selected players
        int selectedCount = 0;
        for (JCheckBox cb : playerCheckBoxes) {
            if (cb.isSelected()) selectedCount++;
        }


        players = new Player[selectedCount];
        int playerIndex = 0;

        // Create only selected players
        for (int i = 0; i < PLAYER_COUNT; i++) {
            if (playerCheckBoxes[i].isSelected()) {
                players[playerIndex] = new Player(
                        (Integer) moneySpinners[i].getValue(),
                        nameFields[i].getText(),
                        playerIconIndex[i]
                );
                playerIndex++;
            }
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
        final int SPRITE_WIDTH = 300; // Each sprite's width (900 px / 3 sprites per row)
        final int SPRITE_HEIGHT = 237; // Each sprite's height (711 px / 3 sprites per column)
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

    private int findNextAvailableIcon(int currentIndex) {
        int index = (currentIndex + 1) % icons.length;
        while (index != currentIndex) {
            if (!iconTaken[index]) {
                return index;
            }
            index = (index + 1) % icons.length;
        }
        return currentIndex; // If no other icon is available, stay on current
    }

    private int findPreviousAvailableIcon(int currentIndex) {
        int index = (currentIndex - 1 + icons.length) % icons.length;
        while (index != currentIndex) {
            if (!iconTaken[index]) {
                return index;
            }
            index = (index - 1 + icons.length) % icons.length;
        }
        return currentIndex; // If no other icon is available, stay on current
    }

    private boolean validatePlayerName(String name) {
        // Check if name meets the criteria:
        // - At least 3 characters long (excluding spaces)
        // - Max 9 characters
        // - No leading numbers or spaces
        // - Only alphanumeric characters allowed
        if (name == null || name.isEmpty()) return false;

        // Remove spaces for length check
        String trimmedName = name.trim();
        if (trimmedName.length() < 3 || name.length() > 9) return false;

        // Check first character isn't a number or space
        if (Character.isDigit(name.charAt(0)) || Character.isWhitespace(name.charAt(0))) return false;

        // Check if all characters are alphanumeric
        return trimmedName.matches("^[a-zA-Z][a-zA-Z0-9]*$");
    }


    private boolean validatePlayers() {
        StringBuilder errorMessage = new StringBuilder();
        boolean isValid = true;
        // Count selected players
        int selectedCount = 0;
        for (JCheckBox cb : playerCheckBoxes) {
            if (cb.isSelected()) selectedCount++;
        }

        // Validate each selected player
        for (int i = 0; i < selectedCount; i++) {
            String playerName = nameFields[i].getText();
            if (!validatePlayerName(playerName)) {
                errorMessage.append("Player ").append(i + 1)
                            .append(": Invalid name. Names must be 3-9 alphanumeric characters, ")
                            .append("start with a letter, and contain no spaces.\n");
                isValid = false;
            }
        }

        if (!isValid) {
            JOptionPane.showMessageDialog(this,
                    errorMessage.toString(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}
