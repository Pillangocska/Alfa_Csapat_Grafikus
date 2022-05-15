package main.GUI.view.view.equipmentView;

import main.GUI.view.menus.EquipmentPopupMenu;
import main.logic.com.teamalfa.blindvirologists.equipments.Bag;
import main.logic.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BagInventoryView extends BagView {
    private boolean isWorn;
    private JPopupMenu popupMenu = new EquipmentPopupMenu(bag);

    public BagInventoryView(Bag bag, boolean isWorn) {
        super(bag);
        this.isWorn = isWorn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!isWorn)
            if(TurnHandler.getActiveVirologist().getField().canChangeEquipment())
                popupMenu.show(this, 0, 0);
        else {
            TurnHandler.getActiveVirologist().toggle(bag);
        }
    }
}
