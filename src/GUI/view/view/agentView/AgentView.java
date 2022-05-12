package GUI.view.view.agentView;

import GUI.view.panels.AgentPanel;
import GUI.view.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AgentView extends JButton implements View, ActionListener {
    public AgentPanel panel;

    /**
     * initializing the button
     */
    public AgentView() {
        setSize(100, 50);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    public void setImageIcon(ImageIcon imageIcon) {
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        setIcon(imageIcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void update() {

    }

    @Override
    public void onClick() {

    }

    @Override
    /**
     * Print a black rectangle with rounded corners. This method can be called from the paint methods of classes, who inherit from this class,
     * when putting an image on top of it.
     */
    public void paint(Graphics g) {
        setOpaque(false);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.BLACK);
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
        g2.dispose();
        super.paint(g);
    }
}
