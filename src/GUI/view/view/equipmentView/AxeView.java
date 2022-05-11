package GUI.view.view.equipmentView;

import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;

import javax.swing.*;
import java.awt.*;

public class AxeView extends EquipmentView{
    public Axe axe;
    private final int iconWidth = 896 / 20;
    private final int iconHeight = 1196 / 20;
    AxeView(Axe axe){
        setLayout(null);
        this.axe = axe;
        setPreferredSize(new Dimension(iconWidth, iconHeight));
        handleIcon();
        setOpaque(false);
    }

    @Override
    protected void handleIcon() {
        ImageIcon icon = new ImageIcon("resources/axe.png");
        Image img = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);
        thumb.setBounds(0,0, iconWidth, iconHeight);
        add(thumb);
    }
}
