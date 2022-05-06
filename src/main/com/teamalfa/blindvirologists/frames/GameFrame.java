package main.com.teamalfa.blindvirologists.frames;

import main.com.teamalfa.blindvirologists.panels.InventoryPanel;
import main.com.teamalfa.blindvirologists.panels.StatusPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {
    private JLayeredPane jlp;
    private JPanel statusPanel;
    private JPanel inventoryPanel;
    private JPanel mapPanel;
    private JPanel whatHappenedPanel;

    public GameFrame(int numberOfPlayers){
        this.setTitle("Blind Virologist");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1280,720);
        ImageIcon imageIcon = new ImageIcon("resources/logo.png");
        this.setIconImage(imageIcon.getImage());
        this.setLayout(new GridBagLayout());

        jlp = new JLayeredPane();
        jlp.setLayout(new GridBagLayout());
        jlp.setSize(new Dimension(900, 720));
        jlp.setBackground(Color.BLUE);

        // Creating the status panel
        statusPanel = new StatusPanel();
        // Adding the status panel to the layered pane
        GridBagConstraints gbc1 = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.LAST_LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        jlp.add(statusPanel, gbc1, JLayeredPane.DEFAULT_LAYER);

        // Creating the inventory panel
        //inventoryPanel = new InventoryPanel();
        //add(inventoryPanel);

        // Adding the layered pane to the frame
        GridBagConstraints gbc2 = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.LAST_LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        this.add(jlp, gbc2);
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
