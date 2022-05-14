package GUI.view.view.equipmentView;

import main.com.teamalfa.blindvirologists.equipments.active_equipments.Gloves;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import java.awt.event.ActionEvent;

public class GlovesInventoryView extends GlovesView {
    private boolean isWorn;

    public GlovesInventoryView(Gloves gloves, boolean isWorn) {
        super(gloves);
        this.isWorn = isWorn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TurnHandler.getActiveVirologist().toggle(gloves);
    }
}
