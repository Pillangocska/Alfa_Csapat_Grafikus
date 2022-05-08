package GUI.view.frames;

import main.com.teamalfa.blindvirologists.city.City;
import main.com.teamalfa.blindvirologists.panels.InventoryPanel;
import main.com.teamalfa.blindvirologists.panels.StatusPanel;
import main.com.teamalfa.blindvirologists.turn_handler.Game;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame implements ActionListener, Notifiable {
    private JLayeredPane jlp;
    private JPanel statusPanel;
    private JPanel inventoryPanel;
    private JPanel mapPanel;
    private JPanel whatHappenedPanel;
    private ArrayList<View> views;
    public static GameFrame instance;

    public GameFrame(int numberOfPlayers){
        //Starting the game
        this.startGame(numberOfPlayers);

        this.setTitle("Blind Virologist");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1280,720);
        ImageIcon imageIcon = new ImageIcon("resources/logo.png");
        this.setIconImage(imageIcon.getImage());

        // Creating a layered pane so the controls can be on top of the game field
        //jlp = new JLayeredPane();
        //jlp.setLayout(new GridBagLayout());
        //jlp.setSize(new Dimension(900, 720));

        // Creating the status panel
        statusPanel = new StatusPanel();
        // Adding the status panel to the layered pane
        //GridBagConstraints gbc1 = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                //GridBagConstraints.LAST_LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        //jlp.add(statusPanel, gbc1, JLayeredPane.DEFAULT_LAYER);
        statusPanel.setBounds(0,0,400,100);
        statusPanel.setBackground(Color.CYAN);

        //Creating the inventory panel
        inventoryPanel = new InventoryPanel();
        //add(inventoryPanel);

        // Adding the layered pane to the frame
        //GridBagConstraints gbc2 = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                //GridBagConstraints.LAST_LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        //this.add(jlp, gbc2);
        //this.add(jlp);
        inventoryPanel.setBounds(950,200,300,200);


        this.setVisible(true);
        this.add(statusPanel);
        this.add(inventoryPanel);
        this.repaint();
    }

    public void logEvent(String message) {
        //TODO
    }

    public void startGame (int numberOfPlayers) {
        Game.getInstance().startGame();
        //Adding players to the turnhandler
        for(int i = 1 ; i <= numberOfPlayers ; i++){
            Virologist virologist = new Virologist("Player"+i);
            virologist.setField(City.getInstance().getAllFields().get(0));
            TurnHandler.accept(virologist);
            System.out.println("Player"+i+" created");
        }
        //TODO
    }

    public void updateView() {
        for (View view : views) {
            view.update();
        }
    }
    */

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO
    }

    @Override
    public void creativeNotify(String massage) {

    }
}
