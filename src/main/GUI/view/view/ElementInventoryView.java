package main.GUI.view.view;

import main.GUI.view.panels.ElementPanel;
import main.logic.com.teamalfa.blindvirologists.virologist.backpack.ElementBank;

public class ElementInventoryView extends ElementView {
    private ElementPanel panel;

    public ElementInventoryView(ElementBank elementBank, ElementPanel panel) {
        super(elementBank);
        this.panel = panel;
        update();
    }

    private void setAminoQuantity(int q) {
        panel.getAminoTextLabel().setText(": " + q);
    }

    private void setNucleoQuantity(int q) {
        panel.getNucleoTextLabel().setText(": " + q);
    }

    @Override
    /**
     *
     */
    public void update() {
        setAminoQuantity(eb.getAminoAcid());
        setNucleoQuantity(eb.getNucleotide());
    }
}
