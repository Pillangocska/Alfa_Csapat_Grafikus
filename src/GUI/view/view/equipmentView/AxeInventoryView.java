package GUI.view.view.equipmentView;

import GUI.view.frames.GameFrame;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;

import java.awt.event.ActionEvent;

public class AxeInventoryView extends AxeView {
    private boolean isWorn;

    public AxeInventoryView(Axe axe, boolean isWorn) {
        super(axe);
        this.isWorn = isWorn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isWorn && GameFrame.getHighlightedVirologistView() != null)
            axe.use(GameFrame.getHighlightedVirologistView().getVirologist());
        else
            TurnHandler.getActiveVirologist().toggle(axe);
    }
}
