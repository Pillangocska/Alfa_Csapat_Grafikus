package GUI.view.frames;

import GUI.view.panels.InventoryPanel;
import GUI.view.panels.StatusPanel;
import GUI.view.panels.WhatHappenedPanel;
import GUI.view.panels.WornEquipmentPanel;
import GUI.view.view.View;
import main.com.teamalfa.blindvirologists.city.City;
import main.com.teamalfa.blindvirologists.turn_handler.Game;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame implements ActionListener, Notifiable {
    private JLayeredPane jlp; //TODO ha mukszik minden megcsinalni addig fix koordinata
    private JPanel statusPanel;
    private JPanel inventoryPanel;
    private JPanel wornEquipmentPanel;
    private JPanel mapPanel; //TODO naon
    private JPanel whatHappenedPanel;
    private ArrayList<View> views;
    public static GameFrame instance;

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
        this.getContentPane().setBackground(new Color(141,120,120));

        // Creating the status panel
        statusPanel = new StatusPanel();
        statusPanel.setBounds(0,0,300,100);
        statusPanel.setBackground(Color.black);

        //Creating worn equipment panel
        wornEquipmentPanel = new WornEquipmentPanel();
        wornEquipmentPanel.setBounds(960,5, 300,110);

        //Creating the inventory panel
        inventoryPanel = new InventoryPanel();
        inventoryPanel.setBounds(960,115,300,300);

        //Creating what happened panel
        whatHappenedPanel = new WhatHappenedPanel();
        whatHappenedPanel.setBounds(960,415,300,260);

        this.setLayout(null);
        this.setVisible(true);
        this.add(whatHappenedPanel);
        this.add(wornEquipmentPanel);
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
    }

    public void updateView() {
        for (View view : views) {
            view.update();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO
    }

    @Override
    public void creativeNotify(String massage) {
        // TODO
    }
}
