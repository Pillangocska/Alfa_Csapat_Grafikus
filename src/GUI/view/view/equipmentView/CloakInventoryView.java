package GUI.view.view.equipmentView;

import main.com.teamalfa.blindvirologists.equipments.Cloak;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import java.awt.event.ActionEvent;

public class CloakInventoryView extends CloakView {
    private boolean isWorn;

    public CloakInventoryView(Cloak cloak, boolean isWorn) {
        super(cloak);
        this.isWorn = isWorn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TurnHandler.getActiveVirologist().toggle(cloak);
    }
}
