package main.GUI.view.view.agentView;

import main.GUI.view.frames.GameFrame;
import main.GUI.view.view.VirologistView;
import main.logic.com.teamalfa.blindvirologists.agents.virus.AmnesiaVirus;
import main.logic.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.logic.com.teamalfa.blindvirologists.virologist.Virologist;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AmnesiaVirusView extends AgentView {
    private AmnesiaVirus virus;

    public AmnesiaVirusView(AmnesiaVirus virus) {
        this.virus = virus;
        setImageIcon(new ImageIcon("resources/viruses/greenvirus.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VirologistView highlightedVirologistView = GameFrame.getHighlightedVirologistView();
        if (highlightedVirologistView != null) {
            Virologist target = highlightedVirologistView.getVirologist();
            TurnHandler.getActiveVirologist().use(virus, target);
        }
    }
}
