package main.GUI.view.panels;

import javax.swing.*;
import java.awt.*;

public class WornEquipmentInventorySlot extends InventorySlot {

    public WornEquipmentInventorySlot(JButton view) {
        super(view);
    }

    @Override
    public void setView(JButton view) {
        super.setView(view);
        if (view == null) {
            JLabel text = new JLabel("Empty");
            text.setPreferredSize(new Dimension(sideLength, sideLength));
            text.setHorizontalAlignment(SwingConstants.CENTER);
            text.setVerticalAlignment(SwingConstants.CENTER);
            text.setFont(new Font("Viner Hand ITC", Font.PLAIN, 12));
            text.setForeground(Color.RED);
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            add(text);
        }
    }
}
