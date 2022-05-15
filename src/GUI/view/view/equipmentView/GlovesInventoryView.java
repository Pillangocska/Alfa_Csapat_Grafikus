package GUI.view.view.equipmentView;

import GUI.view.menus.EquipmentPopupMenu;
import GUI.view.menus.WornGlovePopupMenu;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Gloves;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GlovesInventoryView extends GlovesView {
    private boolean isWorn;
    private JPopupMenu popupMenu = new EquipmentPopupMenu(gloves);

    public GlovesInventoryView(Gloves gloves, boolean isWorn) {
        super(gloves);
        this.isWorn = isWorn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!isWorn)
            popupMenu.show(this, 0, 0);
        else {
            popupMenu = new WornGlovePopupMenu(gloves);
            popupMenu.show(this, 0, 0);
        }
    }
}
