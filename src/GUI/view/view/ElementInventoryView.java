package GUI.view.view;

import GUI.view.panels.ElementPanel;
import main.com.teamalfa.blindvirologists.virologist.backpack.ElementBank;

public class ElementInventoryView extends ElementView {
    private ElementPanel panel;

    public ElementInventoryView(ElementBank elementBank, ElementPanel panel) {
        super(elementBank);
        this.panel = panel;
        update();
    }

    private void setAminoQuantity(int q) {
        System.out.println(q);
        panel.getAminoTextLabel().setText(": " + q);
    }

    private void setNucleoQuantity(int q) {
        System.out.println(q);
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
