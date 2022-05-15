package main.GUI.view.view.agentView;

import main.GUI.view.frames.GameFrame;
import main.GUI.view.view.VirologistView;
import main.logic.com.teamalfa.blindvirologists.agents.virus.BearVirus;
import main.logic.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.logic.com.teamalfa.blindvirologists.virologist.Virologist;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BearVirusView extends AgentView{
    private BearVirus virus;

    public BearVirusView(BearVirus virus) {
        this.virus = virus;
        setImageIcon(new ImageIcon("resources/viruses/redvirus.png"));
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
