package GUI.view.view.agentView;

import main.com.teamalfa.blindvirologists.agents.virus.ParalyzeVirus;

import javax.swing.*;

public class ParalyzeVirusView extends AgentView{
    private ParalyzeVirus virus;

    public ParalyzeVirusView(ParalyzeVirus virus) {
        this.virus = virus;
        setImageIcon(new ImageIcon("resources/viruses/bluevirus.png"));
    }
}
