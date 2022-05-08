package GUI.view.panels;

import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusPanel extends JPanel implements ActionListener/*, View*/ {
    private JButton endTurnButton;
    private JLabel currentPlayerLabel;
    private JLabel nextPlayerLabel;
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
        actionCounter.setText("Actions Left: 2");
        actionCounter.setAlignmentX(CENTER_ALIGNMENT);
        currentPlayerInformationContainer.add(actionCounter);
        currentPlayerInformationContainer.setLayout(new BoxLayout(currentPlayerInformationContainer, BoxLayout.Y_AXIS));
        this.add(currentPlayerInformationContainer);

        nextPlayerLabel = new RoundedJLabel();
        nextPlayerLabel.setText("<html><center>Next Player:<br/>UWU420</center></html>"); // külön osztályba setterrel?
        this.add(nextPlayerLabel);
    }

    public void update() {
        //TODO
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO
        if(e.getSource() == endTurnButton)
            System.exit(1);
    }
}
