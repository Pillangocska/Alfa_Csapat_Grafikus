package GUI.view.menus;

import GUI.view.view.equipmentView.EquipmentView;
import main.com.teamalfa.blindvirologists.equipments.Equipment;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EquipmentPopupMenu extends JPopupMenu {
    public EquipmentPopupMenu() {
        setOpaque(false);
        setBorderPainted(false);

        JMenuItem toss = new NiceMenuItem();
        toss.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getInvoker() instanceof  EquipmentView) {
                    TurnHandler.getActiveVirologist().toss(((EquipmentView) getInvoker()).getEquipment());
                }
            }
        });
        toss.setText("Toss");


        JMenuItem equip = new NiceMenuItem();
        equip.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getInvoker() instanceof  EquipmentView) {
                    TurnHandler.getActiveVirologist().toggle(((EquipmentView) getInvoker()).getEquipment());
                }
            }
        });
        equip.setText("Equip");

        add(toss);

        add(equip);
    }
}
