package propertynmoney;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends JPanel {
    private BoardPanel boardPanel; // Inner panel to display the board image
    private List<Point> boardSpaces; // List of indexed spaces around the board
    private List<PlayerIcon> playerIcons; // Player icons to represent players

    public Board(Image boardImage) {
        setLayout(null); // Absolute layout for precise positioning of inner components

        // Create the inner board panel
        boardPanel = new BoardPanel(boardImage);
        boardPanel.setBounds(50, 50, 400, 400); // Set position and size for the inner board
        add(boardPanel); // Add the inner board panel to the outer panel

        // Initialize lists for board spaces and players
        boardSpaces = new ArrayList<>();
        playerIcons = new ArrayList<>();

        // Initialize indexed spaces for player movement
        initializeBoardSpaces();
    }

    // Add a player to the board with their respective color
    public void addPlayer(String playerName, Color playerColor) {
        PlayerIcon playerIcon = new PlayerIcon(playerName, playerColor);
        playerIcons.add(playerIcon);
        add(playerIcon.label); // Add player's icon to the outer panel
    }

    // Move a player to a specific space (indexed position on the board)
    public void movePlayer(String playerName, int spaceIndex) {
        for (PlayerIcon icon : playerIcons) {
            if (icon.name.equals(playerName)) {
                // Get the target position from board spaces
                Point position = boardSpaces.get(spaceIndex % boardSpaces.size()); // Wrap around spaces
                icon.label.setBounds(
                        (int) position.getX() - 10, // Adjust for icon width
                        (int) position.getY() - 10, // Adjust for icon height
                        20, // Icon width
                        20  // Icon height
                );
                repaint();
                break;
            }
        }
    }

    // Initialize the indexed spaces for player movement around the board
    private void initializeBoardSpaces() {
        int x0 = 50, y0 = 50; // Start position of the inner board panel
        int boardWidth = 400, boardHeight = 400; // Dimensions of the inner board
        int margin = 30; // Distance for player icons outside the board panel
        int numSpacesPerEdge = 10; // Number of spaces on each edge (Monopoly-style)

        int spaceLength = boardWidth / (numSpacesPerEdge - 1); // Space length between each space

        // Bottom row: Left to right
        for (int i = 0; i < numSpacesPerEdge; i++) {
            int x = x0 + i * spaceLength;
            int y = y0 + boardHeight + margin; // Just below the board
            boardSpaces.add(new Point(x, y));
        }

        // Right column: Bottom to top
        for (int i = 1; i < numSpacesPerEdge; i++) { // Start from 1 to avoid duplicate corner
            int x = x0 + boardWidth + margin; // Just to the right of the board
            int y = y0 + boardHeight - i * spaceLength;
            boardSpaces.add(new Point(x, y));
        }

        // Top row: Right to left
        for (int i = 1; i < numSpacesPerEdge; i++) { // Start from 1 to avoid duplicate corner
            int x = x0 + boardWidth - i * spaceLength;
            int y = y0 - margin; // Just above the board
            boardSpaces.add(new Point(x, y));
        }

        // Left column: Top to bottom
        for (int i = 1; i < numSpacesPerEdge; i++) { // Start from 1 to avoid duplicate corner
            int x = x0 - margin; // Just to the left of the board
            int y = y0 + i * spaceLength;
            boardSpaces.add(new Point(x, y));
        }
    }

    // Inner class representing the board panel for rendering the game board
    private static class BoardPanel extends JPanel {

        private Image boardImage;

        public BoardPanel(Image boardImage) {
            this.boardImage = boardImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (boardImage != null) {
                Graphics2D g2d = (Graphics2D) g;

                // Calculate aspect ratio and scale
                int imgWidth = boardImage.getWidth(null);
                int imgHeight = boardImage.getHeight(null);
                double aspectRatio = (double) imgWidth / imgHeight;

                int panelWidth = getWidth();
                int panelHeight = getHeight();

                int scaledWidth, scaledHeight;
                if (panelWidth / (double) panelHeight < aspectRatio) {
                    scaledWidth = panelWidth;
                    scaledHeight = (int) (scaledWidth / aspectRatio);
                } else {
                    scaledHeight = panelHeight;
                    scaledWidth = (int) (scaledHeight * aspectRatio);
                }

                // Center the scaled image
                int x = (panelWidth - scaledWidth) / 2;
                int y = (panelHeight - scaledHeight) / 2;

                g2d.drawImage(boardImage, x, y, scaledWidth, scaledHeight, this);
            }
        }
    }

    // Inner class representing a player icon
    private static class PlayerIcon {
        String name;
        JLabel label;

        public PlayerIcon(String name, Color color) {
            this.name = name;
            this.label = new JLabel();
            this.label.setOpaque(true);
            this.label.setBackground(color); // Use color to distinguish players
            this.label.setSize(20, 20); // Icon size
        }
    }
}
