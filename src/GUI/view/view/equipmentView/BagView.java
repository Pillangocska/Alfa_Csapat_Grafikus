package GUI.view.view.equipmentView;

import main.com.teamalfa.blindvirologists.equipments.Bag;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;

import javax.swing.*;
import java.awt.*;

public class BagView extends EquipmentView{
    public Bag bag;
    private final int iconWidth = 896 / 20;
    private final int iconHeight = 1196 / 20;
    BagView(Bag bag){
        setLayout(null);
        this.bag = bag;
        setPreferredSize(new Dimension(iconWidth, iconHeight));
        handleIcon();
        setOpaque(false);
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
}
