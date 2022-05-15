package main.GUI.view.view.geneticCodeView;

import main.GUI.view.menus.GeneticCodePopupMenu;
import main.logic.com.teamalfa.blindvirologists.agents.genetic_code.ParalyzeCode;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ParalyzeCodeInventoryView extends ParalyzeCodeView {
    public ParalyzeCodeInventoryView(ParalyzeCode code) {
        super(code);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPopupMenu popupMenu = new GeneticCodePopupMenu();
        popupMenu.show(this, 0, 0);
    }
}
