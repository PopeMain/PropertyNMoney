package expirimental;

import javax.swing.*;
import java.awt.*;

public class UtilityTile extends TileAbstract {
    private JLabel ownerIconL;
    private JLabel nameLabel;
    private JLabel iconLabel;
    private final Icon utilityIcon;
    private final String utilityName;
    private JPanel top;

    public UtilityTile(String name, Icon utilityIcon) {
        super();
        this.utilityName = name;
        this.utilityIcon = utilityIcon;
        paintTile();
    }

    @Override
    protected JPanel createTop() {
        top = new JPanel();
        top.setLayout(new BorderLayout());
        //build spot for owner Icon
        ownerIconL = new JLabel(utilityName, SwingConstants.CENTER);
        ownerIconL.setPreferredSize(new Dimension(20, 20)); // Size for the owner icon
        ownerIconL.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        iconLabel = new JLabel("", SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(20,20));
        iconLabel.setIcon(utilityIcon);

        //build spot to show the property Name
        nameLabel = new JLabel("", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 9));
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
}
