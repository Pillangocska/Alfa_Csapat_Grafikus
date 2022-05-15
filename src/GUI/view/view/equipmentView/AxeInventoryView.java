package GUI.view.view.equipmentView;

import GUI.view.frames.GameFrame;
import GUI.view.menus.EquipmentPopupMenu;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AxeInventoryView extends AxeView {
    private boolean isWorn;
    private JPopupMenu popupMenu;

    public AxeInventoryView(Axe axe, boolean isWorn) {
        super(axe);
        this.isWorn = isWorn;
        popupMenu = new EquipmentPopupMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isWorn && GameFrame.getHighlightedVirologistView() != null)
            axe.use(GameFrame.getHighlightedVirologistView().getVirologist());
        else {
            popupMenu.setSize(50, 50);
            popupMenu.show(this, 0, 0);
        }
    }
}
