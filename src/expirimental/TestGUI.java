package expirimental;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestGUI extends JFrame {
    private PGUI pgui;
    private Square Go;
    private PropertyTile mediterraneanAvenue;
    private AltTile comChest1;
    private PropertyTile balticAvenue;
    private AltTile tax1;
    private UtilityTile southTrain;
    private PropertyTile orientalAvenue;
    private AltTile chance1;
    private PropertyTile vermontAvenue;
    private PropertyTile connecticutAvenue;
    private Square jail;
    private PropertyTile stcharlespl;
    private UtilityTile electricUtility;
    private PropertyTile statesavenue;
    private PropertyTile virginaAvenue;
    private UtilityTile westTrain;
    private PropertyTile stjamespl;
    private AltTile comchest2;
    private PropertyTile tennesseeAvenue;
    private PropertyTile newYorkAvenue;
    private Square freeParking;
    private PropertyTile kentuckyAvenue;
    private AltTile chance2;
    private PropertyTile indianAvenue;
    private PropertyTile illinoisAvenue;
    private UtilityTile northTrain;
    private PropertyTile atlanticAvenue;
    private PropertyTile ventnorAvenue;
    private UtilityTile waterUtility;
    private PropertyTile marvinGardens;
    private Square gotoJail;
    private PropertyTile pacificAvenue;
    private PropertyTile northCarolinaAvenue;
    private AltTile comChest3;
    private PropertyTile pennsylvaniaAvenue;
    private UtilityTile eastTrain;
    private AltTile chance3;
    private PropertyTile parkPlace;
    private AltTile tax2;
    private PropertyTile boardwalk;

    public TestGUI() {

        final String MIicons = "src/MIcons.png";

        setTitle("Monopoly");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(11,11));
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 9;
        c.gridheight = 9;
        c.fill = GridBagConstraints.BOTH;
        pgui = new PGUI();
        add(pgui, c);

        c.gridx = 10;
        c.gridy = 10;
        c.fill = GridBagConstraints.BOTH;
        Go = new Square();
        Go.addPlayer(pgui.getPlayerPanel(1).getPlayerIconL(),0);
        Go.addPlayer(pgui.getPlayerPanel(2).getPlayerIconL(),1);
        Go.addPlayer(pgui.getPlayerPanel(3).getPlayerIconL(),2);
        Go.addPlayer(pgui.getPlayerPanel(4).getPlayerIconL(),3);
        add(Go, c);

        c.gridx = 9;
        c.gridy = 10;
        mediterraneanAvenue = new PropertyTile(PropertyNames.MEDITERRANEAN_AVE);
        add(mediterraneanAvenue, c);

        c.gridx = 8;
        c.gridy = 10;
        comChest1 = new AltTile("Community Chest", extractSprite(MIicons, 8));
        add(comChest1, c);

        c.gridx = 7;
        c.gridy = 10;
        balticAvenue = new PropertyTile(PropertyNames.BALTIC_AVE);
        add(balticAvenue, c);

        c.gridx = 6;
        c.gridy = 10;
        tax1 = new AltTile("Tax", extractSprite(MIicons, 10));
        add(tax1, c);

        c.gridx = 5;
        c.gridy = 10;
        southTrain = new UtilityTile("South Train", extractSprite(MIicons, 7));
        add(southTrain, c);

        c.gridx = 4;
        c.gridy = 10;
        orientalAvenue = new PropertyTile(PropertyNames.ORIENTAL_AVE);
        add(orientalAvenue, c);

        c.gridx = 3;
        c.gridy = 10;
        chance1 = new AltTile("Chance", extractSprite(MIicons, 3));
        add(chance1, c);

        c.gridx = 2;
        c.gridy = 10;
        vermontAvenue = new PropertyTile(PropertyNames.VERMONT_AVE);
        add(vermontAvenue, c);

        c.gridx = 1;
        c.gridy = 10;
        connecticutAvenue = new PropertyTile(PropertyNames.CONNECTICUT_AVE);
        add(connecticutAvenue, c);

        c.gridx = 0;
        c.gridy = 10;
        jail = new Square();
        add(jail, c);

        c.gridx = 0;
        c.gridy = 9;
        stcharlespl = new PropertyTile(PropertyNames.ST_CHARLES_PL);
        add(stcharlespl, c);

        c.gridx = 0;
        c.gridy = 8;
        electricUtility = new UtilityTile("Electric Utility", extractSprite(MIicons, 4));
        add(electricUtility, c);

        c.gridx = 0;
        c.gridy = 7;
        statesavenue = new PropertyTile(PropertyNames.STATES_AVE);
        add(statesavenue, c);

        c.gridx = 0;
        c.gridy = 6;
        virginaAvenue = new PropertyTile(PropertyNames.VIRGINIA_AVE);
        add(virginaAvenue, c);

        c.gridx = 0;
        c.gridy = 5;
        westTrain = new UtilityTile("West Train", extractSprite(MIicons, 7));
        add(westTrain, c);

        c.gridx = 0;
        c.gridy = 4;
        stjamespl = new PropertyTile(PropertyNames.ST_JAMES_PL);
        add(stjamespl, c);

        c.gridx = 0;
        c.gridy = 3;
        comchest2 = new AltTile("Community Chest", extractSprite(MIicons, 8));
        add(comchest2, c);

        c.gridx = 0;
        c.gridy = 2;
        tennesseeAvenue = new PropertyTile(PropertyNames.TENNESSEE_AVE);
        add(tennesseeAvenue, c);

        c.gridx = 0;
        c.gridy = 1;
        newYorkAvenue = new PropertyTile(PropertyNames.NEW_YORK_AVE);
        add(newYorkAvenue, c);

        c.gridx = 0;
        c.gridy = 0;
        freeParking = new Square();
        add(freeParking, c);

        c.gridx = 1;
        c.gridy = 10;
        kentuckyAvenue = new PropertyTile(PropertyNames.KENTUCKY_AVE);
        add(kentuckyAvenue, c);

        c.gridx = 2;
        c.gridy = 10;
        chance2 = new AltTile("Chance", extractSprite(MIicons, 3));
        add(chance2, c);

        c.gridx = 3;
        c.gridy = 10;
        indianAvenue = new PropertyTile(PropertyNames.INDIANA_AVE);
        add(indianAvenue, c);

        c.gridx = 4;
        c.gridy = 10;
        illinoisAvenue = new PropertyTile(PropertyNames.ILLINOIS_AVE);
        add(illinoisAvenue, c);

        c.gridx = 5;
        c.gridy = 10;
        northTrain = new UtilityTile("North Train", extractSprite(MIicons, 7));
        add(northTrain, c);

        c.gridx = 6;
        c.gridy = 10;
        atlanticAvenue = new PropertyTile(PropertyNames.ATLANTIC_AVE);
        add(atlanticAvenue, c);

        c.gridx = 7;
        c.gridy = 10;
        ventnorAvenue = new PropertyTile(PropertyNames.VENTNOR_AVE);
        add(ventnorAvenue, c);

        c.gridx = 8;
        c.gridy = 10;
        waterUtility = new UtilityTile("Water Utility", extractSprite(MIicons, 1));
        add(waterUtility, c);

        c.gridx = 9;
        c.gridy = 10;
        marvinGardens = new PropertyTile(PropertyNames.MARVIN_GAR);
        add(marvinGardens, c);

        c.gridx = 10;
        c.gridy = 10;
        gotoJail = new Square();
        add(gotoJail, c);

        c.gridx = 10;
        c.gridy = 1;
        pacificAvenue = new PropertyTile(PropertyNames.PACIFIC_AVE);
        add(pacificAvenue, c);

        c.gridx = 10;
        c.gridy = 2;
        northCarolinaAvenue = new PropertyTile(PropertyNames.NORTH_CAROLINA_AVE);
        add(northCarolinaAvenue, c);

        c.gridx = 10;
        c.gridy = 3;
        comChest3 = new AltTile("Community Chest", extractSprite(MIicons, 8));
        add(comChest3, c);

        c.gridx = 10;
        c.gridy = 4;
        pennsylvaniaAvenue = new PropertyTile(PropertyNames.PENNSYLVANIA_AVE);
        add(pennsylvaniaAvenue, c);

        c.gridx = 10;
        c.gridy = 5;
        eastTrain = new UtilityTile("East Train", extractSprite(MIicons, 7));
        add(eastTrain, c);

        c.gridx = 10;
        c.gridy = 6;
        chance3 = new AltTile("Chance", extractSprite(MIicons, 3));
        add(chance3, c);

        c.gridx = 10;
        c.gridy = 7;
        parkPlace = new PropertyTile(PropertyNames.PARK_PL);
        add(parkPlace, c);

        c.gridx = 10;
        c.gridy = 8;
        tax2 = new AltTile("Luxury Tax", extractSprite(MIicons, 10));
        add(tax2, c);

        c.gridx = 10;
        c.gridy = 9;
        boardwalk = new PropertyTile(PropertyNames.BOARDWALK);
        add(boardwalk, c);


        setSize(700, 700);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TestGUI();
    }


    private ImageIcon extractSprite(String spriteSheetPath, int iconIndex) {
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
            Image scaledSprite = sprite.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledSprite);

        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if the sprite cannot be extracted
        }
    }
}

