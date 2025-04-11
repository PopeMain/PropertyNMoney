package expirimental;

import javax.swing.*;
import java.awt.*;

public class AltTile extends TileAbstract {
    private JLabel nameLabel;
    private JLabel iconLabel;
    private final Icon altIcon;
    private final String altName;
    private JPanel top;

    public AltTile(String name, Icon altIcon) {
        super();
        this.altName = name;
        this.altIcon = altIcon;
        paintTile();
    }

    @Override
    protected JPanel createTop() {
        top = new JPanel();
        top.setLayout(new BorderLayout());

        iconLabel = new JLabel("", SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(20,20));
        iconLabel.setIcon(altIcon);

        //build spot to show the property Name
        nameLabel = new JLabel("", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 9));
        nameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.WHITE);

        top.setBackground(new Color(100, 255, 255));
        top.add(iconLabel, BorderLayout.CENTER);
        top.add(nameLabel, BorderLayout.SOUTH);

        return top;
    }
}
