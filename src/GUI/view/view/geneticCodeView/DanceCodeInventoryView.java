package GUI.view.view.geneticCodeView;

import GUI.view.menus.GeneticCodePopupMenu;
import GUI.view.view.agentView.DanceVirusView;
import main.com.teamalfa.blindvirologists.agents.genetic_code.DanceCode;
import main.com.teamalfa.blindvirologists.agents.virus.DanceVirus;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DanceCodeInventoryView extends DanceCodeView {
    public DanceCodeInventoryView(DanceCode code) {
        super(code);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPopupMenu popupMenu = new GeneticCodePopupMenu();
        popupMenu.show(this, 0, 0);
    }
}
