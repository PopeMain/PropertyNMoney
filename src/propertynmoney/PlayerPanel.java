package propertynmoney;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerPanel extends JPanel {

    private int money;
    private int position;
    private String name;
    private Boolean inJail;
    private List<Property> properties;

    public PlayerPanel(String name, int money) {
        this.name = name;
        this.money = money;
        this.position = 0;
        this.inJail = false;
        this.properties = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    public String getName() {
        return name;
    }
    public int getMoney() {
        return money;
    }
    public void addMoney(int money) {
        this.money += money;
    }
    public void subMoney(int money) {
        this.money -= money;
    }
    public int getPosition() {
        return position;
    }
    public Boolean getInJail() {
        return inJail;
    }
    public void goToJail(){
        inJail = true;
    }
    public List<Property> getProperties() {
        return properties;
    }
    public void buyProperty(Property property) {
        properties.add(property);
    }

    public void paintPlayerSidePanel() {
        JLabel playerNameLabel = new JLabel("Player Name:"+" "+name);
        JLabel playerMoneyLabel = new JLabel("Player Money:"+ " "+money);
        JLabel playerPositionLabel = new JLabel("Player Position:" + " " + position);
        Bank theBank = new Bank();

        //UPDATE to Current Player
        //...........VVVVVVV......
        properties = theBank.getProperties();
        // Create the JList of properties
        JList<Object> propertiesList = new JList<>(properties.toArray());

        // Set a custom cell renderer for the list
        propertiesList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (renderer instanceof JLabel && value instanceof Property) {
                    JLabel label = (JLabel) renderer;
                    Property property = (Property) value;

                    // Set property name as the text
                    label.setText(property.toString());

                    // Add a colored icon to represent the property color
                    label.setIcon(new ColorIcon(property.getColor()));
                }

                return renderer;
            }
        });

        add(playerNameLabel);
        add(playerMoneyLabel);
        add(playerPositionLabel);
        add(new JScrollPane(propertiesList));

    }
}
