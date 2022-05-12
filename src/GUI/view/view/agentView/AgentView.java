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
}
