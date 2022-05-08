package main.com.teamalfa.blindvirologists.panels;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
    BaseBagPanel[] bagPanels;

    public InventoryPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.GRAY.darker());

        JLabel title = new JLabel("Inventory");
        title.setFont(new Font("Viner Hand ITC", Font.PLAIN, 15));
        title.setForeground(Color.RED);
        add(title);

        // Creating bag panels
        bagPanels = new BaseBagPanel[9];
        for (int i = 0; i < 9; i++)
            bagPanels[i] = new BaseBagPanel();

        // Creating the panel that will hold the bag panels
        JPanel bagPanelsPanel = new JPanel();
        bagPanelsPanel.setOpaque(false);
        GridLayout gridLayout = new GridLayout(3, 3);
        gridLayout.setHgap(20);
        gridLayout.setVgap(2);
        bagPanelsPanel.setLayout(gridLayout);
        bagPanelsPanel.setMaximumSize(new Dimension(250, 150));

        // Adding bag panels
        for (var b : bagPanels)
            bagPanelsPanel.add(b);

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
