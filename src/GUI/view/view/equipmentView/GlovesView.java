package GUI.view.view.equipmentView;

import GUI.view.frames.GameFrame;
import main.com.teamalfa.blindvirologists.equipments.Cloak;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Gloves;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlovesView extends EquipmentView{
    protected Gloves gloves;
    private final int iconWidth = 896 / 20;
    private final int iconHeight = 1196 / 20;

    public GlovesView(Gloves gloves) {
        super(gloves);
        setLayout(null);
        this.gloves = gloves;
        setPreferredSize(new Dimension(iconWidth, iconHeight));
        handleIcon();
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        this.addActionListener(this);
    }

    @Override
    protected void handleIcon() {
        ImageIcon icon = new ImageIcon("resources/glove.png");
        Image img = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);
        thumb.setBounds(0, 0, iconWidth, iconHeight);
        add(thumb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TurnHandler.getActiveVirologist().pickUpEquipment(gloves);
    }
}
