package GUI.view.view.equipmentView;

import GUI.view.frames.GameFrame;
import GUI.view.menus.EquipmentPopupMenu;
import GUI.view.menus.WornAxePopupMenu;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AxeInventoryView extends AxeView {
    private boolean isWorn;
    private JPopupMenu popupMenu;

    public AxeInventoryView(Axe axe, boolean isWorn) {
        super(axe);
        this.isWorn = isWorn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isWorn) {
            if (!axe.isWornOut()) {
                if (GameFrame.getHighlightedVirologistView() != null) {
                    popupMenu = new WornAxePopupMenu();
                    popupMenu.show(this, 0, 0);
                }
            }
            else {
                TurnHandler.getActiveVirologist().toggle(axe);
            }
        }
        else {
            popupMenu = new EquipmentPopupMenu();
            popupMenu.show(this, 0, 0);
        }

        handleIcon();
    }
}
