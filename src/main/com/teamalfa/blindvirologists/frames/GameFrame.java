package main.com.teamalfa.blindvirologists.frames;

import main.com.teamalfa.blindvirologists.Notifiable;
import main.com.teamalfa.blindvirologists.panels.StatusPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener, Notifiable {
    private JPanel masterPanel;
    private JLayeredPane jlp;
    private JPanel statusPanel;
    private JPanel inventoryPanel;
    private JPanel mapPanel;
    private JPanel whatHappenedPanel;

    public GameFrame(String difficulty, int numberOfPlayers){
        masterPanel = new JPanel(new GridBagLayout());

        this.setTitle("Blind Virologist");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1280,720);
        ImageIcon imageIcon = new ImageIcon("resources/logo.png");
        this.setIconImage(imageIcon.getImage());

        jlp = getLayeredPane();
        jlp.setLayout(new GridBagLayout());

        // Creating the status panel
        statusPanel = new StatusPanel();
        statusPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.LAST_LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        jlp.add(statusPanel, gbc, JLayeredPane.DEFAULT_LAYER);

        this.setVisible(true);
    }

    public void logEvent(String message) {
        //TODO
    }

    public void startGame (int numberOfPlayers) {
        //TODO
    }

    public void updateView() {
        //TODO
    }

    /* NAME CONFLICT
    public void notify() {

    }
    */

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO
    }
}
