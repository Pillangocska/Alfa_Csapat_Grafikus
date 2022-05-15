package GUI.view.menus;

import GUI.view.frames.GameFrame;
import GUI.view.view.equipmentView.AxeInventoryView;
import GUI.view.view.equipmentView.AxeView;
import GUI.view.view.equipmentView.EquipmentView;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.ActiveEquipment;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class WornAxePopupMenu extends JPopupMenu {
    public WornAxePopupMenu() {
        setOpaque(false);
        setBorderPainted(false);

        JMenuItem use = new NiceMenuItem();
        use.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getInvoker() instanceof AxeInventoryView && GameFrame.getHighlightedVirologistView() != null) {
                    Axe axe = (Axe) ((EquipmentView) getInvoker()).getEquipment();
                    Virologist target = GameFrame.getHighlightedVirologistView().getVirologist();
                    TurnHandler.getActiveVirologist().use(axe, target);
                }
            }
        });
        use.setText("Use");


        JMenuItem unequip = new NiceMenuItem();
        unequip.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getInvoker() instanceof  EquipmentView) {
                    TurnHandler.getActiveVirologist().toggle(((EquipmentView) getInvoker()).getEquipment());
                }
            }
        });
        unequip.setText("Unequip");

        add(use);

        add(unequip);
    }
}
