package main.GUI.view.view.equipmentView;

import main.logic.com.teamalfa.blindvirologists.equipments.Bag;
import main.logic.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BagView extends EquipmentView{
    protected Bag bag;
    private final int iconWidth = 896 / 20;
    private final int iconHeight = 1196 / 20;

    public BagView(Bag bag){
        setLayout(null);
        this.bag = bag;
        setPreferredSize(new Dimension(iconWidth, iconHeight));
        handleIcon();
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        this.addActionListener(this);
    }

    @Override
    protected void handleIcon() {
        ImageIcon icon = new ImageIcon("resources/bag.png");
        Image img = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);
        thumb.setBounds(0,0, iconWidth, iconHeight);
        add(thumb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TurnHandler.getActiveVirologist().pickUpEquipment(bag);
    }
}
