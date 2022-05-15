package main.GUI.view.view.equipmentView;

import main.GUI.view.menus.EquipmentPopupMenu;
import main.GUI.view.menus.WornGlovePopupMenu;
import main.logic.com.teamalfa.blindvirologists.equipments.active_equipments.Gloves;
import main.logic.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

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
            if(TurnHandler.getActiveVirologist().getField().canChangeEquipment()) {
                popupMenu = new EquipmentPopupMenu(gloves);
                popupMenu.show(this, 0, 0);
            }
        else {
            popupMenu = new WornGlovePopupMenu(gloves);
            popupMenu.show(this, 0, 0);
        }
    }
}
