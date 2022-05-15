package GUI.view.view.equipmentView;

import main.com.teamalfa.blindvirologists.equipments.Bag;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import java.awt.event.ActionEvent;

public class BagInventoryView extends BagView {
    private boolean isWorn;

    public BagInventoryView(Bag bag, boolean isWorn) {
        super(bag);
        this.isWorn = isWorn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TurnHandler.getActiveVirologist().toggle(bag);
    }
}
