package main.GUI.view.view.agentView;

import main.GUI.view.frames.GameFrame;
import main.GUI.view.view.VirologistView;
import main.logic.com.teamalfa.blindvirologists.agents.virus.ParalyzeVirus;
import main.logic.com.teamalfa.blindvirologists.turn_handler.TurnHandler;
import main.logic.com.teamalfa.blindvirologists.virologist.Virologist;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ParalyzeVirusView extends AgentView{
    private ParalyzeVirus virus;

    public ParalyzeVirusView(ParalyzeVirus virus) {
        this.virus = virus;
        setImageIcon(new ImageIcon("resources/viruses/bluevirus.png"));
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
