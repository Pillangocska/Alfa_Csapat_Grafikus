package GUI.view.frames;

import GUI.view.panels.*;
import main.com.teamalfa.blindvirologists.agents.Vaccine;
import main.com.teamalfa.blindvirologists.agents.genetic_code.AmnesiaCode;
import main.com.teamalfa.blindvirologists.agents.genetic_code.BearCode;
import main.com.teamalfa.blindvirologists.agents.genetic_code.DanceCode;
import main.com.teamalfa.blindvirologists.agents.genetic_code.ParalyzeCode;
import main.com.teamalfa.blindvirologists.agents.virus.AmnesiaVirus;
import main.com.teamalfa.blindvirologists.agents.virus.BearVirus;
import main.com.teamalfa.blindvirologists.agents.virus.DanceVirus;
import main.com.teamalfa.blindvirologists.agents.virus.ParalyzeVirus;
import main.com.teamalfa.blindvirologists.city.City;
import main.com.teamalfa.blindvirologists.equipments.Bag;
import main.com.teamalfa.blindvirologists.equipments.Cloak;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Gloves;
import main.com.teamalfa.blindvirologists.turn_handler.Game;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener, Notifiable {
    private final StatusPanel statusPanel;
    private InventoryPanel inventoryPanel;
    private WornEquipmentPanel wornEquipmentPanel;
    private MapPanel mapPanel;
    private WhatHappenedPanel whatHappenedPanel;
    private static JPanel helpPanel;

    public GameFrame(int numberOfPlayers){
        //Starting the game
        this.startGame(numberOfPlayers);
        //And setting up the basics...
        this.setTitle("Blind Virologist");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1280,720);
        ImageIcon imageIcon = new ImageIcon("resources/logo.png");
        this.setIconImage(imageIcon.getImage());
        this.getContentPane().setBackground(new Color(0,0,0));

        //LayeredPane panel, map will be in the back and the rest front
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,1280,720);

        // Creating the status panel
        statusPanel = new StatusPanel(this);
        statusPanel.setBounds(0,0,300,100);
        statusPanel.setBackground(Color.black);

        //Creating worn equipment panel
        wornEquipmentPanel = new WornEquipmentPanel();
        wornEquipmentPanel.setBounds(960,5, 300,110);
        //views.add((View)wornEquipmentPanel);

        //Creating the inventory panel
        inventoryPanel = new InventoryPanel();
        inventoryPanel.setBounds(960,115,300,300);
        //views.add((View)inventoryPanel);

        //Creating what happened panel
        whatHappenedPanel = new WhatHappenedPanel();
        whatHappenedPanel.setBounds(960,415,300,360);
        //views.add((View)whatHappenedPanel);

        //Creating map panel
        mapPanel = new MapPanel();
        mapPanel.setBounds(0,80,1000,550);
        //views.add((View)mapPanel);

        this.setLayout(null);
        this.setVisible(true);
        layeredPane.add(mapPanel,Integer.valueOf(0));//Default Layer(bottom)
        layeredPane.add(whatHappenedPanel,Integer.valueOf(1));//The rest will go to the front
        layeredPane.add(wornEquipmentPanel,Integer.valueOf(1));
        layeredPane.add(statusPanel,Integer.valueOf(1));
        layeredPane.add(inventoryPanel,Integer.valueOf(1));
        this.add(layeredPane);

        //to print out comboboxes
        helpPanel = new JPanel();
        helpPanel.setLayout(null);
        this.add(helpPanel);

        this.repaint();
    }

    //getter

    public static JPanel getHelpPanel() {
        return helpPanel;
    }

    //szerintem ez felesleges
    public void logEvent(String message) {
        whatHappenedPanel.logOnPanel(message);
        this.updateView();
    }

    public void startGame (int numberOfPlayers) {
        Game.getInstance().startGame();
        //Adding players to the turnhandler
        for(int i = 1 ; i <= numberOfPlayers ; i++){
            Virologist virologist = new Virologist("Player"+i);
            virologist.setNotifiable(this);

            /*
            EZ ILYEN OCSORTÁNYOS INVENTORY DEBUG CODE, MAJD KÉSŐBB KI KELL TÖRÖLNI
            if (i == 1) {
                virologist.getBackpack().getAgentPocket().addAgent(new BearVirus());
                virologist.getBackpack().getAgentPocket().addAgent(new DanceVirus());
                virologist.getBackpack().getAgentPocket().addAgent(new AmnesiaVirus());
                virologist.getBackpack().getAgentPocket().addAgent(new ParalyzeVirus());
            }

            if (i == 2) {
                virologist.getBackpack().getAgentPocket().addAgent(new Vaccine(new BearCode()));
                virologist.getBackpack().getAgentPocket().addAgent(new Vaccine(new AmnesiaCode()));
                virologist.getBackpack().getAgentPocket().addAgent(new Vaccine(new ParalyzeCode()));
                virologist.getBackpack().getAgentPocket().addAgent(new Vaccine(new DanceCode()));
            }

            if (i == 3) {
                virologist.getBackpack().getEquipmentPocket().add(new Gloves());
                virologist.getBackpack().getEquipmentPocket().add(new Axe());
                virologist.getBackpack().getEquipmentPocket().add(new Cloak());
                virologist.getBackpack().getEquipmentPocket().add(new Bag());
            }

            if (i == 4) {
                virologist.getBackpack().getElementBank().setAminoAcid(10);
                virologist.getBackpack().getElementBank().setNucleotide(10);

                virologist.getBackpack().getGeneticCodePocket().add(new AmnesiaCode());
                virologist.getBackpack().getGeneticCodePocket().add(new DanceCode());
                virologist.getBackpack().getGeneticCodePocket().add(new BearCode());

            }

            */


            City.getInstance().getAllFields().get(0).accept(virologist);
            TurnHandler.accept(virologist);
            System.out.println("Player"+i+" created");
        }
    }

    /**
     * Updates all Views
     */
    public void updateView() {
        /*
        for (View view : views) {
            view.update();
        }
        */
        statusPanel.update();
        wornEquipmentPanel.update();
        inventoryPanel.update();
        whatHappenedPanel.update();
        mapPanel.update();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO
    }

    /**
     * Passes the event massage to the WhatHappenedPanel
     * Updates all Views
     * @param massage
     */
    @Override
    public void creativeNotify(String massage) {
        whatHappenedPanel.logOnPanel(massage);
        updateView();
    }
}
