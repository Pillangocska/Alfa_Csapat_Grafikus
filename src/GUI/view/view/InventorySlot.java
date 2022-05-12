package GUI.view.view;

import GUI.view.view.agentView.AgentView;

import javax.swing.*;
import java.awt.*;

public class InventorySlot extends JPanel {
    private JButton view;
    private static int sideLength = 60;

    public InventorySlot(JButton view) {
        setOpaque(false);
        int a = sideLength/2;
        setBorder(BorderFactory.createEmptyBorder(a, a, a, a));
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        setView(view);
    }

    public void setView(JButton view) {
        removeAll();
        this.view = view;
        if (view != null) {
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 2, 0), 0, 0);
            this.add(view, constraints);
        }
        else {
            int a = sideLength/2;
            setBorder(BorderFactory.createEmptyBorder(a, a, a, a));
        }
    }

    @Override
    /**
     * Print a black rectangle with rounded corners. This method can be called from the paint methods of classes, who inherit from this class,
     * when putting an image on top of it.
     */
    public void paint(Graphics g) {
        setOpaque(false);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
        g2.dispose();
        super.paint(g);
    }
}
