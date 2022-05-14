package GUI.view.view.geneticCodeView;

import GUI.view.menus.GeneticCodePopupMenu;
import main.com.teamalfa.blindvirologists.agents.genetic_code.AmnesiaCode;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AmnesiaCodeInventoryView extends AmnesiaCodeView {
    public AmnesiaCodeInventoryView(AmnesiaCode code) {
        super(code);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPopupMenu popupMenu = new GeneticCodePopupMenu();
        popupMenu.show(this, 0, 0);
    }
}
