package expirimental;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame{

    public Test() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        PGUI playerGUI = new PGUI();

        // Create several PropertyTile instances for the board
        PropertyTile mediterraneanAveTile = new PropertyTile(PropertyNames.MEDITERRANEAN_AVE);
        PropertyTile parkPlaceTile = new PropertyTile(PropertyNames.PARK_PL);
        PropertyTile balticAveTile = new PropertyTile(PropertyNames.BALTIC_AVE);
        PropertyTile atlanticAveTile = new PropertyTile(PropertyNames.ATLANTIC_AVE);
        UtilityTile railroad = new UtilityTile("South RAILROAD", "src/MIcons.png", 7);

        // Simulate Player 1 buying Mediterranean Ave
        playerGUI.addProperty(1, PropertyNames.MEDITERRANEAN_AVE);
        mediterraneanAveTile.setOwnerIcon(playerGUI.getPlayerPanel(1).getPlayerIconL());
        mediterraneanAveTile.addHouse();

        mediterraneanAveTile.addPlayer(playerGUI.getPlayerPanel(1).getPlayerIconL(), 0);
        mediterraneanAveTile.addPlayer(playerGUI.getPlayerPanel(2).getPlayerIconL(), 1);
        mediterraneanAveTile.addPlayer(playerGUI.getPlayerPanel(4).getPlayerIconL(), 3);
        atlanticAveTile.addPlayer(playerGUI.getPlayerPanel(3).getPlayerIconL(), 2);

        // Simulate Player 1 buying Park Place
        playerGUI.addProperty(2, PropertyNames.PARK_PL);
        parkPlaceTile.setOwnerIcon(playerGUI.getPlayerPanel(2).getPlayerIconL());
        parkPlaceTile.addHouse();
        parkPlaceTile.addHouse(); // Add a second house

        // Add Baltic Ave to the list
        playerGUI.addProperty(1 ,PropertyNames.BALTIC_AVE);

        // Create a panel to display the tiles and player details alongside each other
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(1, 9, 1, 0)); // Example: Displaying the tiles in 1 row
        boardPanel.add(mediterraneanAveTile);
        boardPanel.add(parkPlaceTile);
        boardPanel.add(balticAveTile);
        boardPanel.add(atlanticAveTile);
        boardPanel.add(railroad);

        // Add the panels to the JFrame
        add(playerGUI, BorderLayout.CENTER); // Player details on the left
        add(boardPanel, BorderLayout.SOUTH); // Property tiles in the center
        System.out.println(mediterraneanAveTile.getPropertyName());
        // Frame settings
        setSize(800, 600);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Test();
    }


}
