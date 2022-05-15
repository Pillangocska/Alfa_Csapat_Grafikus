package GUI.view.view.equipmentView;

import GUI.view.menus.EquipmentPopupMenu;
import main.com.teamalfa.blindvirologists.equipments.Cloak;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CloakInventoryView extends CloakView {
    private boolean isWorn;
    private JPopupMenu popupMenu = new EquipmentPopupMenu();

    public CloakInventoryView(Cloak cloak, boolean isWorn) {
        super(cloak);
        this.isWorn = isWorn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!isWorn)
            popupMenu.show(this, 0, 0);
        else
            TurnHandler.getActiveVirologist().toggle(cloak);
    }
}
