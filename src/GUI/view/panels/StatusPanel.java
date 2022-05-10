package GUI.view.panels;

import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusPanel extends JPanel implements ActionListener/*, View*/ {
    private JButton endTurnButton;
    private JLabel currentPlayerLabel;
    private JLabel actionCounter;

    public StatusPanel() {
        endTurnButton = new RoundedOutlinedButton("End Turn");
        endTurnButton.addActionListener(this);
        this.add(endTurnButton);

        // this is necessary for information about the current player (currentPlayerLabel and actionCounter) to be displayed in the same column
        JPanel currentPlayerInformationContainer = new JPanel();
        currentPlayerInformationContainer.setOpaque(false);

        currentPlayerLabel = new RoundedJLabel();
        currentPlayerLabel.setText(TurnHandler.getActiveVirologist().getName());
        currentPlayerLabel.setAlignmentX(CENTER_ALIGNMENT);
        currentPlayerInformationContainer.add(currentPlayerLabel);

        actionCounter = new RoundedJLabel();
        actionCounter.setText("Initializing...");
        actionCounter.setAlignmentX(CENTER_ALIGNMENT);
        currentPlayerInformationContainer.add(actionCounter);
        currentPlayerInformationContainer.setLayout(new BoxLayout(currentPlayerInformationContainer, BoxLayout.Y_AXIS));
        this.add(currentPlayerInformationContainer);
    }

    public void update() {
        this.currentPlayerLabel.setText(TurnHandler.getActiveVirologist().getName());
        this.actionCounter.setText(Integer.toString(TurnHandler.getActiveVirologist().getActions()));
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == endTurnButton)
            TurnHandler.getInstance().tick();
        this.update();
    }
}
