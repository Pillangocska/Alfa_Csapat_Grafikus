package GUI.view.view.agentView;

import main.com.teamalfa.blindvirologists.agents.virus.DanceVirus;

import javax.swing.*;

public class DanceVirusView extends AgentView{
    private DanceVirus virus;

    public DanceVirusView(DanceVirus virus) {
        this.virus = virus;
        setImageIcon(new ImageIcon("resources/dancevirus.gif"));
    }
}
