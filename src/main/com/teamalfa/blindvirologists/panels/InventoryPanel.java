package main.com.teamalfa.blindvirologists.panels;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
    //BaseBagPanel[] bagPanels; -> not implemented yet

    public InventoryPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.GRAY);

        JLabel title = new JLabel("Inventory");
        title.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
        title.setForeground(Color.RED);
        add(title);

        JPanel bagPanelsPanel = new JPanel();
        bagPanelsPanel.setOpaque(false);
        bagPanelsPanel.setLayout(new GridLayout());

        add(bagPanelsPanel);

        JPanel elementPanel = new JPanel();
        elementPanel.setOpaque(false);
    }

    public void update() {
        //TODO
    }

    public void onClick() {
        //TODO
    }

}
