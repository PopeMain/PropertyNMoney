package expirimental;

import javax.swing.*;
import java.awt.*;


public class PropertyListRenderer extends DefaultListCellRenderer {
        private final Icon utilityIcon; // Icon for utilities

    public PropertyListRenderer(Icon utilityIcon) {
            this.utilityIcon = utilityIcon; // Pass a utility icon (can be an ImageIcon)
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            // Use the default renderer for styling
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof PropertyNames) {
                PropertyNames property = (PropertyNames) value; // Cast to PropertyNames enum

                // Check if the property is a utility (use utilityIcon)
                if (property.name().contains("RAILROAD") || property.name().contains("UTILITY")) {
                    label.setIcon(utilityIcon); // Use icon for utilities
                } else {
                    // Otherwise, use the property color icon
                    label.setIcon(new ColorIcon(property.getColor())); // Use ColorIcon
                }

                label.setText(property.toString()); // Display property name
            }

            return label;
        }

    }
