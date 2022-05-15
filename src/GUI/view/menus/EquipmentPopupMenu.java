package GUI.view.menus;

import GUI.view.view.equipmentView.EquipmentView;
import main.com.teamalfa.blindvirologists.equipments.Equipment;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EquipmentPopupMenu extends JPopupMenu {
    private Equipment equipment;

    public EquipmentPopupMenu(Equipment equipment) {
        this.equipment = equipment;
        setOpaque(false);
        setBorderPainted(false);

        JMenuItem toss = new NiceMenuItem();
        toss.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getInvoker() instanceof  EquipmentView) {
                    TurnHandler.getActiveVirologist().toss(equipment);
                }
            }
        });
        toss.setText("Toss");


        JMenuItem equip = new NiceMenuItem();
        equip.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getInvoker() instanceof  EquipmentView) {
                    TurnHandler.getActiveVirologist().toggle(equipment);
                }
            }
        });
        equip.setText("Equip");

        add(toss);

        add(equip);
    }
}
