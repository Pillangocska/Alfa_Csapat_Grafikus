package GUI.view.view.agentView;

import main.com.teamalfa.blindvirologists.agents.virus.BearVirus;

import javax.swing.*;

public class BearVirusView extends AgentView{
    private BearVirus virus;

    public BearVirusView(BearVirus virus) {
        this.virus = virus;
        setImageIcon(new ImageIcon("resources/logo.png")); // todo
    }
}
