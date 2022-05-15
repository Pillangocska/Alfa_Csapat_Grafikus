package GUI.view.view.agentView;

import GUI.view.frames.GameFrame;
import GUI.view.panels.AgentPanel;
import GUI.view.view.View;
import GUI.view.view.VirologistView;
import main.com.teamalfa.blindvirologists.agents.virus.BearVirus;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

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
        this.addActionListener(this);

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
    public void actionPerformed(ActionEvent e) {   }

    @Override
    public void update() {
        panel.update();
    }

    @Override
    public void onClick() {

    }
}
