package GUI.view.panels;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
    // bag panels which will store the equipments in the inventory
    BaseBagPanel[] bagPanels;
    ElementPanel elementPanel;

    /**
     * constructs a new inventory panel
     */
    public InventoryPanel() {
        // creating the layout of the panel. creating constraints that will later be manipulated at each element
        setLayout(new GridBagLayout());
        setBackground(Color.GRAY.darker());
        setOpaque(false);
        GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.PAGE_START, GridBagConstraints.NONE, new Insets(3, 0, 3, 0), 0, 0);

        // creating the title of the panel
        JLabel title = new JLabel("Inventory");
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Viner Hand ITC", Font.PLAIN, 15));
        title.setForeground(Color.RED);
        add(title, constraints);


        // Creating bag panels
        bagPanels = new BaseBagPanel[9];
        for (int i = 0; i < 8; i++)
            bagPanels[i] = new BaseBagPanel();
        bagPanels[8] = new AgentPanel();

        // Creating the panel that will hold the bag panels
        JPanel bagPanelsPanel = new JPanel();
        bagPanelsPanel.setOpaque(false);
        GridLayout gridLayout = new GridLayout(3, 3);
        gridLayout.setHgap(20);
        gridLayout.setVgap(2);
        bagPanelsPanel.setLayout(gridLayout);

        // Adding bag panels
        for (var b : bagPanels)
            bagPanelsPanel.add(b);

        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(0, 20, 0, 20);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weighty = 1.0;
        add(bagPanelsPanel, constraints);


        // create the panel displaying the elements
        elementPanel = new ElementPanel();

        // adding the panel to the parent panel
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.weighty = 0.0;
        add(elementPanel, constraints);
    }

    /**
     * Prints the custom panel: makes the background have rounded corners.
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
        g2.dispose();
        super.paint(g);
    }

    public void update() {
        //TODO
    }

    public void onClick() {
        //TODO
    }

}
