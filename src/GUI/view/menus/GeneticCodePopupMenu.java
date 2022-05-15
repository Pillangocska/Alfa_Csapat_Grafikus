package GUI.view.menus;

import GUI.view.frames.GameFrame;
import GUI.view.view.equipmentView.AxeInventoryView;
import GUI.view.view.equipmentView.EquipmentView;
import GUI.view.view.geneticCodeView.GeneticCodeView;
import main.com.teamalfa.blindvirologists.agents.genetic_code.GeneticCode;
import main.com.teamalfa.blindvirologists.equipments.active_equipments.Axe;
import main.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GeneticCodePopupMenu extends JPopupMenu {
    public GeneticCodePopupMenu() {
        setOpaque(false);
        setBorderPainted(false);

        JMenuItem craftVirus = new NiceMenuItem();
        craftVirus.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getInvoker() instanceof GeneticCodeView) {
                    GeneticCode gc = ((GeneticCodeView) getInvoker()).getGeneticCode();
                    TurnHandler.getActiveVirologist().getBackpack().createVirus(gc);
                }
            }
        });
        craftVirus.setText("Craft virus");


        JMenuItem craftVaccine = new NiceMenuItem();
        craftVaccine.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getInvoker() instanceof  GeneticCodeView) {
                    GeneticCode gc = ((GeneticCodeView) getInvoker()).getGeneticCode();
                    TurnHandler.getActiveVirologist().getBackpack().createVaccine(gc);
                }
            }
        });
        craftVaccine.setText("Craft vaccine");

        add(craftVirus);

        add(craftVaccine);
    }
}
