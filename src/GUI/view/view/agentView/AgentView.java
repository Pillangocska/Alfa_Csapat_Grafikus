package GUI.view.view.agentView;

import GUI.view.panels.AgentPanel;
import GUI.view.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AgentView extends JButton implements View, ActionListener {
    private AgentPanel panel;
    // todo ezt még át kell gondolni...
    private Dimension mysize = new Dimension(20, 20);

    /**
     * initializes the button part of the view
     */
    public AgentView() {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setVerticalAlignment(CENTER);
        setHorizontalAlignment(CENTER);

        setSize(mysize);
    }

    /**
     * scales and sets the passed icon as the picture on the view.
     * @param imageIcon - the image
     */
    public void setImageIcon(ImageIcon imageIcon) {
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
        setIcon(imageIcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //todo
    }

    @Override
    public void update() {
        panel.update();
    }

    @Override
    public void onClick() {

    }
}
