package GUI.view.view.agentView;

import main.com.teamalfa.blindvirologists.agents.virus.AmnesiaVirus;

import javax.swing.*;

public class AmnesiaVirusView extends AgentView {
    private AmnesiaVirus virus;

    public AmnesiaVirusView(AmnesiaVirus virus) {
        this.virus = virus;
        setImageIcon(new ImageIcon("resources/viruses/greenvirus.png"));
    }
}
