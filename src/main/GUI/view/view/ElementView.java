package main.GUI.view.view;

import main.logic.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.logic.com.teamalfa.blindvirologists.virologist.backpack.ElementBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElementView extends JButton implements View, ActionListener {
    protected ElementBank eb;
    private final int iconWidth = 896 / 20;
    private final int iconHeight = 1196 / 20;

    public ElementView(ElementBank elementBank) {
        setLayout(null);
        this.eb = elementBank;
        setPreferredSize(new Dimension(iconWidth, iconHeight));
        handleIcon();
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        this.addActionListener(this);
    }
    protected void handleIcon() {
        if(eb.getAminoAcid() > 0 || eb.getNucleotide() > 0) {
            ImageIcon icon = new ImageIcon("resources/elementbank.png");
            Image img = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            JLabel thumb = new JLabel();
            thumb.setIcon(icon);
            thumb.setBounds(0, 0, iconWidth, iconHeight);
            add(thumb);
        }
        else {
            this.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(eb.getAminoAcid() > 0 || eb.getNucleotide() > 0) {
            TurnHandler.getActiveVirologist().pickUpMaterial(eb);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void onClick() {

    }

    public void setElementBank(ElementBank elementBank) {
        eb = elementBank;
    }
}
