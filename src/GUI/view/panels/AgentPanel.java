package GUI.view.panels;

import GUI.view.view.AgentView;

import javax.swing.*;
import java.awt.*;

public class AgentPanel extends BaseBagPanel {
    private AgentView view;
    private JLabel imageLabel;

    public AgentPanel() {
        ImageIcon imageIcon = new ImageIcon("resources/logo.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(imageLabel);
    }

    @Override
    public void update() {

    }
}
